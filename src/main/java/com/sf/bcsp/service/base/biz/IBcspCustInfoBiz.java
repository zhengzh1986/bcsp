package com.sf.bcsp.service.base.biz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 查询客户信息
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015-07-09     172162          Create
 * ****************************************************************************
 * </pre>
 * @author zzh
 */
public interface IBcspCustInfoBiz {
	/**
	 * 查询客户信息
	 * @param deptCode 归属客户网点
	 * @param custCode 归属客户卡号
	 * @param bilDt    归属客户账期开始日期
	 * @param endDt    归属客户账期结束日期
	 * @return
	 */


	Map<String, Object> getCustInfo(String taskJobId, String deptCode,
                                    String custCode, Date beginDt, Date endDt, BigDecimal totalAmt);
	
	
	
}
