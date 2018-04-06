package com.sf.bcsp.service.base.biz;

import java.util.Map;

public interface IBcspBankInfoBiz {


    Map<String,Object> getCustBankInfoByCust(String deptCode,
                                             String custCode);


}
