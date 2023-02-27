package com.example.ratingservice.services.impl;

import com.example.ratingservice.entities.Rating;
import com.example.ratingservice.repositories.RatingRepo;
import com.example.ratingservice.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;

    public RatingServiceImpl(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userId) {
        return ratingRepo.findAllByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelId) {
        return ratingRepo.findAllByHotelId(hotelId);
    }
}
