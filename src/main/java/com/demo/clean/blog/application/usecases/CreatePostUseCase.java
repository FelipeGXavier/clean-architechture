package com.demo.clean.blog.application.usecases;

import com.demo.clean.blog.adapters.CreatePostRequest;
import com.demo.clean.blog.application.CreatePost;
import com.demo.clean.blog.domain.Post;
import com.demo.clean.blog.domain.PostBody;
import com.demo.clean.blog.domain.PostLink;
import com.demo.clean.blog.domain.PostTitle;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import com.demo.clean.shared.LoadLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreatePostUseCase implements CreatePost {

    private final PostRepository postRepository;

    @Autowired
    public CreatePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void execute(CreatePostRequest request) {
        var loggedUser = LoadLoggedUser.load(request.getUser());
        var postBody = PostBody.of(request.getBody());
        var postTitle = PostTitle.of(request.getTitle());
        var post = new Post(postBody, postTitle, loggedUser);
        Set<PostLink> postLinks = new HashSet<>();
        request.getLinks().forEach(link -> postLinks.add(new PostLink(link, post)));
        post.addLinks(postLinks);
        this.postRepository.save(post);
    }
}
