package cigniti.rmg.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*Class is used to create reusable functions  */
/**
 * 
 * @author Santosh BM 
 *
 */
public class RmgUtilities {

	@SuppressWarnings("deprecation")
	/**
	 * 
	 * @param String
	 * @return java.sql.Timestamp
	 */
	public static Timestamp StringToTimestamp(String str){   
		
	System.out.println( "Hello World!" );

    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	java.sql.Date sqlDate=null;
	java.sql.Timestamp sqlTimestamp =null;

	    try {
	        java.util.Date utilDate = format.parse(str);
	      //  sqlDate = new java.sql.Date(utilDate.getTime());
	        sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
	        System.out.println(sqlDate);
	        System.out.println(sqlTimestamp);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		return sqlTimestamp;
    }
	
}
