package com.sf.bcsp.base.entity.sqlmapper;

import com.sf.bcsp.base.entity.BcspSystemJob;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  记录 映射关系
 * @author 172162
 *
 */
public class BcspSystemJobSqlMapper implements RowMapper<BcspSystemJob> {

	@Override
	public BcspSystemJob mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		BcspSystemJob bcspSystemJob = new BcspSystemJob();
		bcspSystemJob.setJobId(resultSet.getString("SYSTEM_JOB_ID"));
		bcspSystemJob.setAccountCode(resultSet.getString("ACCOUNT_CODE"));
		bcspSystemJob.setDeptCode(resultSet.getString("DEPT_CODE"));
		bcspSystemJob.setStartDt(resultSet.getDate("START_DT"));
		bcspSystemJob.setEndDt(resultSet.getDate("END_DT"));
		bcspSystemJob.setStatus(resultSet.getInt("STATUS"));
		bcspSystemJob.setJobResult(resultSet.getString("JOB_RESULT")); //
		bcspSystemJob.setStartRunTm(resultSet.getDate("START_RUN_TM")); //
		bcspSystemJob.setEndRunTm(resultSet.getDate("END_RUN_TM")); //
		bcspSystemJob.setDealCount(resultSet.getInt("DEAL_COUNT"));
		bcspSystemJob.setHandleName(resultSet.getString("HANDLE_NAME"));
		bcspSystemJob.setHandleType(resultSet.getString("HANDLE_TYPE"));
		bcspSystemJob.setLocalFile(resultSet.getString("LOCAL_FILE"));
		bcspSystemJob.setProcessNo(resultSet.getString("PROCESS_NO"));
		bcspSystemJob.setExt1(resultSet.getString("EXT1"));
		bcspSystemJob.setExt2(resultSet.getString("EXT2"));
		bcspSystemJob.setExt3(resultSet.getString("EXT3"));
		bcspSystemJob.setExt4(resultSet.getString("EXT4"));
		bcspSystemJob.setExt5(resultSet.getString("EXT5"));
		bcspSystemJob.setReportLogId(resultSet.getString("REPORT_LOG_ID"));
		bcspSystemJob.setFiledsId(resultSet.getString("FIELD_ID"));
		return bcspSystemJob;
	}

}
