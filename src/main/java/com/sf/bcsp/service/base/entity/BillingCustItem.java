package com.sf.bcsp.service.base.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class BillingCustItem {

	private String keyId;
	
	@JSONField(name="bill_type")
	private String billType;
	
	@JSONField(name="fee_item_amt")
	private String feeItemAmt;
	
	@JSONField(name="payer_zone_code")
	private String payerZoneCode;
	
	@JSONField(name="rebate_begin_dt")
	private String rebateBeginDt;
	
	@JSONField(name="rebate_end_dt")
	private String rebateEndDt;
	

	@JSONField(name="remark")
	private String remark;


	public String getKeyId() {
		return keyId;
	}


	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}




	public String getBillType() {
		return billType;
	}


	public void setBillType(String billType) {
		this.billType = billType;
	}


	public String getFeeItemAmt() {
		return feeItemAmt;
	}


	public void setFeeItemAmt(String feeItemAmt) {
		this.feeItemAmt = feeItemAmt;
	}


	public String getPayerZoneCode() {
		return payerZoneCode;
	}


	public void setPayerZoneCode(String payerZoneCode) {
		this.payerZoneCode = payerZoneCode;
	}


	public String getRebateBeginDt() {
		return rebateBeginDt;
	}


	public void setRebateBeginDt(String rebateBeginDt) {
		this.rebateBeginDt = rebateBeginDt;
	}


	public String getRebateEndDt() {
		return rebateEndDt;
	}


	public void setRebateEndDt(String rebateEndDt) {
		this.rebateEndDt = rebateEndDt;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	



	

}
