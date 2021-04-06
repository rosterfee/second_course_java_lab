package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User implements Serializable {

    private static final long serialVersionUID = 8014933619964135196L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String hashPassword;

    private String email;

    private String phone;

    private String avatar;

    private String confirmCode;

    @OneToMany(mappedBy = "author")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "author")
    private List<Order> orders;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public enum Status {
        CONFIRMED, NOT_CONFIRMED
    }

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public boolean isBanned() {
        return state == State.BANNED;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isConfirmed() {
        return status == Status.CONFIRMED;
    }

}
