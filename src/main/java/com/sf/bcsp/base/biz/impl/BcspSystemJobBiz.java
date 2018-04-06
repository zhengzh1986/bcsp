package com.sf.bcsp.base.biz.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sf.bcsp.base.biz.IBcspSystemJobBiz;
import com.sf.bcsp.base.entity.BcspSystemJob;
import com.sf.bcsp.base.entity.sqlmapper.BcspSystemJobSqlMapper;
import com.sf.bcsp.conf.BcspSqlConfig;
import com.sf.bcsp.data.base.BscpJdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 定时任务业务层
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
@Service("bcspSystemJobBiz")
public class BcspSystemJobBiz implements IBcspSystemJobBiz {

	private static final Logger logger = LoggerFactory.getLogger(BcspSystemJobBiz.class);
	

	@Resource
	private BscpJdbcTemplate bcspJdbcTemplate;

	@Autowired
	private BcspSqlConfig bcspSqlConfig;

	@Override
	public int findSysTaskData(String handleType) {
		return  Integer.valueOf(bcspJdbcTemplate.queryForObject(bcspSqlConfig.getFindSysTaskDataSql(), new Object[] { handleType } ,Integer.class)) ;
	}
	


	@Override
	public int updateProcessNo(String processNo, int taskLoopCount, String handleType) {
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error("get localhost Error:", e);
			hostName = "";
		}
		return bcspJdbcTemplate.update(bcspSqlConfig.getUpdateProcessNoSql(), new Object[] { processNo, hostName, handleType, taskLoopCount });
	}

	@Override
	public List<BcspSystemJob> getBcspSystemTaskData(String processNo) {
		return bcspJdbcTemplate.query(bcspSqlConfig.getGetBcspSystemTaskDataSql(), new Object[] { processNo }, new BcspSystemJobSqlMapper());
	}

	@Override
	public int updateSuccessListInfo(List<BcspSystemJob> sysJob) {

		sysJob.forEach(job-> bcspJdbcTemplate.update(bcspSqlConfig.getUpdateSuccessListInfoSql(), job.getJobId()));
		return 1;
	}

	@Override
	public int updateErrorListInfo(List<BcspSystemJob> errorList) {
		errorList.forEach(job-> bcspJdbcTemplate.update(bcspSqlConfig.getUpdateSuccessListInfoSql(), job.getJobId()));
		return 1;
	}

	@Override
	public int updateSystemJob(BcspSystemJob sysJob) {
		return bcspJdbcTemplate.update(bcspSqlConfig.getUpdateSystemJobSql(), new Object[] {sysJob.getJobResult(),sysJob.getStatus(),sysJob.getDealCount(),sysJob.getJobId()});
	}

	@Override
	public int saveSystemJob(BcspSystemJob sysJob) {
		return bcspJdbcTemplate.update(bcspSqlConfig.getSaveSystemJobSql(), new Object[] {});

	}

	@Override
	public String getSystemJobId() {
		return null;
	}

	@Override
	public BcspSystemJob getSystemJobById(String id) {
		return null;
	}

	@Override
	public List<BcspSystemJob> getBcspFailSystemJob(String handType) {
	
		return new ArrayList<>();
	}

	@Override
	public void updateRestBatch(List<BcspSystemJob> list) {
		throw new UnsupportedOperationException();
	}

}
