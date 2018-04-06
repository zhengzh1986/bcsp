package com.sf.bcsp.service.base.biz.impl;

import com.sf.bcsp.service.base.biz.IBcspCustInfoBiz;
import com.sf.bcsp.data.base.BscpJdbcTemplate;
import com.sf.bcsp.conf.BcspSqlConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询客户信息
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015-07-09     847792          Create
 * ****************************************************************************
 * </pre>
 * @author 张伟
 */
@Service
@Transactional(readOnly = true)
public class BcspCustInfoBiz implements IBcspCustInfoBiz {
	
	public static final String formateTime = "yyyyMMdd";
 
	@Resource
	private BscpJdbcTemplate bcspJdbcTemplate;

	@Autowired
	BcspSqlConfig bcspSqlConfig;
	
	/**
	 * 查询客户信息
	 * @param deptCode 归属客户网点
	 * @param custCode 归属客户卡号
	 * @param beginDt    归属客户账期开始日期
	 * @param endDt    归属客户账期结束日期
	 * @param  totalAmtBig
	 * @return
	 */
	@Override
	public Map<String,Object> getCustInfo(String taskJobId,String deptCode, String custCode, Date beginDt,
			Date endDt, BigDecimal  totalAmtBig) {
		Map<String, Object> result = new HashMap<>();
		
		//查询客户资料??
		Map<String,Object> custinfo= bcspJdbcTemplate.queryForMap(bcspSqlConfig.getSqlGetCustInfo(),new Object[]{deptCode,custCode});

		String fProcessTime = DateFormatUtils.format(beginDt, formateTime)+ "-" + DateFormatUtils.format(endDt, formateTime);
		String paymentCycleQty=String.valueOf(custinfo.get("PAYMENT_CYCLE_QTY"));
		result.put("settledCharge",0);//是否预约报价，当前模板无此界面
		result.put("custCode",custCode);
		
		result.put("month", DateFormatUtils.format(endDt, "MM")+"月");
		result.put("fDeptCode", custinfo.get("DEPT_CODE"));
		result.put("fVipCode", custinfo.get("VIP_CODE"));
		result.put("fCustName", custinfo.get("CUST_NAME"));
		result.put("linkName", custinfo.get("LINK_NAME"));
		result.put("fCurrency", custinfo.get("CURRENCY_CODE"));
		result.put("fDunCode", custinfo.get("DUN_CODE"));
		result.put("startDt", beginDt);
		result.put("endDt", endDt);
		result.put("fstartDt", DateFormatUtils.format(beginDt, "yyyy-MM-dd"));
		result.put("fendDt", DateFormatUtils.format(endDt, "yyyy-MM-dd"));
		result.put("fProcessTime", fProcessTime);
		result.put("reportCustCode", custinfo.get("REPORT_CUST_CODE"));
		result.put("paymentDisplayMode",custinfo.get("PAYMENT_DISPLAY_MODE"));//到期日显示方式
		
		String billPeriod;
		String reckonType=(String) custinfo.get("RECKONINGPERIOD_TYPE");
		//传BRIM的账期
		if("3".equals(reckonType)) {
			billPeriod=fProcessTime;
		}else if("0".equals(reckonType) || "1".equals(reckonType)){
			billPeriod=DateFormatUtils.format(endDt, "yyyyMM");
		}else{
			billPeriod=null;
		}
		result.put("billPeriod", billPeriod);
		//到期日
		Calendar expiringDt = Calendar.getInstance();
		expiringDt.setTime(endDt);
		if (StringUtils.isNotEmpty(paymentCycleQty)) {
			Integer tempPaymentCycleQty = Integer.parseInt(paymentCycleQty.replace("天", ""));
			expiringDt.add(Calendar.DATE, tempPaymentCycleQty);
		}
		Date fExpiringDate = expiringDt.getTime();
		result.put("fExpiringDate", DateFormatUtils.format(fExpiringDate, "yyyy年MM月dd日"));
		
		
		//查询总金额
		//custInfoDao.getNextTotalAmt(deptCode, custCode, DateFormatUtils.format(endDt, "yyyy-MM"));
		Double nextTotalAmt =(Double) bcspJdbcTemplate.queryForObject(bcspSqlConfig.getSqlCustNextTotalAmt(),new Object[]{deptCode, custCode,DateFormatUtils.format(endDt, "yyyy-MM")}, Double.class);
		Double totalAmt=totalAmtBig.doubleValue();
		result.put("reportTotalAmt", totalAmt);
		result.put("nextTotalAmt", nextTotalAmt);
		BigDecimal allTotalAmt=(new BigDecimal(String.valueOf(String.valueOf(totalAmt)))).add(new BigDecimal(String.valueOf(String.valueOf(nextTotalAmt))));
		result.put("allTotalAmt",allTotalAmt.doubleValue());
		result.put("showheadcust", "尊敬的 "+custinfo.get("LINK_NAME")+"("+custCode+") 先生/女士");
		result.put("showheadprocesstime", "    感谢您使用我司的服务，以下是您"+fProcessTime);
		result.put("showheadtotalamt", "的电子账单，金额为："+totalAmt);
		result.put("showheadlastpay", "    您本期的最晚汇款日是: "+DateFormatUtils.format(fExpiringDate, "yyyy年MM月dd日")+"，请勿错过您的期限。");
		result.put("showreporttotalamt", "    本期应付金额为："+totalAmt);

		return result;
	}
}