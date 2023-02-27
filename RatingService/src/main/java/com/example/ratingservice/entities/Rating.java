package com.example.ratingservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    private String id;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
