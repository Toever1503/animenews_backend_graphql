package animenews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Properties;

@Configuration
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(465);

        mailSender.setUsername("0845954f4600a5");
        mailSender.setPassword("8252158432ddf2");

        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.smtp.auth", "true");

        return mailSender;
    }

//    @Bean
//    public WebMvcConfigurer configurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000/")
//                        .allowedHeaders("Access-Control-Allow-Origin: *")
//                        .allowCredentials(true)
//                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
//            }
//        };
//    }

    @Bean
    public CorsConfigurationSource  corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://192.168.72.101:3000", "http://192.168.1.44:3000")); //or add * to allow all origins
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); //to set allowed http methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin")); //to set allowed headers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
