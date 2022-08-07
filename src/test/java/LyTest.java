import com.alibaba.fastjson.JSON;
import com.ly.MyApplication;
import com.ly.entity.User;
import entity.LyObj;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.InvocationTargetException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
@AutoConfigureMockMvc
public class LyTest {

    @Autowired
    private MockMvc mockMvc;

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


    @Test
    public void test1() throws Exception {
        User user=new User();
        user.setAge(12);

        ClassPathResource classPathResource = new ClassPathResource("ly/ly.xml");
        String s = FileUtils.readFileToString(classPathResource.getFile(),
                Charsets.UTF_8).trim();
        user.setName(s);
        this.mockMvc.perform(
                post("/helloUser")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        //.param("name","12323")
                        .content(JSON.toJSONString(user))
        ).andDo(print());
    }

}
