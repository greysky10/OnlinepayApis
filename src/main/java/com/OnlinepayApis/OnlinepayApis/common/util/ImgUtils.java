package com.OnlinepayApis.OnlinepayApis.common.util;

import com.OnlinepayApis.OnlinepayApis.common.exceptions.ClientException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author demoAuthor
 * @Description 图片工具类
 * @Version V2.0.3
 * @Notice
 */
public class ImgUtils {

	private static final Logger log = LoggerFactory.getLogger(ImgUtils.class);

	/**
	 * 
	 * 将图片转base64编码
	 * 
	 * @author demoAuthor
	 * @param imgFileName
	 * @return GBK格式的base64编码
	 * @throws ClientException
	 * @Notice 无
	 */
	public static String img2Base64(String imgFileName) throws ClientException {
		InputStream in = null;
		byte[] data;
		try {
			in = new FileInputStream(imgFileName);
			data = new byte[in.available()];
			in.read(data);
			return new String(Base64.encodeBase64(data), "GBK");
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new ClientException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new ClientException(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}

	}

}
