package com.samonenko.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Getter
    @Setter
    private String type;

}
