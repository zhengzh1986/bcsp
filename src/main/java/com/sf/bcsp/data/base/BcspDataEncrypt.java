package com.sf.bcsp.data.base;
import cn.tass.exceptions.TAException;
import cn.tass.hsmApi.SFExpress.UserDataEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;

@Configuration
public class BcspDataEncrypt {

	private static final Logger logger = LoggerFactory.getLogger(BcspDataEncrypt.class);
	
	@Value("${dataEncryptInfo}")
	private String msgHead;
	
	@Bean("userDataEncrypt")
	public UserDataEncrypt getUserDataEncrypt() {
		UserDataEncrypt userDataEncrypt;
		try {
			userDataEncrypt = UserDataEncrypt.getInstance(msgHead);
		} catch (TAException e) {
			logger.error("加密机调用失败!",e);
			return null;
		}
		return userDataEncrypt;
	}

}