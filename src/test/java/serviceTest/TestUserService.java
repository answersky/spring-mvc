package serviceTest;

import com.java.domain.User;
import com.java.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by liuf on 2016/7/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class TestUserService {
    @Resource
    private UserService userService;

    @Test
    public void find(){
        User user=userService.findUserById(7);
        System.out.println(user);
    }

    @Test
    public void testSql(){
        read();
    }

    private void read(){
        File file = new File("D:\\Documents\\Tencent Files\\2048875230\\FileRecv\\11.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String sql="SELECT b. NAME,A .pn,A .grab_url FROM pm_product A LEFT JOIN pm_supplier b ON A .supplier_id = b. ID WHERE a.supplier_id=";
                String sql1=" LIMIT 5";
                System.out.println(sql+tempString+sql1);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
