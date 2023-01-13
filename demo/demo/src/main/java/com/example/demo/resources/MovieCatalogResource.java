package com.example.demo.resources;

import com.example.demo.models.CatalogItem;
import com.example.demo.models.Movie;
import com.example.demo.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.catalog.Catalog;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder builder;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("456",5)
        );


       return ratings.stream().map( rating -> {
           //Movie mov = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
           Movie block = builder.build()
                   .get()
                   .uri("http://localhost:8082/movies/" + rating.getMovieId())
                   .retrieve()
                   .bodyToMono(Movie.class)
                   .block();
           return new CatalogItem(block.getMovieId(), "desc", rating.getRatings());


       }).collect(Collectors.toList());
       // return Collections.singletonList(new CatalogItem("Avatar","Avarter desc",5));
    }
}
