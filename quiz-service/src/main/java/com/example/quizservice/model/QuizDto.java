package com.example.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    private String CategoryName;
    private Integer numQ;
    private String difficulty_Level;
    private String title;
}
