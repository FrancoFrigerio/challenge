package com.example.challenge.service;

import com.example.challenge.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments(String name, String email);
}
