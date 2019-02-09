package org.springcourse.project.springsocialnetwork.web;

import org.springcourse.project.springsocialnetwork.model.User;
import org.springcourse.project.springsocialnetwork.service.SecurityService;
import org.springcourse.project.springsocialnetwork.service.UserService;
import org.springcourse.project.springsocialnetwork.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JspController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;


    @GetMapping(value = {"/", "/index"})
    public String welcome(Model model) {
        if (securityService.getLoggedInName() != null) {
            return "index";
        }
        return "login";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping(value = "/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.createUser(userForm);

        securityService.login(userForm.getName(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

}
