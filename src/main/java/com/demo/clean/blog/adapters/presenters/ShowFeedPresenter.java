package com.demo.clean.blog.adapters.presenters;

import com.demo.clean.blog.adapters.PostResponse;
import com.demo.clean.blog.adapters.ShowFeedResponse;
import com.demo.clean.blog.domain.Post;
import com.demo.clean.shared.Presenter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShowFeedPresenter implements Presenter<Page<Post>, ShowFeedResponse> {

    @Override
    public ShowFeedResponse present(Page<Post> response) {
        var showFeedResponse = new ShowFeedResponse();
        showFeedResponse.setTotal(response.getTotalPages());
        showFeedResponse.setCurrent(response.getNumber());
        var posts = showFeedResponse.getResult();
        response.getContent()
                .forEach(
                        content ->
                                posts.add(
                                        PostResponse.builder()
                                                .title(content.getTitle().toString())
                                                .minifiedBody(
                                                        this.minifyBody(
                                                                content.getBody().toString()))
                                                .author(content.getAuthor().getLogin())
                                                .postedAt(content.getPostedAt().toString())
                                                .relatedLinks(
                                                        content.getLinks().stream()
                                                                .map(Object::toString)
                                                                .collect(Collectors.toList()))
                                                .build()));
        showFeedResponse.setResult(posts);
        return showFeedResponse;
    }

    private String minifyBody(String body) {
        var tokens = body.split(" ");
        var slops = 20;
        if (tokens.length < slops) {
            return body;
        }
        var result = new StringBuilder();
        for (var i = 0; i < slops; i++) {
            result.append(tokens[i]);
            if (i != slops - 1) {
                result.append(" ");
            }
        }
        result.append("...");
        return result.toString().trim();
    }
}
