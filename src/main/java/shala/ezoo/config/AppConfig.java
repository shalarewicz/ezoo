package shala.ezoo.config;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import shala.ezoo.security.MethodSecurityConfig;
import shala.ezoo.security.WebSecurityConfig;

public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {HibernateConfig.class, Config.class, WebSecurityConfig.class, /*MethodSecurityConfig.class*/};
    }

    @Override protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {ServletConfig.class};
    }

    @Override protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("C:\\Users\\shala\\Documents\\CS\\revature\\eZoo\\docs\\", 1073741824, 1073741824, 0));
    }
    

}
