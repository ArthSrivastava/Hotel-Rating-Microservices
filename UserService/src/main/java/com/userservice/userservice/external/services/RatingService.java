package com.userservice.userservice.external.services;

import com.userservice.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //create Rating
    @PostMapping("/ratings")
    public Rating createRating(Rating rating);
}
