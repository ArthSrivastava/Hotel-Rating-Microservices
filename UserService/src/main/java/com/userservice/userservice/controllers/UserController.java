package com.userservice.userservice.controllers;

import com.userservice.userservice.entities.User;
import com.userservice.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger;
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("http://localhost:8081/users/" + savedUser.getId())).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "hotelRatingBreakerFallback")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    public ResponseEntity<User> hotelRatingBreakerFallback(String id, Exception ex) {
        logger.info("Fallback method running: {}", ex.getMessage());
        User dummy = User.builder().name("Dummy").about("Dummy user is shown since one of the service" +
                "is down").email("dummy@gmail.com").build();
        return new ResponseEntity<>(dummy, HttpStatus.BAD_REQUEST);
    }
}
