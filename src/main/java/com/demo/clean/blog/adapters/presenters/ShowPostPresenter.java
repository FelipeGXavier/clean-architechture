package com.demo.clean.blog.adapters.presenters;

import com.demo.clean.blog.adapters.ShowCommentResponse;
import com.demo.clean.blog.adapters.ShowPostResponse;
import com.demo.clean.blog.domain.Post;
import com.demo.clean.shared.Presenter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowPostPresenter implements Presenter<Post, ShowPostResponse> {

    @Override
    public ShowPostResponse present(Post response) {
        var relatedLinks =
                response.getLinks().stream().map(Object::toString).collect(Collectors.toList());
        var author = response.getAuthor().getLogin();
        var postedAt = response.getPostedAt().toString();
        var countFavorites = response.getCountFavorites();
        var countComments = response.getCountComments();
        var body = response.getBody().toString();
        List<ShowCommentResponse> comments = new ArrayList<>();
        response.getComments()
                .forEach(
                        comment -> {
                            comments.add(
                                    ShowCommentResponse.builder()
                                            .body(comment.getBody().toString())
                                            .author(comment.getPerson().getLogin())
                                            .commentedAt(comment.getCommentedAt().toString())
                                            .build());
                        });
        return ShowPostResponse.builder()
                .postedAt(postedAt)
                .author(author)
                .comments(comments)
                .relatedLinks(relatedLinks)
                .countComments(countComments)
                .countFavorites(countFavorites)
                .content(body)
                .build();
    }
}
