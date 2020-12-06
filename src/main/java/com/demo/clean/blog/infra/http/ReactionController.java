package com.demo.clean.blog.infra.http;

import com.demo.clean.blog.adapters.FavoritePostRequest;
import com.demo.clean.blog.adapters.FollowPersonRequest;
import com.demo.clean.blog.application.SocialActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/reactions")
public class ReactionController {

    private final SocialActions reactionsUseCase;

    @Autowired
    public ReactionController(SocialActions reactionsUseCase) {
        this.reactionsUseCase = reactionsUseCase;
    }

    @PostMapping("/follow")
    public ResponseEntity<Void> follow(@RequestBody FollowPersonRequest request){
        this.reactionsUseCase.followPerson(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/favorite")
    public ResponseEntity<Void> favorite(@RequestBody FavoritePostRequest request){
        this.reactionsUseCase.favoritePost(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
