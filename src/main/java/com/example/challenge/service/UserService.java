package com.example.challenge.service;

import com.example.challenge.model.Photo;
import com.example.challenge.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    List<Photo> getPhotos(Long id);
}
