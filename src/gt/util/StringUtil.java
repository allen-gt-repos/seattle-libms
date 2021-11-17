/**
 * 
 */
package gt.util;

/**
 * @author Wang, Yinuo
 * String Utility Class
 */
public class StringUtil {
	
	/**
	 * Check the string is empty or not
	 * @param str
	 * @return bool
	 */	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Check the string is not empty or not
	 * @param str
	 * @return bool
	 */
	public static boolean isNotEmpty(String str){
		if(str!= null && !"".equals(str.trim())){
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
