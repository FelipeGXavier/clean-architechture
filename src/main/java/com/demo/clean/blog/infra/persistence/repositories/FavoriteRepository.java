package com.demo.clean.blog.infra.persistence.repositories;

import com.demo.clean.blog.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query(value = "select f from Favorite f where f.person.id = ?1 and f.post.externalId = ?2")
    Optional<Favorite> findByPersonIdAndPostId(Long userId, String postId);
}
