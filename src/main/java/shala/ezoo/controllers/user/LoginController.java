package shala.ezoo.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.model.User;

@Controller
public class LoginController {

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String showHome() {
        return "index";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
