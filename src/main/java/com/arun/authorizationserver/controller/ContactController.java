package com.arun.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun on 1/1/22
 */
@RestController
public class ContactController {

    @GetMapping("/v2/contact")
    public String saveContactInquiryDetails(String input) {
        return "Inquiry details saved in the db";
    }
}
