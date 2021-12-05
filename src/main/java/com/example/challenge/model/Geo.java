package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class Geo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lat;
    private String lng;
}
