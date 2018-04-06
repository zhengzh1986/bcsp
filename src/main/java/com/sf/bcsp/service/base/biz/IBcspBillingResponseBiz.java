package com.sf.bcsp.service.base.biz;

import com.sf.bcsp.base.entity.BcspSystemJob;
import com.sf.bcsp.service.base.entity.BillingCustResponse;

public interface IBcspBillingResponseBiz {

    BillingCustResponse getBillCustResponse(BcspSystemJob systemJob);

}
