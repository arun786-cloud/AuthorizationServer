package com.arun.authorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun on 1/1/22
 */
@RestController
public class NoticeController {

    @GetMapping("/v2/notices")
    public String getNotices(String input) {
        return "here are the notices from the DB";
    }
}
