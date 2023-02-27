package com.example.ratingservice.repositories;

import com.example.ratingservice.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepo extends MongoRepository<Rating, String> {
    List<Rating> findAllByUserId(String userId);
    List<Rating> findAllByHotelId(String hotelId);
}
