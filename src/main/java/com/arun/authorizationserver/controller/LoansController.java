package com.arun.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun on 1/1/22
 */
@RestController
public class LoansController {

    @GetMapping("/v1/loan")
    public String getLoanDetails(String input) {
        return "Here are the loan Details";
    }
}
