package com.example.challenge.service;

import com.example.challenge.model.Photo;
import com.example.challenge.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();

    List<Photo> getPhotos(Long id);

    Map<String,Object>getPagination(Long id, Integer size, Long album);
}
