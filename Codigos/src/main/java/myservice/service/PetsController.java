package myservice.service;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PetsController {

    @QueryMapping
    public List<Pet> pets() {
        return List.of(
            new Pet("Fido", "brown"),
            new Pet("Rex", "black"),
            new Pet("Luna", "white")
        );
    }
}
