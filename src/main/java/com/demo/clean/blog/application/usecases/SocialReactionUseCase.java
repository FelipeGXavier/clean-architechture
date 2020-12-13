package com.demo.clean.blog.application.usecases;

import com.demo.clean.accounting.infra.persistence.repositories.PersonRepository;
import com.demo.clean.blog.adapters.FavoritePostRequest;
import com.demo.clean.blog.adapters.FollowPersonRequest;
import com.demo.clean.blog.application.SocialActions;
import com.demo.clean.blog.domain.Favorite;
import com.demo.clean.blog.domain.Follow;
import com.demo.clean.blog.infra.persistence.repositories.FavoriteRepository;
import com.demo.clean.blog.infra.persistence.repositories.FollowRepository;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import com.demo.clean.shared.DomainException;
import com.demo.clean.shared.LoadLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialReactionUseCase implements SocialActions {

    private final FollowRepository followRepository;
    private final FavoriteRepository favoriteRepository;
    private final PersonRepository personRepository;
    private final PostRepository postRepository;


    @Autowired
    public SocialReactionUseCase(
            FollowRepository followRepository,
            FavoriteRepository favoriteRepository,
            PersonRepository personRepository, PostRepository postRepository) {
        this.followRepository = followRepository;
        this.favoriteRepository = favoriteRepository;
        this.personRepository = personRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void followPerson(FollowPersonRequest request) {
        var followFromDatabase = this.followRepository.findByFollowedIdAndFollowerId(request.getFollowedId(), request.getUserId());
        if (followFromDatabase.isPresent()) {
            throw new DomainException("You already are a follow");
        }
        var follower = LoadLoggedUser.load(request.getUserId());
        var followed = this.personRepository.findById(request.getFollowedId()).orElseThrow(() -> {
            throw new DomainException("User not found");
        });
        var follow = new Follow(follower, followed);
        this.followRepository.save(follow);
    }

    @Override
    public void favoritePost(FavoritePostRequest request) {
        var favoriteFromDatabase = this.favoriteRepository.findByPersonIdAndPostId(request.getUserId(), request.getPostExternalId());
        if (favoriteFromDatabase.isPresent()) {
            throw new DomainException("You already favored this post");
        }
        var post = this.postRepository.findByExternalId(request.getPostExternalId()).orElseThrow(() -> {
            throw new DomainException("Post not found");
        });
        var loggedUser = LoadLoggedUser.load(request.getUserId());
        var favorite = new Favorite(post, loggedUser);
        this.favoriteRepository.save(favorite);
    }
}
