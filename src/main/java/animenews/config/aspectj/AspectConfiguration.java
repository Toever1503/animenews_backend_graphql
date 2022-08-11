package animenews.config.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

//@Aspect
//@Configuration
public class AspectConfiguration {
//    @Around("execution(* animenews.graphql.resolvers.*.*(..))") // pointcut expression only apply with these methods service has first parameter type is Pageable
//    public Object fixPageIfExceed(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        return joinPoint.proceed(joinPoint.getArgs()); //continue  proceed to method
//    }

}
