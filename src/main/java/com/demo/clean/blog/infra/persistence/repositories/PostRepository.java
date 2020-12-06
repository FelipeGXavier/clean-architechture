package com.demo.clean.blog.infra.persistence.repositories;

import com.demo.clean.blog.domain.Post;
import com.demo.clean.blog.domain.PostTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p where p.author.id = ?1 and p.title = ?2")
    Optional<Post> findByAuthorIdAndPostId(Long authorId, PostTitle postTitle);

    @Query(value = "select p from Post p where p.externalId = ?1")
    Optional<Post> findByExternalId(String externalId);
}
