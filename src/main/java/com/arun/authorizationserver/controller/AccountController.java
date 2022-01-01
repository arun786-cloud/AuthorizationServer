package com.arun.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun on 1/1/22
 */
@RestController
public class AccountController {

    @GetMapping("/v1/account")
    public String getAccountDetails(String input) {
        return "Here are the account details";
    }
}
