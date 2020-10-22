package ru.itis.javalab.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Product {
    private Long id;
    private String title;
    private Double price;
    private User owner;
}

