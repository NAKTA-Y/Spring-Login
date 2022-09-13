package hello.itemservice.config;

import hello.itemservice.web.filter.LogFilter;
import hello.itemservice.web.filter.LoginCheckFilter;
import hello.itemservice.web.interceptor.LogInterceptor;
import hello.itemservice.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public FilterRegistrationBean logFilter() {
//
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new LogFilter());
//        registrationBean.setOrder(1);
//        registrationBean.addUrlPatterns("/*");
//
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean loginCheckFilter() {
//
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new LoginCheckFilter());
//        registrationBean.setOrder(2);
//        registrationBean.addUrlPatterns("/*");
//
//        return registrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/members/add", "/login", "logout",
                        "/css/**", "/*.ico", "/error"
                );
    }
}
