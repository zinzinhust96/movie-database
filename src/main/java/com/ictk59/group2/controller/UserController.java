package com.ictk59.group2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ictk59.group2.domain.Movie;
import com.ictk59.group2.domain.User;
import com.ictk59.group2.service.SecurityService;
import com.ictk59.group2.service.UserService;
import com.ictk59.group2.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "auth/registration";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, RedirectAttributes attributes) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        userService.save(user);
        attributes.addFlashAttribute("user", user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(@ModelAttribute("user") User user ,Model model) {
    	model.addAttribute("user", user);
    	model.addAttribute("movie", new Movie());
        return "auth/welcome";
    }
}