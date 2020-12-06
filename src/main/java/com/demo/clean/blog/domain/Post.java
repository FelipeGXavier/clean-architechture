package com.demo.clean.blog.domain;

import com.demo.clean.blog.infra.persistence.converters.PostBodyConverter;
import com.demo.clean.blog.infra.persistence.converters.PostTitleConverter;
import com.demo.clean.accounting.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = PostBodyConverter.class)
    private PostBody body;

    @Convert(converter = PostTitleConverter.class)
    @Column(unique = true, nullable = false)
    private PostTitle title;

    @Column(name = "posted_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedAt;

    @Column(name = "external_id")
    private String externalId = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostLink> links = new HashSet<>();

    @ManyToOne private Person author;

    public Post(PostBody body, PostTitle title, Person author) {
        this.body = body;
        this.title = title;
        this.author = author;
    }

    public void addLinks(Set<PostLink> links) {
        this.links.addAll(links);
    }

    public void addLink(PostLink link) {
        this.links.add(link);
    }

    public void updateLink(PostLink oldPostLink, PostLink newPostLink) {
        this.links.remove(oldPostLink);
        this.links.add(newPostLink);
    }
}
