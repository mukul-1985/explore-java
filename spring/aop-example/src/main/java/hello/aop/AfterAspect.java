package hello.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterAspect {

    private Logger logger = LoggerFactory.getLogger(AfterAspect.class);

    @After("execution(* hello.service.*.*(..))")
    public void after(JoinPoint joinPoint){
        logger.info("----------------");
        logger.info("after aspect {}", joinPoint);
    }

    @AfterReturning(value = "execution(* hello.service.*.*(..))", returning = "value")
    public void afterReturning(JoinPoint joinPoint, Object value) {
        logger.info("----------------");
        logger.info("after returning aspect {} is {}", joinPoint, value);
    }
}
