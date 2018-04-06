package com.sf.bcsp.base.biz;

import java.util.List;

import com.sf.bcsp.base.entity.BcspSystemJob;


/**
 * 定时任务业务层
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015-07-09     847792          Create
 * ****************************************************************************
 * </pre>
 * @author 张伟
 */
public interface IBcspSystemJobBiz {
	
	/**
	 * 扫描系统任务表中是否存在待处理的任务数据
	 * @param handleType
	 * @return
	 */
	public int findSysTaskData(String handleType);
	/**
	 * 更新批次号
	 * @param processNo
	 * @param taskLoopCount
	 * @param handleType
	 */
	public int updateProcessNo(String processNo, int taskLoopCount, String handleType);
	/**
	 * 根据批次号获取实际待处理的任务数据
	 * @param processNo
	 * @return
	 */
	public List<BcspSystemJob> getBcspSystemTaskData(String processNo);
	
	/**更新批次处理成功的数据*/
	public int updateSuccessListInfo(List<BcspSystemJob> sysJob);
	
	/**更新批次处理中出现错误的数据*/
	public int updateErrorListInfo(List<BcspSystemJob> errorList);
	
	/**更新任务*/
	public int updateSystemJob(BcspSystemJob sysJob);
	
	/**保存任务*/
	public int saveSystemJob(BcspSystemJob sysJob);
	
	/**获取任务下一个ID*/
	public String getSystemJobId();
	
	/**按ID获取任务*/
	public BcspSystemJob getSystemJobById(String id);
	
	/**获取失败的任务*/
	public List<BcspSystemJob> getBcspFailSystemJob(String handType);
	
	/**批量更新*/
	public void updateRestBatch(List<BcspSystemJob> list);
}
