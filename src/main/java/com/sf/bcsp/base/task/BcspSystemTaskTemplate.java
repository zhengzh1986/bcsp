package com.sf.bcsp.base.task;

import com.sf.bcsp.base.entity.BcspSystemJob;
import com.sf.bcsp.base.utils.SpringUtil;
import com.sf.bcsp.base.biz.IBcspSystemJobBiz;
import com.sf.bcsp.core.task.TaskDispatcher;
import com.sf.bcsp.service.base.BcspBillControlTemplateLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 定时任务 - 通用任务处理入口
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015-07-09     847792          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 张伟
 */
@Service
@Scope(value = "prototype")
public class BcspSystemTaskTemplate {

	private static final Logger logger = LoggerFactory.getLogger(BcspSystemTaskTemplate.class);

	@Resource
	private IBcspSystemJobBiz bcspSystemJobBiz;

	/** 批次对应最大数据量 */
	private Integer taskLoopCount;

	private String handleType;

	private boolean singleThread;

	public void executeJob() {
		executeThreadJob();
	}

	/**
	 * SFIT0045 2014-5-21 执行多线程任务
	 */
	public void executeThreadJob() {

		// 扫描系统任务表中是否存在待处理类型的任务数据
		int dataCount = bcspSystemJobBiz.findSysTaskData(handleType);
		if (dataCount > 0) {
			BcspSystemJob sysJob = null;

			String processNo = null;

			try {
				// 生成批次号具体规则待定...
				 processNo = handleType + UUID.randomUUID().toString();
				// 更新批次号、任务运行时间、和任务状态
				bcspSystemJobBiz.updateProcessNo(processNo, taskLoopCount, handleType);

				// 重新根据批次号获取实际待处理的数据
				List<BcspSystemJob> processList = bcspSystemJobBiz.getBcspSystemTaskData(processNo);

				int dataSize = processList != null ? processList.size() : 0; // 获取实际已经更新批次号的数据

				logger.info("当前处理批次号:" + processNo + ":总记录数据为" + dataSize);

				for (int i = 0; i < dataSize; i++) {
					sysJob = processList.get(i);
					logger.info("当前处理客户为" + sysJob.getDeptCode() + "," + sysJob.getAccountCode());
					BcspBillControlTemplateLocal taskTemplate = (BcspBillControlTemplateLocal) SpringUtil
							.getApplicationContext().getBean(sysJob.getHandleName());
					taskTemplate.setParameter(sysJob);
					if (!TaskDispatcher.addTask(taskTemplate)){
						logger.error("当前jobId"+sysJob.getJobId()+"提交异常: 线程资源不足。");
						sysJob.setStatus(-1);
						sysJob.setJobResult("do it next time");
						bcspSystemJobBiz.updateSystemJob(sysJob);
					}
				}
			} catch (Exception e) {
				logger.error("当前批次："+processNo+"发生异常:",e);
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("----------生成月结账单任务 主控制入口[" + Thread.currentThread().getId() + "]:没有任务数据---------");
			}
			return;
		}

	}

	public Integer getTaskLoopCount() {
		return taskLoopCount;
	}

	public void setTaskLoopCount(Integer taskLoopCount) {
		this.taskLoopCount = taskLoopCount;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public boolean isSingleThread() {
		return singleThread;
	}

	public void setSingleThread(boolean singleThread) {
		this.singleThread = singleThread;
	}
}
