package ua.kay.monolit.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UsersController {
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal ) {
//        String name = principal.getName(); //get logged in username
//        model.addAttribute("username", name);
        System.out.println(principal);
        return "hello";
    }
}
