package animenews.config;

import animenews.config.interceptor.BeginAfterHandleRequestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addWebRequestInterceptor(new WebRequestInterceptor());
        registry.addInterceptor(new BeginAfterHandleRequestFilter());
    }
}
