package com.sf.bcsp.base.entity;

import java.util.Date;

/**
 * 定时任务实体类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015-07-09     847792          Create
 * ****************************************************************************
 * </pre>
 * @author 张伟           
 */
public class BcspSystemJob {

	// id
	private String jobId;
	
	// 账号代码(收派员工号、客户卡号)
	private String accountCode;
		
	// 网点代码
	private String deptCode;
	
	// 开始时间
	private Date startDt;
	
	// 结束时间
	private Date endDt;
	
	// -1：待处理。0：处理成功。1：处理失败 2：处理失败
	private Integer status;
	
	// 任务处理结果描述
	private String jobResult;
	
	// 任务执行开始时间
	private Date startRunTm;
	
	// 任务执行结束时间
	private Date endRunTm;
	
	// 任务处理失败次数
	private Integer dealCount;
	
	// 任务处理类
	private String handleName;
	
	// 任务计划开始处理时间
	private Date startHandleTm;
	
	// 任务执行IP地址
	private String serverIp;
	
	// 国际化信息{}
	private String local;
	
	// 查询日期类型 ：0：营业收入时间 1：账单日期
	private Integer dateType;
	
	// 文件名后缀
	private String fileExtName;
	
	// 任务处理源类型：WEB DB
	private String handleType;
	
	// 创建人时间
	private Date createTm;
	
	// 创建人工号
	private String createEmpCode;
	
	// 文件类型任务，文件完整地址
	private String localFile;
	
	// 批次号
	private String processNo;

	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private Double  totalAmt;
	private String reportLogId;
	private String filedsId;
	
	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAll) {
		this.totalAmt = totalAll;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}


	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJobResult() {
		return jobResult;
	}

	public void setJobResult(String jobResult) {
		this.jobResult = jobResult;
	}

	public Date getStartRunTm() {
		return startRunTm;
	}

	public void setStartRunTm(Date startRunTm) {
		this.startRunTm = startRunTm;
	}

	public Date getEndRunTm() {
		return endRunTm;
	}

	public void setEndRunTm(Date endRunTm) {
		this.endRunTm = endRunTm;
	}

	public Integer getDealCount() {
		return dealCount;
	}

	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}

	public String getHandleName() {
		return handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

	public Date getStartHandleTm() {
		return startHandleTm;
	}

	public void setStartHandleTm(Date startHandleTm) {
		this.startHandleTm = startHandleTm;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	public String getFileExtName() {
		return fileExtName;
	}

	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public String getCreateEmpCode() {
		return createEmpCode;
	}

	public void setCreateEmpCode(String createEmpCode) {
		this.createEmpCode = createEmpCode;
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	public String getReportLogId() {
		return reportLogId;
	}

	public void setReportLogId(String reportLogId) {
		this.reportLogId = reportLogId;
	}

	public String getFiledsId() {
		return filedsId;
	}

	public void setFiledsId(String filedsId) {
		this.filedsId = filedsId;
	}


	
}
