package com.example.springdemo01;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class APIClientNASA {

    private String myNasaApiKey = "..."; // Do not check your key into version control, the proper way of
                                         // storing API keys is by using a configuration file or the
                                         // properties file and not checking it into VCS.
                                         // You can also ask for the key from the user in the client.

    public void getAPOD() {
        Flux<NASAAPOD> podFlux = WebClient
                .create("https://api.nasa.gov/planetary/apod?api_key=" + myNasaApiKey)
                .get()
                .retrieve()
                .bodyToFlux(NASAAPOD.class);

        List<NASAAPOD> apodList = podFlux
                .collect(Collectors.toList())
                .share().block();

        for (NASAAPOD apod : apodList) {
            System.out.println(apod.copyright);
        }
    }
}
