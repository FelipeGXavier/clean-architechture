package com.demo.clean.accounting.domain;

import com.demo.clean.accounting.infra.persistence.converters.PersonEmailConverter;
import com.demo.clean.blog.domain.Favorite;
import com.demo.clean.blog.domain.Follow;
import com.demo.clean.blog.domain.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = PersonEmailConverter.class)
    private PersonEmail email;

    private String login;
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "followed")
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<Favorite> favorites = new ArrayList<>();

    public Person(PersonEmail email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public int totalPosts() {
        return this.posts.size();
    }

    public int totalFavorites() {
        return this.favorites.size();
    }

    public Optional<Post> mostRecentPost() {
        if(this.posts.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(Collections.max(this.posts, Comparator.comparing(Post::getPostedAt)));
    }

    public Optional<Post> mostRatedPost() {
        if(this.posts.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(Collections.max(this.posts, Comparator.comparing(Post::getCountFavorites)));
    }

    public int totalFollowers() {
        var followers = 0;
        for (Follow follow : this.followers) {
            if (follow.getFollowed().getId().equals(this.id)) {
                followers++;
            }
        }
        return followers;
    }

    @Override
    public String toString() {
        return "Person=" + this.id;
    }
}
