package ua.kay.monolit.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Aleksandr on 22.05.2016.
 */
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
