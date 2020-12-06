package com.demo.clean.blog.domain;

import com.demo.clean.accounting.domain.Person;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followed_id"}))
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "followed_at")
    @CreatedDate
    private LocalDateTime followedAt;

    @OneToOne private Person follower;
    @OneToOne private Person followed;

    public Follow(Person follower, Person followed) {
        if (followed.getId().equals(follower.getId())) {
            throw new IllegalArgumentException("You can't follow yourself");
        }
        this.follower = follower;
        this.followed = followed;
    }
}
