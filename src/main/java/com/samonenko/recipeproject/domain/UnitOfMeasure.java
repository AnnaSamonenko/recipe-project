package com.samonenko.recipeproject.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String type;
}
