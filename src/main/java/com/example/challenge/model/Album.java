package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class Album {


    private Long userId;
    private Long id;
    private String title;

}
