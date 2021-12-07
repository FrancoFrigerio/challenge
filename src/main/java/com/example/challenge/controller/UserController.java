package com.example.challenge.controller;


import com.example.challenge.service.CommentService;
import com.example.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getUserPhotos(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(userService.getPhotos(id));
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<?>getPage(@PathVariable(name="id")Long id,@RequestParam(name = "album",required = false)Long page){
        return ResponseEntity.ok().body(userService.getPagination(id,5,page));
    }

}
