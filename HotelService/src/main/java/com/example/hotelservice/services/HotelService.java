package com.example.hotelservice.services;

import com.example.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(String id);
    Hotel createHotel(Hotel hotel);
}
