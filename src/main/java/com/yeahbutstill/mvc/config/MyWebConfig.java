package com.yeahbutstill.mvc.config;

import com.yeahbutstill.mvc.config.interceptor.SessionInterceptor;
import com.yeahbutstill.mvc.config.resolver.PartnerArgumentResolver;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.List;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    private final SessionInterceptor sessionInterceptor;

    private final PartnerArgumentResolver partnerArgumentResolver;

    public MyWebConfig(SessionInterceptor sessionInterceptor, PartnerArgumentResolver partnerArgumentResolver) {
        this.sessionInterceptor = sessionInterceptor;
        this.partnerArgumentResolver = partnerArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/user/*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(partnerArgumentResolver);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(2L))
                .setReadTimeout(Duration.ofSeconds(2L))
                .build();
    }

}
