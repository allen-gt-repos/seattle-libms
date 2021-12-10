/**
 * 
 */
package gt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	/**
	 * 
	 * @author Wang, Yinuo
	 * @throws ParseException 
	 * @replacedBy java.sql.Date Date.valueOf(string)
	 */
    public static java.sql.Date string2Date(String StringDate) throws ParseException {
        // string to date
    	Date date= new SimpleDateFormat("yyyy-MM-dd").parse(StringDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
	/**
	 * 
	 * @author Wang, Yinuo
	 * @throws ParseException 
	 * @replacedBy java.sql.Time Time.valueOf(string)
	 */
    public static java.sql.Time string2Time(String StringTime) throws ParseException{
       // string to time
        Date time = new SimpleDateFormat("HH:mm:ss").parse(StringTime);
        java.sql.Time sqlTime = new java.sql.Time(time.getTime());
        return sqlTime;
    }
	

    /**
     * @author Wang, Yinuo
     * @param str
     * @return true if match the regulation expression
     */
    public static boolean isEmail( String str ) {
    	
    	String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher( str );
    	return matcher.matches();

    	}

    /**
     * @author Wang, Yinuo
     * @param coordStr
     * @return the float coordinate of destination
     */
    public static float[] getCoordinate(String coordStr) {
    	
    	float coord[] = new float[2];
    	String xyString[] = coordStr.split(",");
    	for (int i = 0; i < 2; i++) {
			coord[i] = Float.valueOf(xyString[i]);	
//			System.out.println(coord[i]);
		}
    	
    	
    	return coord;
    }
    /**
     * Check the string is a digit sequence or not
     * 
     */
    public static boolean isNumeric(String str)
    {
    	for (int i = 0; i < str.length(); i++)
    	{
    		
    		if (!Character.isDigit(str.charAt(i)))
    		{
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Check the string is a valid date format or  not
     */
    public static boolean isValidDate(String str) {
    	
    	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateFormat.parse(str);
//			System.out.println(dateFormat.parse(str));
		} catch (Exception e) {
			return false;

		}
		return true;
	}
}
