package animenews.config.filter;

import animenews.Utils.RequestUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class BeforeGoInFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute("currentThreadId", Thread.currentThread().getId());
        RequestUtil.addRequest(request);
        filterChain.doFilter(request, response);
    }
}
