package com.sf.bcsp.service.base.biz.impl;

import com.sf.bcsp.base.entity.BcspSystemJob;
import com.sf.bcsp.data.core.HbaseDataReader;
import com.sf.bcsp.service.base.biz.IBcspBankInfoBiz;
import com.sf.bcsp.service.base.biz.IBcspBillingResponseBiz;
import com.sf.bcsp.service.base.biz.IBcspCustInfoBiz;
import com.sf.bcsp.service.base.entity.BillingCustItem;
import com.sf.bcsp.service.base.entity.BillingCustResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("pro")
@Service
public class BcspBillingResponseBiz implements IBcspBillingResponseBiz {

    private static final Logger logger = LoggerFactory.getLogger(BcspBillingResponseBiz.class);

    @Autowired
    private IBcspCustInfoBiz bcspCustInfoBiz;

    @Autowired
    private IBcspBankInfoBiz bcspBankInfoBiz;

    @Autowired
    private HbaseDataReader hbaseDataReader;

    private BigDecimal totalAmt = new BigDecimal(0);

    private Map<String, Object> customerMap = new HashMap<>();

    private List<BillingCustItem> custItemList = new ArrayList<>();

    @Override
    public BillingCustResponse getBillCustResponse(BcspSystemJob systemJob) {
        getCustomerOtherData();
        getCustomTotalAmt();
        return null;
    }


    private String getCustomerOtherData() {
        try {
            Map<String, Object> dataMap = hbaseDataReader.nextReader();
            if (dataMap == null) {
                return "";
            }
            custItemList.add(null);
        } catch (Exception e) {
            logger.error("processing customerData err:", e);
        } finally {
            hbaseDataReader.close();
        }
    }

    private void getCustomTotalAmt() {
        try {
            // hbaseDataReader.reparametrization();
            while (true) {
                Map<String, Object> dataMap = hbaseDataReader.nextReader();
                if (dataMap == null) {
                    break;
                }
                totalAmt = totalAmt.add(new BigDecimal("0"));
            }
        } catch (Exception e) {
            logger.error("processing getCustomTotalAmt err:", e);
        } finally {
            hbaseDataReader.close();
        }
    }
}
