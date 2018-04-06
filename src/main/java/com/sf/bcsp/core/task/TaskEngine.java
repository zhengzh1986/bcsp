package com.sf.bcsp.core.task;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有取数任务的执行引擎
 * 任务被提交到TaskDispatcher后，被TaskEngine执行
 * 该执行引擎 功能：
 * 1. 限制线程大小
 * 2. 管理任务的超时，超时后被cancel掉
 * 3. 确保每个任务的资源回收
 * @author 840163
 *
 */
public class TaskEngine {
	
	private TaskEngine(){
		//
	}
	
	private static final Logger logger = LoggerFactory.getLogger(TaskEngine.class);
	
	
	/*
	 * 任务执行线程池，容量是预设的大小，不允许扩展
	 */
	private static final  ExecutorService EXEC_CORE = new ThreadPoolExecutor(100, 
			                                                                 TaskDispatcher.getTaskCapacity(), 
																			 300L,TimeUnit.MILLISECONDS, 
																			 new LinkedBlockingQueue<Runnable>(1),
																		     new ThreadPoolExecutor.AbortPolicy());
	/*
	 * 保存执行中的线程future,用于超时检查
	 */
	private static final Map<FutureHolder, Integer> FUTURE_MAP = new ConcurrentHashMap<>();
	
	
	//该引擎是否已经初始化
	private static boolean isInit = false;

	//初始化
	public static synchronized void start(){
		if(!isInit){
			 runTasks() ;
			 checkTask();
			 isInit = true;
		}
	 }
	
	
	/**
	 * 定时轮询任务表中的任务，把它提交到线程池执行并把返回的futue 保存到FUTURE_MAP中。选择ConcurrentHashMap是因为遍历不需要锁定整个容器
	 */
	private static void runTasks() {
		Thread t = new Thread() {
            @Override
            public void run() {
            	while(true){
	                try{
	                	ITask task = TaskDispatcher.takeTask();
	                	if (task != null){
	                		Future<Integer> f = EXEC_CORE.submit(new TaskExecutor(task));
	                		FUTURE_MAP.put(new FutureHolder(f,task.getTimeOut(),task.getName()), 1);
	                	}
	                }catch(Exception e){
	                	 logger.error("err in tasEnginekMonitor!",e);
	                }
	                try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						 Thread.currentThread().interrupt();
						  break;
					}
            	}
            }
        };
        t.setName("task-dispath-thread");
        t.start();
    }
	
	
	/**
	 * 定时轮询超时的线程，把超时的线程cancel掉,done的移除
	 */
	private static void checkTask() {
		Thread t =  new Thread() {
            @Override
            public void run() {
            	while(true){
	                try{
	                	Iterator<FutureHolder> it = FUTURE_MAP.keySet().iterator();
	                	while(it.hasNext()){
	                		FutureHolder f = it.next();
	                		dealTask(f);
	                	}
	                }catch(Exception e){
	                	 logger.error("err in tasEnginekMonitor!",e);
	                }
	                try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						  Thread.currentThread().interrupt();
						  break;
					}
            	}
            }
        };
      t.setName("task-check-thread");
      t.start();
    }
	
	//把超时的线程cancel掉,done的移除
	private static void dealTask(FutureHolder f){
		if(f.getFuture().isDone()){
			FUTURE_MAP.remove(f);
			logger.error("***task: {} is done***",f.getTaskName());
		}
		else if (f.isTimeOut()){
			FUTURE_MAP.remove(f);
			f.getFuture().cancel(true);
			logger.error("***task: {} is timeout, have been canceled!***",f.getTaskName());
		}
	}
	
	
	/**
	 * 任务执行的模板类，确保doFinal一定执行
	 * @author 840163
	 *
	 */
	 static class TaskExecutor implements Callable<Integer>{
		
		private ITask task;
		
		public TaskExecutor(ITask task){
			this.task = task;
		}
		
		@Override
		public Integer call() throws Exception {
			try{
				if(task.doStart()){
					task.doTask();
				}
			}catch(Exception e){
				logger.error("err in running task->"+task.getName(),e);
				return 0;
			}
			finally{
				task.doFinal();
				TaskDispatcher.doneTask(task);
			}
			return 1;
		}
	}
	
	 /**
	  * future 包装类，用于任务的超时检查
	  * @author 840163
	  *
	  */
	 private static class FutureHolder{
		 
		 private String taskName ;
		  
		 private Future<Integer> futue;
		 
		 private int timeOut;
		 
		 private Date startTime;
		
		 public FutureHolder(Future<Integer> future,int timeOut,String taskName){
			 this.futue = future;
			 this.timeOut = timeOut;
			 this.taskName = taskName;
			 startTime = new Date();
		 }
		 
		 public boolean isTimeOut(){
			 Date now = new Date();
			 return (now.getTime()-startTime.getTime())>timeOut*1000;
		 }
		 
		 public Future<Integer> getFuture(){
			 return futue;
		 }
		 
		 public String getTaskName(){
			 return this.taskName;
		 }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((futue == null) ? 0 : futue.hashCode());
			result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
			result = prime * result + timeOut;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FutureHolder other = (FutureHolder) obj;
			if (futue == null) {
				if (other.futue != null)
					return false;
			} else if (futue != other.futue)
				return false;
			if (startTime == null) {
				if (other.startTime != null)
					return false;
			} else if (!startTime.equals(other.startTime))
				return false;
			return (timeOut == other.timeOut)?true:false;
		}
		 
	}

}