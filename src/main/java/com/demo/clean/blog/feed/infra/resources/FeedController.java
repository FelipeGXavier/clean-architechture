package com.demo.clean.blog.feed.infra.resources;

import com.demo.clean.blog.feed.application.FeedActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/feed")
public class FeedController {

    private final FeedActions feedUseCase;

    @Autowired
    public FeedController(FeedActions feedUseCase) {
        this.feedUseCase = feedUseCase;
    }

    @GetMapping
    public ResponseEntity<?> dummy(@RequestParam(value = "page", defaultValue = "0") int page) {
        return new ResponseEntity<>(this.feedUseCase.showMostRecentPosts(page), HttpStatus.OK);
    }
}
