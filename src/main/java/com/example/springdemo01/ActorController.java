package com.example.springdemo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/actors")
public class ActorController {
    private ActorService actorService;

    // Dependency Injection
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Actor> list() {
        return actorService.getActors();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor newActor = actorService.createActor(actor);

        return new ResponseEntity<>(newActor, HttpStatus.CREATED);
    }
}
