package com.demo.clean.blog.post.infra.persistence.repositories;

import com.demo.clean.blog.post.domain.Post;
import com.demo.clean.blog.post.domain.PostTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p where p.author.id = ?1 and p.title = ?2")
    Optional<Post> findByAuthorIdAndPostId(Long authorId, PostTitle postTitle);
}
