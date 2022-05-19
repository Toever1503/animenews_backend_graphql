package animenews.config.interceptor;

import animenews.Utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class BeginAfterHandleRequestFilter implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(BeginAfterHandleRequestFilter.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long currentThreadId = Thread.currentThread().getId();
        logger.info("AfterGoOutFilter.preHandle: currentThreadId = " + currentThreadId);

        request.setAttribute("currentThreadId", currentThreadId);
        RequestUtil.addRequest(request);

        System.out.println("preHandle: " + Thread.currentThread().getId());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long currentThreadId = (Long) request.getAttribute("currentThreadId");
        logger.info("AfterGoOutFilter.postHandle: currentThreadId = " + currentThreadId);

        RequestUtil.removeRequest(currentThreadId);
        System.out.println("postHandle: " + Thread.currentThread().getId());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
