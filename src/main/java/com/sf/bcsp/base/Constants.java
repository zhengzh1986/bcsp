package com.sf.bcsp.base;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;


public class Constants {

	/**
	 * 二维码目录
	 */

	// public static final String
	// imageQrcode="/com/sf/module/ebilbootstrap/META-INF/images/sf_qrcode.png";
	public static final String imageQrcode = "sf_qrcode.png";
	public static final String imageWaterMark = "sf_water_mark.bmp";
	public static final String imageLogo = "/com/sf/module/ebilbootstrap/META-INF/image/sf_logo.png";
	public static final String imagePhone = "/com/sf/module/ebilbootstrap/META-INF/image/sf_phone.png";
	public static HashMap<String, String> pdfReportHeader = new HashMap<String, String>();
	public static final String pdfDocument="pdfDocument";

	static {
		pdfReportHeader.put("expressWaybill", "快递运单明细");
		pdfReportHeader.put("expressRebate", "快递运费折扣资料");
		pdfReportHeader.put("expressGylWaybill", "供应链订单明细");
		pdfReportHeader.put("expressCpWaybill", "仓配订单明细");
		pdfReportHeader.put("scmWmsbillData", "仓储费用");
		pdfReportHeader.put("expressWarehouse", "入仓费");
		pdfReportHeader.put("expressSingleRebate", "特殊单票折扣");
		pdfReportHeader.put("expressTax", "税  金");
		pdfReportHeader.put("expressCompensate", "理赔资料");
		pdfReportHeader.put("expressT801", "次晨理赔");
	}

	public static String fourSpaceStr = " " + " " + " " + " ";
	public static String sevenSpaceStr = " " + " " + " " + " " + " " + " "
			+ " ";
	public static String eightSpaceStr = " " + " " + " " + " " + " " + " "
			+ " " + " ";
	public static String tenSpaceStr = " " + " " + " " + " " + " " + " " + " "
			+ " " + " " + " ";

	public static final String EXPRESSWAYBILL = "expressWaybill";
	public static final String EXPRESSGYLWAYBILL = "expressGylWaybill";
	public static final String EXPRESSCPWAYBILL = "expressCpWaybill";
	public static final String EXPRESSREBATE = "expressRebate";
	public static final String EXPRESSCPREBATE = "expressCpRebate";
	public static final String CPSPECIALREBATE = "cpSpecialRebate";
	public static final String EXPRESSWAREHOUSE = "expressWarehouse";
	public static final String EXPRESSSINGLEREBATE = "expressSingleRebate";
	public static final String EXPRESSTAX = "expressTax";
	public static final String EXPRESSCOMPENSATE = "expressCompensate";
	public static final String EXPRESST801 = "expressT801";

	public static final String EXPRESSGYLREBATE = "expressGylRebate";
	public static final String EXPRESSGYLCOMPENSATE = "expressGylCompensate";
	public static final String EXPRESSCPCOMPENSATE = "expressCpCompensate";
	public static final String SCMWMSBILLDATAGYL = "scmWmsbillDataGyl";

	public static final String SCMWMSBILLDATACP = "scmWmsbillDataCp";

	public static final String WAYBILLREPORT = "账单明细";
	public static final String CUST_CODE = "custCode";
	public static final String payAmount = "payAmount";
	public static final String rebateAmount = "rebateAmount";
	public static final String shouldPayAmount = "shouldPayAmount";
	public static final String parameter = "\r\n\r\n";
	public static final String rebateAmt = "rebateAmt";
	public static final String expressTax = "expressTax";
	public static final String lineTotalAmount = "lineTotalAmount";
	public static final String expressWarehouse = "expressWarehouse";

