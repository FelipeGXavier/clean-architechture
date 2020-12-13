package com.demo.clean.accounting.adapter.presenter;

import com.demo.clean.accounting.adapter.DashboardResponse;
import com.demo.clean.accounting.adapter.PostPreview;
import com.demo.clean.accounting.domain.Person;
import com.demo.clean.blog.domain.Post;
import com.demo.clean.shared.DomainException;
import com.demo.clean.shared.Presenter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDashboardPresenter implements Presenter<Optional<Person>, DashboardResponse> {

    @Override
    public DashboardResponse present(Optional<Person> response) {
        if (response.isEmpty()) {
            throw new DomainException("User not found");
        }
        var person = response.get();
        var mostRatedPost = person.mostRatedPost();
        var mostRecentPost = person.mostRecentPost();
        var dashboard = DashboardResponse.builder().login(person.getLogin())
                .mostRatedPost(this.assignPost(mostRatedPost))
                .mostRecentPost(this.assignPost(mostRecentPost))
                .totalFavorites(person.totalFavorites())
                .totalFollowers(person.totalFollowers())
                .totalPosts(person.totalPosts())
                .followers(person.getFollowers().stream().map(f -> f.getFollower().getLogin()).collect(Collectors.toList()))
                .build();
        return dashboard;
    }

    private PostPreview assignPost(Optional<Post> post) {
        final var baseUrl = "http://localhost:8080/v1/post/%s";
        if (post.isPresent()) {
            var postObject = post.get();
            return PostPreview.builder()
                    .title(postObject.getTitle().toString())
                    .url(String.format(baseUrl, postObject.getTitle().toString()))
                    .build();
        }
        return null;
    }
}
