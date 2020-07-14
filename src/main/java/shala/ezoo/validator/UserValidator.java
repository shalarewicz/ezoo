package shala.ezoo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shala.ezoo.dao.user.UserDao;
import shala.ezoo.model.User;

@Component
public class UserValidator implements Validator {
    
    @Autowired
    private UserDao dao;

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        //TODO Are these necessary
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        User user = (User) target;

        if (dao.existsUsername(user.getUsername())) {
            errors.rejectValue("username", "unique", "Username must be unique");
        }
        
        if (dao.existsEmail(user.getEmail())) {
            errors.rejectValue("email", "unique", "Email must be unique");
        }
               
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "match", "Passwords do not match");
        }        
    }

}
