package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.dto.RegistrationForm;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phone;

    private String avatar;

    private String confirmCode;

    @OneToMany(mappedBy = "author")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "author")
    private List<Order> orders;

}
