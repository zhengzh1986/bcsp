package com.sf.bcsp.core.task;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务接受和分发
 * 并定时监控任务池
 * @author 840163
 *
 */
public class TaskDispatcher {
	
	private TaskDispatcher(){
		//
	}
	
    private static final Logger logger = LoggerFactory.getLogger(TaskDispatcher.class);
	
	private  static int taskCapacity = 100;
	
	//总等待任务数=正在执行的任务数+队列中待执行任务
	private static final AtomicInteger WAITING_TASK_NUM = new AtomicInteger(0);  
	
	//正在执行的任务数
	private static final AtomicInteger RUNNING_TASK_NUM = new AtomicInteger(0);
	
	
	private static  LinkedBlockingQueue<ITask>  taskList;
	
	//该引擎是否已经初始化
	private static boolean isInit = false;
	
	static{
		start();
		TaskEngine.start();
	}
	
	//初始化
	private static synchronized void  start(){
		if (!isInit){
			taskMonitor();
			isInit = true;
		}
	}

	/**
	 * 添加任务到任务池
	 * @param task
	 * @return 成功添加true, 失败false
	 */
	public static synchronized boolean addTask(ITask task){
		if (taskList == null){
			taskList = new LinkedBlockingQueue<>(taskCapacity);
		}
		try{
			if (WAITING_TASK_NUM.get()<taskCapacity){
				   taskList.add(task);
				   WAITING_TASK_NUM.incrementAndGet();
				   return Boolean.TRUE;
			}
		}catch(Exception e){
			 logger.error("task list exceed limited capacity!",e);
		}
		 return Boolean.FALSE;
	}
	
	/**
	 * 从任务池获取任务，正在执行的任务数加1
	 * @return
	 */
	public static synchronized ITask takeTask(){
		if (taskList == null){
			taskList = new LinkedBlockingQueue<>(taskCapacity);
		}
		ITask task =  taskList.poll();
		if (task!=null) {
			RUNNING_TASK_NUM.incrementAndGet();
		}
		return task;
	}
	
	/**
	 * 任务执行完成，不管成功失败都要执行该方法
	 * @param task
	 */
	public static void doneTask(ITask task){
		if (task !=null){
			WAITING_TASK_NUM.decrementAndGet();
			RUNNING_TASK_NUM.decrementAndGet();
		}
	}
	
	/**
	 * 任务池监控线程
	 */
	private static void taskMonitor() {
		Thread t = new Thread() {
            @Override
            public void run() {
            	while(true){
            		logger.info("worker Thread Capacity:{}, wating task number is {},runing task number is {}!",taskCapacity,WAITING_TASK_NUM,RUNNING_TASK_NUM);
            		try {
						TimeUnit.MINUTES.sleep(1);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						break;
					}
            	}
            }
        };
        
	   t.setName("task-capacity-monitor");
       t.start();
    }
	
	public static int getTaskCapacity(){
		return taskCapacity;
	}
	
	public static void setTaskCapacity(int tasksCount){
		taskCapacity = tasksCount;
	}
}
