package com.example.challenge.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @OneToOne
    private Address address;

    private String phone;

    private String website;

    @OneToOne
    private Company company;


}
