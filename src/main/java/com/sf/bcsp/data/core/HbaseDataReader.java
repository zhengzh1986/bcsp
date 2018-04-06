package com.sf.bcsp.data.core;

import java.io.IOException;
import java.util.Map;

import com.sf.bcsp.data.base.BcspDataReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.sf.bcsp.dataprovider.common.hbase.HbaseGet;

public class HbaseDataReader implements BcspDataReader {

	private Map<String, Object> params = null;
	private HbaseGet hbaseGet = new HbaseGet();
	private ResultScanner scaner;
	private Result result;
	private Scan scan = new Scan();
	private String prefixKey;
	protected Table table;
	private String tableName;

	public HbaseDataReader() {

	}

	@Override
	public Map<String, Object> nextReader() {
		try {
			result = scaner.next();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (result == null) {
			return null;
		}

		if (result.rawCells() == null || result.rawCells().length == 0) {
			return null;
		}
		return HbaseDataProcessUtils.getHbaseRecord(Bytes.toString(CellUtil.cloneValue(result.rawCells()[0])));
	}

	@Override
	public void reparametrization(Map<String, Object> params) {
		this.params = params;
		tableName = "bcsp:tt_ebil_month_rpt_data";
		String custCode = String.valueOf(params.get("reportCustCode"));
		String deptCode = String.valueOf(params.get("fDeptCode"));
		String beginDate = String.valueOf(params.get("fstartDt")).replace("-", "");
		String endDate = String.valueOf(params.get("fendDt")).replace("-", "");
		prefixKey = StringUtils.reverse(custCode) + deptCode + beginDate + endDate;
		hbaseGet.setScanCondition(tableName, prefixKey);
		hbaseGet.doStart();
		table = hbaseGet.getTable();

		String type = String.valueOf(params.get("key999")); // 业务类型
		scan.setRowPrefixFilter(Bytes.toBytes(prefixKey + type));
		try {
			scaner = table.getScanner(scan);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		hbaseGet.doFinal();
	}
}
