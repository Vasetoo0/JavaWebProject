package softuni.javaweb.springproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.javaweb.springproject.statistics.interceptors.StatsInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private StatsInterceptor statsInterceptor;

    @Autowired
    public WebConfig(softuni.javaweb.springproject.statistics.interceptors.StatsInterceptor statsInterceptor) {
        this.statsInterceptor = statsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
    }
}
