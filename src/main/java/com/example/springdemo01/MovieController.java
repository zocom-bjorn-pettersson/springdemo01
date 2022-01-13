package com.example.springdemo01;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/movies")
public class MovieController {
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Movie getGreeting(@RequestBody Movie movie) {
        return movie;
    }
}
