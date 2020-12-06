package com.demo.clean.blog.post.application.usecases;

import com.demo.clean.blog.post.adapters.CreatePostRequest;
import com.demo.clean.blog.post.application.CreatePost;
import com.demo.clean.blog.post.domain.Post;
import com.demo.clean.blog.post.domain.PostBody;
import com.demo.clean.blog.post.domain.PostLink;
import com.demo.clean.blog.post.domain.PostTitle;
import com.demo.clean.blog.post.infra.persistence.repositories.PostRepository;
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
    public void createPost(CreatePostRequest request) {
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
