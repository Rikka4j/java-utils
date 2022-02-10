import org.junit.Test;
import utils.ReadUser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 *  测试ReadUser类
 * </p>
 *
 * @author wpc
 * @since 2022/2/10
 */
public class ReadUserTest {

    @Test
    public void test1() throws IOException {
        ReadUser readUser = new ReadUser();
        String path = "C:\\fxb\\city.txt";
        ArrayList<String> read = readUser.read(path);
        System.out.println(read.size());
        for (String str:read) {
            System.out.println(str);
        }
    }
}
