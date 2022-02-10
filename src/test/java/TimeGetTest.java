import org.junit.Test;
import utils.TimeGet;

/**
 * <p>
 *
 * </p>
 *
 * @author wpc
 * @since 2022/2/10
 */
public class TimeGetTest {
    @Test
    public void test1(){
        TimeGet timeGet = new TimeGet();

        String nowTime1 = timeGet.getNowTime("yyyy-MM-dd");
        System.out.println(nowTime1);

        String nowTime2 = timeGet.getNowTime("yyyy-MM-dd HH:mm:ss");
        System.out.println(nowTime2);

        String yesterday = timeGet.getYesterday();
        System.out.println(yesterday);

        String lastSaturday = timeGet.getLastSaturday();
        System.out.println(lastSaturday);
    }
}
