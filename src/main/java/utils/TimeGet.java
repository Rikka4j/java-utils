package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 *  获取时间
 * </p>
 * "yyyy-MM-dd"
 *"yyyy-MM-dd HH:mm:ss"
 * @author wpc
 * @since 2021/10/28
 */

public class TimeGet {
    /**
     * @param pattern : 时间格式  如:yyyy-MM-dd
     * @return 返回所需时间格式字符串
     **/
    public String getNowTime(String pattern){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String format = formatter.format(date);
        return format;
    }
    public String getYesterday(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
        String yesterday=sp.format(d);
        return  yesterday;
    }
    /**
     *Description:获取上周六的时间
     **/
    public  String getLastSaturday(){
        Calendar date=Calendar.getInstance(Locale.CHINA);
        date.setFirstDayOfWeek(Calendar.MONDAY);
        //周数减一，即上周
        date.add(Calendar.WEEK_OF_MONTH,-1);
        //日子设为周几
        date.set(Calendar.DAY_OF_WEEK, 7);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String times = format.format(date.getTime());
        return times;
    }


}

