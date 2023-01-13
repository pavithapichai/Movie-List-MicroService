package com.example.ratingsdataservice.resources;

import com.example.ratingsdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/ratings-data")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){

        return new Rating(movieId,4);

    }
}
