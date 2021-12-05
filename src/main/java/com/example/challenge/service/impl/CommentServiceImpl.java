package com.example.challenge.service.impl;

import com.example.challenge.exception.NotContentException;
import com.example.challenge.model.Comment;
import com.example.challenge.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    RestTemplate restTemplate = new RestTemplate();

    private final static String url = "https://jsonplaceholder.typicode.com/comments";



    @Override
    public List<Comment> getComments(String name, String email) {

        List<Comment> commentList = new ArrayList<>();
        List<Object> listFromApi = restTemplate.getForObject(url,List.class);
        listFromApi.forEach(comment ->{
            Comment comment1 = modelMapper.map(comment,Comment.class);
                if(comment1.getName().equals(name)||comment1.getEmail().equals(email)){
                    commentList.add(comment1);
                }
        });
        if(commentList.isEmpty()){
            throw new NotContentException("Debe ingresar el campo name o email para filtrar");
        }else{
            return commentList;
        }

    }
}
