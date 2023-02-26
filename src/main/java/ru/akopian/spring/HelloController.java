package ru.akopian.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/spring")
    public String sayHello() {
        return "hello_world";
    }
}
