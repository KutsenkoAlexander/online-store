package ua.kay.monolit.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
