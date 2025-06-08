package myservice.service;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import java.util.List;

@Controller
public class PetsController {
    WebClient petWebClient;

    PetsController(WebClient.Builder.builder) {
        this.petWebClient = builder.baseUrl("http://pet-service").build();
        this.ownerWebClient = builder.baseUrl("http://owner-service").build();
    }

    @QueryMapping
    Flux<Pet> pets() {
        return petWebClient.get()
                .uri("/pets")
                .retrieve()
                .bodyToFlux(Pet.class);
    }

    @SchemaMapping
    Mono<Person> owner(Pet pet) {
        return ownerWebClient.get()
                .uri("/owner/{id}", pet.ownerId())
                .retrieve()
                .bodyToMono(Person.class);
    }
}