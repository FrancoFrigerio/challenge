package com.example.challenge.service.impl;

import com.example.challenge.dto.AlbumDto;
import com.example.challenge.model.Album;
import com.example.challenge.model.Photo;
import com.example.challenge.model.User;
import com.example.challenge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    RestTemplate restTemplate = new RestTemplate();

    private String url = "https://jsonplaceholder.typicode.com";

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<Object> usersFromApi = restTemplate.getForObject(url.concat("/users"),List.class);
        usersFromApi.forEach(user->{
            users.add(modelMapper.map(user,User.class));
        });
    return users;
    }


    @Override
    public List<Photo> getPhotos(Long id) {
        List<Photo> listPhotos= new ArrayList<>();
        List<Object> listAlbumsFromApi = restTemplate.getForObject(url.concat("/users/"+id+"/albums"),List.class);
            listAlbumsFromApi.forEach(album -> {
            Album albumMap = modelMapper.map(album,Album.class);
                List<Object> listPhotosFromApi= restTemplate.getForObject(url.concat("/albums/"+albumMap.getId()+"/photos"),List.class);
                listPhotosFromApi.forEach(element ->{
                    listPhotos.add(modelMapper.map(element,Photo.class));
                });
            });

    return listPhotos;
    }



}
