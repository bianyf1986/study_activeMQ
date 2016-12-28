package com.afeng.utils;

/**
 * 
 * @Description : 字符串工具类
 * @author      : bianyf
 */
public class StringUtils {
	
	public static void main(String[] args) {
		
		//System.out.println(isNotEmpty(""));
		System.out.println(toFirstLowerCase("DDBBddDDDD", 2));
		
	}

	/**
	 * 
	 * @Function :  isNotEmpty
	 * @Desc     :  判断目标字符串是否为空：返回（true 不为空,false 为空）
	 * @Author   :  yfbian
	 * @param value 目标字符串
	 * @return true or false
	 */
    public static boolean isNotEmpty(String value) {
        if(value != null && !value.trim().equals("") && !value.trim().equalsIgnoreCase("null"))  {
            return true;
        }
        return false;
    }

    /**
	 * 
	 * @Function :  toFirstLowerCase
	 * @Desc     :  把源字符串从0--index变为小写（例如：source="ABCD",index=2,结果为abCD）
	 * @Author   :  yfbian
	 * @param source 源字符串
	 * @param index 小写截止下标
	 * @return 转换小写后的字符串
	 */
    public static String toFirstLowerCase(String source, int index) {
        String temp = source.substring(0, index);
        source = temp.toLowerCase()+source.substring(index,source.length());
        return source;
    }
    
    /**
	 * 
	 * @Function :  toFirstUperCase
	 * @Desc     :  把源字符串从0--index变为大写（例如：source="abcd",index=2,结果为ABcd）
	 * @Author   :  yfbian
	 * @param source 源字符串
	 * @param index 大写截止下标
	 * @return 转换大写后的字符串
	 */
    public static String toFirstUperCase(String source, int index) {
        String temp = source.substring(0, index);
        source = temp.toUpperCase()+source.substring(index,source.length());
        return source;
    }

    /**
	 * 
	 * @Function :  isEquals
	 * @Desc     :  判断两个字符串是否相等，如果两个字符串有任意一个为空，返回false
	 * @Author   :  yfbian
	 * @param value1 比较字符串1
	 * @param value2 比较字符串2
	 * @return 比较结果（相等true，不相等false）
	 */
    public static boolean isEquals(String value1,String value2) {
        if(isNotEmpty(value1) && isNotEmpty(value2)) {
            if(value1.equals(value2)) {
                return true;
            }
        }
        return false;
    }
    /**
	 * 
	 * @Function :  cutString
	 * @Desc     :  截断字符串，从头开始截取，后面加上省略号（……），如果value的长度没有length大的话，直接返回value。
	 * @Author   :  yfbian
	 * @param value 源字符串
	 * @param length 截取长度，从头开始截取
	 * @return 截断后的结果字符串
	 */
    public static String cutString(String value, int length) {
    	if(isNotEmpty(value)) {
    		if(value.length() > length){
    			return value.substring(0, length -1) + "...";
    		}else{
    			return value;
    		}
    	}
    	return "";
    }

    /**
	 * 
	 * @Function :  outString
	 * @Desc     :  输出字符串，处理了空值的情况
	 * @Author   :  yfbian
	 * @param value 源字符串
	 * @return 进行简单处理后的字符串
	 */
    public static String outString(String value) {
    	if(isNotEmpty(value)) {
			return value;
    	}
    	return "";
    }
    
}

