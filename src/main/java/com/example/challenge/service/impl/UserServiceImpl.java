package com.example.challenge.service.impl;

import com.example.challenge.dto.AlbumDto;
import com.example.challenge.model.Album;
import com.example.challenge.model.Photo;
import com.example.challenge.model.User;
import com.example.challenge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.example.challenge.util.Constants.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Value("error.album.not-available")
    private String error_message;

    @Value("rango.disponible")
    private String rangoDisponible;

    @Value("page.content")
    private String pageContent;

    @Value("album.actual")
    private String albumActual;

    @Value("next.album")
    private String nextAlbum;

    @Value("previous.album")
    private String previousAlbum;

    @Value("is.the.last.album")
    private String lastAlbumMessage;

    @Value("is.the.first.album")
    private String firstAlbumMessage;


    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<Object> usersFromApi = restTemplate.getForObject(JSON_PLACE_HOLDER_URL.concat(PATH_USERS),List.class);
        usersFromApi.forEach(user->{
            users.add(modelMapper.map(user,User.class));
        });
    return users;
    }


    @Override
    public List<Photo> getPhotos(Long id) {
        List<Photo> listPhotos= new ArrayList<>();
        List<Object> listAlbumsFromApi = restTemplate.getForObject(JSON_PLACE_HOLDER_URL.concat(PATH_USERS+id+PATH_ALBUMS),List.class);
            listAlbumsFromApi.forEach(album -> {
            Album albumMap = modelMapper.map(album,Album.class);
                List<Object> listPhotosFromApi= restTemplate.getForObject(JSON_PLACE_HOLDER_URL.concat(PATH_ALBUMS+albumMap.getId()+PATH_PHOTOS),List.class);
                listPhotosFromApi.forEach(element ->{
                    listPhotos.add(modelMapper.map(element,Photo.class));
                });
            });

    return listPhotos;
    }

    @Override
    public Map<String, Object> getPagination(Long id, Integer size, Long album) {
        Map<String,Object> resp = new HashMap<>();
        List<Photo> listPhotos= new ArrayList<>();
        List<Object> listAlbumsFromApi = restTemplate.getForObject(JSON_PLACE_HOLDER_URL.concat(PATH_USERS+id+PATH_ALBUMS),List.class);
        List<Object> listPhotosFromApi= restTemplate.getForObject(JSON_PLACE_HOLDER_URL.concat(PATH_ALBUMS+album+PATH_PHOTOS),List.class);
        List<AlbumDto> listDtos = new ArrayList<>();
        listAlbumsFromApi.forEach(e->{
            listDtos.add(modelMapper.map(e,AlbumDto.class));
        });
        if(album > listDtos.get(9).getId() || album< listDtos.get(0).getId()){
            resp.put("error" , messageSource.getMessage(error_message,null, Locale.getDefault()));
            resp.put(messageSource.getMessage(rangoDisponible,null,Locale.getDefault()),+listDtos.get(0).getId()+", hasta: "+ listDtos.get(9).getId());
            return resp;
        }
            listPhotosFromApi.forEach(element ->{
                    listPhotos.add(modelMapper.map(element,Photo.class));
            });
        resp.put(messageSource.getMessage(pageContent,null,Locale.getDefault()),listPhotos);
        resp.put(messageSource.getMessage(albumActual,null,Locale.getDefault()),album);
        resp.put(messageSource.getMessage(nextAlbum,null,Locale.getDefault()),album>=listDtos.get(9).getId()?(messageSource.getMessage(lastAlbumMessage,null,Locale.getDefault())):(album+1));
        resp.put(messageSource.getMessage(previousAlbum,null,Locale.getDefault()),album<=listDtos.get(0).getId()?(messageSource.getMessage(firstAlbumMessage,null,Locale.getDefault())):(album-1));
        return resp;
    }

}
