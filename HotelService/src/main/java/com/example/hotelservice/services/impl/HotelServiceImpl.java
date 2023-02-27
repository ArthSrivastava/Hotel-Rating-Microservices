package com.example.hotelservice.services.impl;

import com.example.hotelservice.entities.Hotel;
import com.example.hotelservice.exceptions.ResourceNotFoundException;
import com.example.hotelservice.repositories.HotelRepo;
import com.example.hotelservice.services.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;
    public HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No hotel found with id " + id));
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        Hotel savedHotel = hotelRepo.save(hotel);
        return savedHotel;
    }
}
