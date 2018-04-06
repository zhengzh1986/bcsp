package com.sf.bcsp.service.base.entity;

import java.math.BigDecimal;
import java.util.List;

public class BillingCustResponse {

    private List<BillingCustItem> custItemList;

    private String code;
    private String msg;
    private BigDecimal totalAmt;


    public List<BillingCustItem> getCustItemList() {
        return custItemList;
    }

    public void setCustItemList(List<BillingCustItem> custItemList) {
        this.custItemList = custItemList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }


}	
