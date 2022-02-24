import entity.LyObj;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class LyTest {
    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        LyObj lyObj1=new LyObj();
        lyObj1.setAge(12);
        lyObj1.setName("xiaochen");
        LyObj lyObj2=new LyObj();
        lyObj2.setName(null);
        lyObj2.setAge(12);
        BeanUtils.copyProperties(lyObj2,lyObj1);
        System.out.println(lyObj1);
    }
}
