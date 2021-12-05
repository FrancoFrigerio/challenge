package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Photo {

    private Long albumId;
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
