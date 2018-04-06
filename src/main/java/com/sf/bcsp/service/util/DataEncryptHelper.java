package com.sf.bcsp.service.util;
import cn.tass.hsmApi.SFExpress.UserDataEncrypt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;

@Component
public class DataEncryptHelper {
	private static final Logger logger = LoggerFactory.getLogger(DataEncryptHelper.class);
	/**
	 * 电话号码信息类型标识
	 */
	public static final int phoneFlag = 0x11;
	/**
	 * 身份证号信息（限纯数字格式）
	 */
	public static final int cardFlag = 0x12;
	/**
	 * 通讯地址
	 */
	public static final int addressFlag = 0x13;
	/**
	 * 其他类型
	 */
	public static final int otherFlag = 0x14;

	/**
	 * 加密后密文前缀,用于判断密文和明文,加密后需要手动给密文添加此前缀
	 */
	public static final String prefix = "DE";

	@Autowired
	private UserDataEncrypt userDataEncrypt;

	public String dataDecrypt(String cipherData) {
		if(null == userDataEncrypt){
			return "****";
		}
		if (StringUtils.isEmpty(cipherData)) {
			return "";
		}
		if (cipherData.startsWith(prefix)) {
			try {
			    String phoneNo =  userDataEncrypt.dataDecrypt(cipherData.substring(2));
			    return phoneNo;
			}catch(Exception e){
				logger.error("解密失败!",e);
				return "****";
			}
		}
		return cipherData;
	}
}