package com.OnlinepayApis.OnlinepayApis.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author demoAuthor
 * @Description 流水号工具类
 * @Version V2.0.3
 * @Notice
 */
public class SerialNoUtil {

	private SerialNoUtil() {}
	
	public static String getDateTime() {
		return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
	}

	public static String getRandomNum(int length) {
		if (length < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	public static String getSerialNo() {
		return getDateTime().concat(getRandomNum(10));
	}

}
