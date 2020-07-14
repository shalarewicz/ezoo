package shala.ezoo.controllers.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.user.UserDao;
import shala.ezoo.model.Role;
import shala.ezoo.model.User;

@Controller
public class AddUserController {

    @Autowired
    private UserDao dao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    @Qualifier("userValidator")
    private Validator validator;
    
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registrationForm";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.POST) 
    public String saveUser(@Valid @ModelAttribute User user, Errors errors, Model model, HttpSession session) {
        
        // TODO Check that passwords match
        // TODO Check email uniqueness
        // TODO Verify email address make use of enabled column
        ValidationUtils.invokeValidator(validator, user, errors);
        if (errors.hasErrors()) {
            model.addAttribute("message", "Please correct the specified fields");
        } else {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.addRole(Role.ROLE_USER);
                if (dao.saveUser(user)) {
                    session.setAttribute("message", "Registration Successful!");
                    session.setAttribute("messageClass", "alert-success");
                    return "redirect:/login";
                } else {
                    //TODO Build a custom validator to check for unique username
                    session.setAttribute("message", "Username: " + user.getUsername() + " is already in use");
                }
            }  catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                session.setAttribute("message",  "There was a problem registering at this time.");
            } 
            
        }
        user.setPassword("");
        user.setConfirmPassword("");
        session.setAttribute("messageClass", "alert-danger");  
        return "registrationForm";
        
        
        
    }
}
