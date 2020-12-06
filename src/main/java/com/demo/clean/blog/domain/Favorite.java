package com.demo.clean.blog.domain;

import com.demo.clean.accounting.domain.Person;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "post_id"}))
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne private Post post;

    @OneToOne
    private Person person;

    @Column(name = "favored_at")
    @CreatedDate
    private LocalDateTime favoredAt;

    public Favorite(Post post, Person person) {
        this.post = post;
        this.person = person;
    }
}
