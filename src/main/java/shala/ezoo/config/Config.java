package shala.ezoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import shala.ezoo.aspect.LoggingAspect;
import shala.ezoo.logging.DatabaseLogger;
import shala.ezoo.logging.LoggerDefault;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages= {"shala.ezoo"}, excludeFilters = {@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
public class Config {
    
    @Bean
    public DatabaseLogger databaseLogger() {
        return new LoggerDefault();
    }
    
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
    
}
