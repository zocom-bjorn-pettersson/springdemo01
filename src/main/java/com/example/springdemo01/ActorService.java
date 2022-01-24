package com.example.springdemo01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActorService {
    ArrayList<Actor> actors;
    int latestActorID;

    public ActorService() {
        actors = new ArrayList<>();
        latestActorID = 0;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public Actor createActor(Actor newActor) {
        latestActorID++;
        newActor.setId(latestActorID);
        actors.add(newActor);
        return newActor;
    }
}
