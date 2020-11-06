package ru.itis.javalab.homework;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isWorker;

    public Account() { }

    public Account(Long id, String firstName, String lastName, boolean isWorker) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isWorker = isWorker;
    }

}

