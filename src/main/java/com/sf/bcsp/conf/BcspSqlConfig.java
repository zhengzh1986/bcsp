package com.sf.bcsp.conf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@DisconfFile(filename="sql.properties")
public class BcspSqlConfig {
	
	private String findSysTaskDataSql;

	
	private String updateProcessNoSql;

	
	private String getBcspSystemTaskDataSql;

	
	private String updateSuccessListInfoSql;

	
	private String updateErrorListInfoSql;

	
	private String updateSystemJobSql;

	
	private String saveSystemJobSql;

	
	private String getSystemJobIdSql;

	
	private String getSystemJobByIdSql;
	
	private String getBcspFailSystemJobSql;
	
	private String updateRestBatchSql;


	private String bankAccountSql;


	private String bankAccountCustSql;



    private String sqlGetCustInfo;


    private String sqlCustTotalAmt;


    private String sqlCustNextTotalAmt;


	public void setSqlGetCustInfo(String sqlGetCustInfo) {
        this.sqlGetCustInfo = sqlGetCustInfo;
    }

    public void setSqlCustTotalAmt(String sqlCustTotalAmt) {
        this.sqlCustTotalAmt = sqlCustTotalAmt;
    }

    public void setSqlCustNextTotalAmt(String sqlCustNextTotalAmt) {
        this.sqlCustNextTotalAmt = sqlCustNextTotalAmt;
    }

    @DisconfFileItem(name="sqlGetCustInfo")
    public String getSqlGetCustInfo() {

        return sqlGetCustInfo;
    }

    @DisconfFileItem(name="sqlCustTotalAmt")
    public String getSqlCustTotalAmt() {
        return sqlCustTotalAmt;
    }

    @DisconfFileItem(name="sqlCustNextTotalAmt")
    public String getSqlCustNextTotalAmt() {
        return sqlCustNextTotalAmt;
    }

    public void setBankAccountSql(String bankAccountSql) {
		this.bankAccountSql = bankAccountSql;
	}

	public void setBankAccountCustSql(String bankAccountCustSql) {
		this.bankAccountCustSql = bankAccountCustSql;
	}

	@DisconfFileItem(name="getBankAccountSql")
	public String getBankAccountSql() {
		return bankAccountSql;
	}

	@DisconfFileItem(name="getBankAccountCustSql")
	public String getBankAccountCustSql() {
		return bankAccountCustSql;
	}

	private String bcspJsonFlag; //BCSP.JSON.FLAG;


	
	@DisconfFileItem(name="bcspjsonflag")
	public String getBcspJsonFlag() {
		return bcspJsonFlag;
	}

	public void setBcspJsonFlag(String bcspJsonFlag) {
		this.bcspJsonFlag = bcspJsonFlag;
	}

	@DisconfFileItem(name="findSysTaskDataSql")
	public String getFindSysTaskDataSql() {
		return findSysTaskDataSql;
	}

	public void setFindSysTaskDataSql(String findSysTaskDataSql) {
		this.findSysTaskDataSql = findSysTaskDataSql;
	}

	@DisconfFileItem(name="updateProcessNoSql")
	public String getUpdateProcessNoSql() {
		return updateProcessNoSql;
	}

	public void setUpdateProcessNoSql(String updateProcessNoSql) {
		this.updateProcessNoSql = updateProcessNoSql;
	}

	@DisconfFileItem(name="getBcspSystemTaskDataSql")
	public String getGetBcspSystemTaskDataSql() {
		return getBcspSystemTaskDataSql;
	}

	public void setGetBcspSystemTaskDataSql(String getBcspSystemTaskDataSql) {
		this.getBcspSystemTaskDataSql = getBcspSystemTaskDataSql;
	}

	@DisconfFileItem(name="updateSuccessListInfoSql")
	public String getUpdateSuccessListInfoSql() {
		return updateSuccessListInfoSql;
	}

	public void setUpdateSuccessListInfoSql(String updateSuccessListInfoSql) {
		this.updateSuccessListInfoSql = updateSuccessListInfoSql;
	}

	@DisconfFileItem(name="updateErrorListInfoSql")
	public String getUpdateErrorListInfoSql() {
		return updateErrorListInfoSql;
	}

	public void setUpdateErrorListInfoSql(String updateErrorListInfoSql) {
		this.updateErrorListInfoSql = updateErrorListInfoSql;
	}

	@DisconfFileItem(name="updateSystemJobSql")
	public String getUpdateSystemJobSql() {
		return updateSystemJobSql;
	}

	public void setUpdateSystemJobSql(String updateSystemJobSql) {
		this.updateSystemJobSql = updateSystemJobSql;
	}

	@DisconfFileItem(name="saveSystemJobSql")
	public String getSaveSystemJobSql() {
		return saveSystemJobSql;
	}

	public void setSaveSystemJobSql(String saveSystemJobSql) {
		this.saveSystemJobSql = saveSystemJobSql;
	}

	@DisconfFileItem(name="getSystemJobIdSql")
	public String getGetSystemJobIdSql() {
		return getSystemJobIdSql;
	}

	public void setGetSystemJobIdSql(String getSystemJobIdSql) {
		this.getSystemJobIdSql = getSystemJobIdSql;
	}

	@DisconfFileItem(name="getSystemJobByIdSql")
	public String getGetSystemJobByIdSql() {
		return getSystemJobByIdSql;
	}

	public void setGetSystemJobByIdSql(String getSystemJobByIdSql) {
		this.getSystemJobByIdSql = getSystemJobByIdSql;
	}

	@DisconfFileItem(name="getBcspFailSystemJobSql")
	public String getGetBcspFailSystemJobSql() {
		return getBcspFailSystemJobSql;
	}

	public void setGetBcspFailSystemJobSql(String getBcspFailSystemJobSql) {
		this.getBcspFailSystemJobSql = getBcspFailSystemJobSql;
	}

	@DisconfFileItem(name="updateRestBatchSql")
	public String getUpdateRestBatchSql() {
		return updateRestBatchSql;
	}

	public void setUpdateRestBatchSql(String updateRestBatchSql) {
		this.updateRestBatchSql = updateRestBatchSql;
	}
	
	
	
}
