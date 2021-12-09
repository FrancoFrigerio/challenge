package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lat;
    private String lng;
}
