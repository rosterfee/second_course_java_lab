package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;

    @Column(unique = true)
    private String model;

    private Integer price;

    private String powers;

    private String engine;

    public enum Transmission {
        AUTOMATIC, MECHANICAL, ROBOT
    }

    private Transmission transmission;

    private String maxSpeed;

    private String description;

    private String racing;

    @OneToMany(mappedBy = "car")
    private List<CarImage> images;

}
