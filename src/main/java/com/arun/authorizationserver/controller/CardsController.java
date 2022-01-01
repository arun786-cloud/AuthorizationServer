package com.arun.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun on 1/1/22
 */
@RestController
public class CardsController {

    @GetMapping("/v1/cards")
    public String getCardsDetails(String input) {
        return "Here are the card details";
    }
}
