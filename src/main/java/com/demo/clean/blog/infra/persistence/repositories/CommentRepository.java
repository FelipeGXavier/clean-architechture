package com.demo.clean.blog.infra.persistence.repositories;

import com.demo.clean.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
