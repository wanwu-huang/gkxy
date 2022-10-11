package com.mentpeak.website.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class StrUtil {

	private StrUtil() {
	}

	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String getPrefix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}


	/**
	 * @param bound
	 * @return
	 */
	public static String getRandom(int bound) {
		Random ra = new Random();
		String result = "";
		for (int i = 1; i <= bound; i++) {
			result += ra.nextInt(10);
		}
		return result;
	}



	/**
	 * 判断是否包含特殊字符
	 * @param str
	 * @return
	 */
	public static boolean isSpecialChar(String str) {
		String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否包含特殊字符 ——
	 * @param str
	 * @return
	 */
	public static boolean isSpecialCharLine(String str) {
		String regEx = "——";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
}
