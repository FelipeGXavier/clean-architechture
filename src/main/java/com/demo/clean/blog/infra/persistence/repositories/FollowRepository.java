package com.demo.clean.blog.infra.persistence.repositories;

import com.demo.clean.blog.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query(value = "select f from Follow f where f.followed.id = ?1 and f.follower.id = ?2")
    Optional<Follow> findByFollowedIdAndFollowerId(Long followedId, Long followerId);
}
