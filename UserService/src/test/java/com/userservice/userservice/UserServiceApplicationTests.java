package com.userservice.userservice;

import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RatingService ratingService;

    @Test
    public void checkCreateRating() {
        Rating rating = Rating.builder().rating(9).userId("")
                .hotelId("").feedback("Another test").build();
        Rating savedRating = ratingService.createRating(rating);
        System.out.println(savedRating);
    }

}
