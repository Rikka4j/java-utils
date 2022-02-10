package utils;



import java.util.Set;
import static cn.hutool.core.util.RandomUtil.getRandom;


/**
 * <p>
 *  获取set的一个随机值
 * </p>
 * @author yzd
 * @since 2022/1/10
 */

public class RandomSet {
    public <E> E getRandomElement(Set<E> set){
        int rn = getRandom().nextInt(set.size());
        int i = 0;
        for (E e : set) {
            if(i==rn){
                return e;
            }
            i++;
        }
        return null;
    }
}
