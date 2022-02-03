package com.example.springdemo01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/nasa")
public class NASA {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public void get() {
        APIClientNASA api = new APIClientNASA();

        api.getAPOD();
    }
}
