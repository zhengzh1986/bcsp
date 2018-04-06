package com.sf.bcsp.data.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * 处理 Hbase 中的数据
 * 
 * @author 172162
 *
 */
public class HbaseDataProcessUtils {

	private static final Logger logger = Logger.getLogger(HbaseDataProcessUtils.class);

	/*
	 * public final static String[] EXPRESS_COLMUN_KEYS = { "customer_code",
	 * "dept_code", "report_begin_dt", "report_end_dt", "vip_code",
	 * "vip_zone_code", "otherman", "waybill_no", "waybill_id",
	 * "billing_version_no", "weight_of_charge_qty", "otherzone", "other_zone",
	 * "othercust_name", "othertel", "empcode", "sender_customer_name",
	 * "sender_customer_contact_name", "sender_customer_phone_no",
	 * "receiver_customer_name", "receiver_customer_phone_tel",
	 * "reciever_customer_contact_name", "original_zone_code",
	 * "original_zone_name", "destination_zone_code", "destination_zone_name",
	 * "memo", "takeover_tm", "sending_tm", "income_dt", "bill_dt",
	 * "income_type_code", "currency_code", "payment_change_type_code",
	 * "is_interior", "amt_type", "old_waybill_no", "send_remark_one",
	 * "send_remark_two", "remark_third", "cargo_type_name", "rebate_amt",
	 * "fee_item_amt", "new_fee_item_amt", "due_amt", "fuel_surcharge_amt",
	 * "rebate_due_amt", "charge_item_code", "type_code", "order_flag",
	 * "new_charge_item_code", "caculate_dt", "batch_no", "create_tm",
	 * "signed_back_waybill_no", "subscriber_name", "sender_customer_addr",
	 * "receiver_member_no", "receiver_tm", "receiver_customer_addr",
	 * "goods_on_bill_desc", "quantity", "service_property_value_1",
	 * "actual_weight_qty", "volume", "fee_ind_type", "cargo_type_name_new",
	 * "bill_long", "bill_width", "bill_high", "distance_type_code", "ext1",
	 * "ext3", "ext4", "sender_province", "receiver_province", "billing_date",
	 * "income_type_name", "pick_up_date", "should_pay_amount" };
	 */

	public final static String[] EXPRESS_COLMUN_KEYS = { "payerCustCode", "payerZoneCode", "report_begin_dt",
			"report_end_dt", "payerCustCode", "payerZoneCode", "otherman", "waybillno", "waybill_id",
			"billing_version_no", "feeWeight", "otherZoneName", "other_zone", "otherCompanyName", "othertel",
			"handName", "senderCompany", "sender", "senderPhone", "receiverCompany", "receiverPhone", "receiver",
			"senderCity", "senderCity", "receiverCity", "receiverCity", "serviceFeeName", "takeover_tm", "sending_tm",
			"income_dt", "billingDate", "income_type_code", "currency_code", "payment_change_type_code", "is_interior",
			"amt_type", "sourceWaybill", "selfDefinition1", "selfDefinition2", "selfDefinition3", "cargo_type_name",
			"rebateAmount", "payAmount", "payAmount", "shouldPayAmount", "fuel_surcharge_amt", "rebate_due_amt",
			"charge_item_code", "type_code", "order_flag", "charge_item_code", "caculate_dt", "batch_no", "create_tm",
			"returnTracking", "consignee", "senderAddress", "pickUpEmp", "signTime", "receiverAddress", "cargo",
			"cargoQuantity", "value", "totalWeight", "volume", "fee_ind_type", "productType", "bill_long", "bill_width",
			"bill_high", "distance_type_code", "ext1", "ext3", "ext4", "senderProvince", "receiverProvince",
			"billing_date", "incomeTypeName", "pickUpDate", "shouldPayAmount" };

	final static String SPILT_KEYS = "`";

	/**
	 * 根据字符串 获取数据对象。
	 * 
	 * @param str
	 * @return
	 */
	public static Map getHbaseRecord(String data) {
		int dataType = 0;
		if (data.indexOf("{'t1':'e','c':") >= 0) {
			return JSONObject.parseObject(data, new TypeReference<Map<String, String>>() {
			});
		}

		Map map = new HashMap<>();

		String[] splitStrs = data.split(SPILT_KEYS);
		for (int i = 0; i < splitStrs.length; i++) {
			map.put(EXPRESS_COLMUN_KEYS[i], splitStrs[i]);
		}
		return map;
	}

}
