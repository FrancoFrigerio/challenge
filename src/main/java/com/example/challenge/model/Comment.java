package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
