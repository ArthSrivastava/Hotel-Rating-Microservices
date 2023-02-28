package com.userservice.userservice.services.impl;

import com.userservice.userservice.entities.Hotel;
import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.exceptions.ResourceNotFoundException;
import com.userservice.userservice.external.services.HotelService;
import com.userservice.userservice.repositories.UserRepo;
import com.userservice.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RestTemplate restTemplate;
    private final Logger logger;

    private final HotelService hotelService;

    public UserServiceImpl(UserRepo userRepo, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
        this.logger = LoggerFactory.getLogger(UserServiceImpl.class);
    }

    @Override
    public User createUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        return userRepo.save(user);
    }

    @Override
    public User getUserById(String id) {
        //http://localhost:8083/ratings/users/id
        Rating[] userRatings =
                restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + id, Rating[].class);
        List<Rating> ratings = Arrays.asList(userRatings);
        logger.info("Ratings: {}", ratings);

        List<Rating> finalRatings = ratings.stream().map(rating -> {
            //call for hotels api

            //Using RestTemplate
//            ResponseEntity<Hotel> forEntity = restTemplate
//                    .getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set hotel to rating
            rating.setHotel(hotel);
            //return rating
            return rating;
        }).toList();

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setRatings(finalRatings);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
