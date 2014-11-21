package com.yunyou.tounahao.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return [0-9]{5,9}
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			//Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Pattern p = Pattern.compile("^(1)\\d{10}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 检验身份证
	 * @param no
	 * @return
	 */
	public static boolean isIdCardNO(String no) {
		boolean flag = false;
		try {
			//Pattern p = Pattern.compile("^\\d{15}$)|(^\\d{17}(\\d|X)$)");
			Pattern p = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$");
			Matcher m = p.matcher(no);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证是否是数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNum(String number) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^[0-9]{5}$");
			Matcher m = p.matcher(number);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证是否是邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			//String check = "^([a-z0-9A-Z]+[-|.]?)+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";//效率太低
			//String check =  "^[a-z0-9A-Z][a-zA-Z0-9_.-]*@[0-9a-zA-Z]+(.[a-zA-Z]+)+$";
			String check = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
			// String check =
			// "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}
	/**
	 * 验证是否是银行卡号
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean idBankCardId(String cardId) {
		boolean flag = false;
		try {
			String check = "^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(cardId);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}

}
