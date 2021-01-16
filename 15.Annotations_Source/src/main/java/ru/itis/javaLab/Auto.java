package ru.itis.javaLab;

@HtmlFormAnnotation(method = "post", action = "/auto")
public class Auto {

    @HtmlInputAnnotation(name = "mark", placeholder = "Введите марку автомобиля")
    private String mark;

    @HtmlInputAnnotation(name = "model", placeholder = "Введите модель автомобиля")
    private String model;

    @HtmlInputAnnotation(type = "number", name = "price", placeholder = "Введите стоимость автомобиля")
    private String price;

}
