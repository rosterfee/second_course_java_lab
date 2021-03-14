package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIME)
    private Date date;

    private Integer likes;

    private Integer dislikes;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

}
