package com.sf.bcsp.service.base.biz.impl;

import com.sf.bcsp.service.base.biz.IBcspBankInfoBiz;
import com.sf.bcsp.data.base.BscpJdbcTemplate;
import com.sf.bcsp.conf.BcspSqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class BcspBankInfoBiz implements IBcspBankInfoBiz{

    @Autowired
    private BscpJdbcTemplate bcspJdbcTemplate;

    @Autowired
    private BcspSqlConfig bcspSqlConfig;


    @Override
    public Map<String, Object> getCustBankInfoByCust(String deptCode, String custCode) {

        Map<String,Object> bankAccount= bcspJdbcTemplate.queryForMap(bcspSqlConfig.getBankAccountCustSql(),new Object[]{custCode,deptCode});

        Map<String,Object> result = new HashMap<>();
        if(bankAccount==null){
            bankAccount=bcspJdbcTemplate.queryForMap(bcspSqlConfig.getBankAccountSql(),new Object[]{deptCode});
        }

        if (bankAccount != null) {

            result.put("companyName", bankAccount.get("COMPANY_NAME"));// 公司名称
            result.put("phoneNo", bankAccount.get("PHONE_NO"));// 联系电话
            result.put("faxNo", bankAccount.get("FAX_NO"));// 传真号码
            result.put("contactorName", bankAccount.get("CONTACTOR_NAME"));// SF联系人
            result.put("bankaccountno", bankAccount.get("BANK_ACCOUNT"));// 银行账号
            result.put("stateEmail", bankAccount.get("EMAIL_ADDR"));// 联系邮箱
            result.put("bankname", bankAccount.get("BANK_NAME"));// 开户银行
        } else {
            result.put("companyName", "");// 公司名称
            result.put("phoneNo", "");// 联系电话
            result.put("faxNo", "");// 传真号码
            result.put("contactorName", "");// SF联系人
            result.put("bankaccountno", "");// 银行账号
            result.put("stateEmail", "");// 联系邮箱
            result.put("bankname", "");// 开户银行
        }
        return result;
    }
}
