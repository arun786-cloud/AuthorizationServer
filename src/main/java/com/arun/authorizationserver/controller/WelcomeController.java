package com.arun.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun on 1/1/22
 */
@RestController
public class WelcomeController {

    @GetMapping("/v1/hello")
    public String sayHello() {
        return "say hello";
    }
}