	public static final String expressCompensate = "expressCompensate";
	public static final String minusFee = "minusFee";
	public static final String minusMonthlyFee = "minusMonthlyFee";
	public static final String expressT801 = "expressT801";
	public static final String expressGylWaybill = "expressGylWaybill";
	public static final String expressGylCompensate = "expressGylCompensate";
	public static final String expressCpWaybill = "expressCpWaybill";
	public static final String success = "success";
	public static final String param = "0.000";
	public static final String subItemName = "subItemName";
	public static final String spacea = "         ";
	public static final String feeWeight = "weight_of_charge_qty";
	public static final String feeItemAmt = "fee_item_amt";
	public static final String rebate_amt = "rebate_amt";
	public static final String newFeeItemAmt = "should_pay_amount";
	public static final String sheet = "sheettemp";
	public static final String WORKBOOK="workBook";
	public static final String TEMPLATE_WOKR_ROWS="templateRowkRows";
	public static final String TEMPLATE_INIT_RANGEMAP="initRangeMap";
	public static final String currRow = "currentRow";
	public static final String compareTotal = "compareTotal";



	/***************************************************************************
	 * excel的后缀
	 */
	public static final String XLS_REPORT_EXT = ".xls";

	public static final String ZIP_NAME = ".zip";

	public  static final  String star = "****";
	public static final  String EBIL_ADMIN_USER_NAME = "admin";
	/** ECP系统简称 */
	public static final String ECP = "ECP";
	/** EBIL系统简称 */
	public static final String EBIL = "EBIL";
	/** CCP系统简称 */
	public static final String CCP = "CCP";
	/** CDH系统简称 */
	public static final String CDH = "CDH";
	/** CBE系统简称 */
	public static final String CBE = "CBE";
	/** 系统自动创建人工号 */
	public static final String CREATE_EMPCODE = "999999";

	/**系统任务失败标志 */
	public static final String RESULT_FAIL = "fail";
	/**系统任务成功标志 */
	public static final String RESULT_SUCCESS = "success";

	/**esb接口返回成功标志*/
	public static final String ESB_SUCCESS = "100";
	/**esb接口返回失败标志*/
	public static final String ESB_FAIL = "101";

	public static final String SEPARATOR = "/";


	public static final String RESULT_NEXT="next";

	public static final String RESULT_DONE="done";


	public static final String DEFAULT_JOB_TIMEOUT_TIME="5000";

	public static final String YYYY_M_MDD = "yyyyMMdd";

	/**
	 * 子模板路径
	 */
	public static final String REPORT_SUBREPORT_DIR = "SUBREPORT_DIR";

	/**
	 * 报表文件名的pdf扩展名
	 */
	public static final String REPORT_FILENAME_EXT_PDF = ".pdf";

	/**
	 * 报表文件名的word扩展名
	 */
	public static final String REPORT_FILENAME_EXT_RTF = ".doc";
	/**
	 * 报表文件名的xls扩展名
	 */
	public static final String REPORT_FILENAME_EXT_XLS =".xls";
	/**
	 * jasper报表模板目录路径
	 */
	public static final String REPORT_TEMPLATE_DIR = "com/sf/bcsp/generator/template/";

	/**
	 * 下载文件存放路径Unix/Linux环境
	 */
	public static final String DOWNLOAD_PATH_UNIX= "/nfsc/EBIL/share/billing/downloads/";
	/**
	 * 中港澳台件的区域类型
	 */
	public static final String[] DISTANCE_TYPE_CN = {"R1","R101","R10101","R10102","R102","R10201","R10202","R2","R201","R202","R204"};

	public static String getConfPath(){
		/**
		 String confPath=System.getenv().get("CONF_PATH");
		 if(StringUtils.isEmpty(confPath)){
		 confPath=System.getProperty("CONF_PATH");
		 }
		 return confPath;
		 */
		try {
			Resource[] rsTmp = null;//AppContext.getContext().getResourcePatternResolver().getResources("classpath:esb.properties");
			if(rsTmp.length>0){
				return rsTmp[0].getFile().getParent();
			}else{
				String confPath=System.getenv().get("CONF_PATH");
				if(StringUtils.isEmpty(confPath)){
					confPath=System.getProperty("CONF_PATH");
				}
				return confPath;
			}
		} catch (IOException e) {
			return null;
		}
	}

}
