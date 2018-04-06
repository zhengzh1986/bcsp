package com.sf.bcsp.data.base;

import java.util.Map;

/**
 *  数据处理 接口
 *
 */
public interface BcspDataReader {
	/**
	 * 
	 * @return
	 */
	public Map<String,Object> nextReader();
	
	public void reparametrization(Map<String, Object> params);

	public void close();
}
