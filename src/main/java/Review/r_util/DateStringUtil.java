package Review.r_util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/28/2016
 * Time: 12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateStringUtil {
    private static SimpleDateFormat dateFormat_set = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat_set = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date StringToDate(String dateString) throws ParseException {
        Date date = (Date) dateFormat_set.parse(dateString);
        return date;
    }

    public static String DateToString(Date date) {
        String str = null;
        str = dateFormat_set.format(date);
        return str;
    }

    public static Date StringToTime(String dateString) throws ParseException {
        Date date = (Date) timeFormat_set.parse(dateString);
        return date;
    }

    public static String TimeToString(Date date) {
        String str = null;
        str = timeFormat_set.format(date);
        return str;
    }


}
