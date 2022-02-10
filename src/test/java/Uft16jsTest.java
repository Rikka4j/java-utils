import org.junit.Test;
import utils.Uft16js;

/**
 * <p>
 *
 * </p>
 *
 * @author wpc
 * @since 2022/2/10
 */
public class Uft16jsTest {
    @Test
    public void test1(){
        String title="买房让销售收玉米杆&#129315;&#129315;这方法&#127569;";
        Uft16js uft16js = new Uft16js();
        String trans = uft16js.trans(title);
        System.out.println(trans);
    }
}
