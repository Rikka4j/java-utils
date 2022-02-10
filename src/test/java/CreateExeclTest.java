import org.junit.Test;
import utils.CreateExecl;

/**
 * <p>
 *
 * </p>
 *
 * @author wpc
 * @since 2022/2/10
 */
public class CreateExeclTest {
    @Test
    public void test1(){
        CreateExecl createExecl = new CreateExecl();
        createExecl.getExecl("D:\\data\\testExecl.csv");
    }
}
