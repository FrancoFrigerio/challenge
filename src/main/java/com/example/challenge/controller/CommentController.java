package com.example.challenge.controller;


import com.example.challenge.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<?> getComments(@RequestParam(name = "name",required = false)String name, @RequestParam(name = "email",required = false)String email){
        return ResponseEntity.ok().body(commentService.getComments(name,email));
    }
}
