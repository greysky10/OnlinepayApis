package com.OnlinepayApis.OnlinepayApis.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * @author demoAuthor
 * @Description 客户端工具类
 * @Version V2.0.3
 * @Notice
 */
public class CRC32Util {
	public static String crc32(String filePath) throws IOException {
		CRC32 crc32 = new CRC32();
		try (FileInputStream fis = new FileInputStream(filePath);
				CheckedInputStream in = new CheckedInputStream(fis, crc32)) {
			while (in.read() != -1) {

			}

		} catch (IOException e) {
			throw e;
		}
		return Long.toHexString(crc32.getValue());
	}

	public static void main(String[] args) throws IOException {
		String filePath = "D://file/a.zip";
		String crc32 = crc32(filePath);
		System.out.println("crc32: " + crc32);
	}

}
