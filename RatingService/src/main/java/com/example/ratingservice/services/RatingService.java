package com.example.ratingservice.services;

import com.example.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getAllRatings();
    List<Rating> getAllRatingsByUserId(String userId);
    List<Rating> getAllRatingsByHotelId(String hotelId);
}
