package com.afeng.utils.javaBasis;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

/**
 * 
 * @Description : java.sql.Clob类型工具类
 * @author      : yfbian
 */
public class ClobUtils {

	/**
	 * 
	 * @Function :  getString
	 * @Desc     :  Clob类型to字符串类型
	 * @Author   :  yfbian
	 * @param clob java.sql.Clob类型
	 * @return String类型数据  
	 */
	public static String getString(java.sql.Clob clob) {
		StringBuffer sb = new StringBuffer();
		if (clob != null) {
			try {
				BufferedReader bufferReader = new BufferedReader(clob.getCharacterStream());
				try {
					String str;
					while ((str = bufferReader.readLine()) != null) {
						sb.append(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Function :  getClob
	 * @Desc     :  字符串类型toClob类型
	 * @Author   :  yfbian
	 * @param str String类型
	 * @return java.sql.Clob类型数据  
	 */
	public static java.sql.Clob getClob(String str) {
		java.sql.Clob clob = null;
		try {
			if (str != null) {
				clob = new SerialClob(str.toCharArray());
			}
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clob;
	}

}
