package ua.kay.monolit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutingController {

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirectIndex() {
        return "forward:/";
    }

    @RequestMapping(value = "/catalog/{[path:[^\\.]*}")
    public String redirectCategory() {
        return "forward:/";
    }

    @RequestMapping(value = "/catalog/products/{[path:[^\\.]*}")
    public String redirectListProduct() {
        return "forward:/";
    }

    @RequestMapping(value = "/catalog/products/product/{[path:[^\\.]*}")
    public String redirectProduct() {
        return "forward:/";
    }

    @RequestMapping(value = "/admin/{[path:[^\\.]*}")
    public String redirectAdmin() {
        return "forward:/";
    }

}
