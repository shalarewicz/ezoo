package shala.ezoo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


//@Configuration
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        //TODO
////        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//        return expressionHandler;
//    }
}
