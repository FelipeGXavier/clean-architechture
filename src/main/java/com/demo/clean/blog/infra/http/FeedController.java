package com.demo.clean.blog.infra.http;

import com.demo.clean.blog.application.FeedActions;
import com.demo.clean.blog.application.ShowPostDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/feed")
public class FeedController {

    private final FeedActions feedUseCase;
    private final ShowPostDetails showPostUseCase;

    @Autowired
    public FeedController(FeedActions feedUseCase, ShowPostDetails showPostUseCase) {
        this.feedUseCase = feedUseCase;
        this.showPostUseCase = showPostUseCase;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page) {
        return new ResponseEntity<>(this.feedUseCase.showMostRecentPosts(page), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> show(@PathVariable String slug) {
        return new ResponseEntity<>(this.showPostUseCase.execute(slug), HttpStatus.OK);
    }
}
