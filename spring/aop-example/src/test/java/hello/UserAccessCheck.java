package hello;

import hello.service.Business1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccessCheck {

    private Logger logger = LoggerFactory.getLogger(UserAccessCheck.class);

    @Autowired
    private Business1 business1;

    @Test
    public void test1() {
        logger.info("UserAccessCheck {}", business1.calculate());
    }
}
