package com.example.venusblog.controller;

import com.example.venusblog.data.Category;
import com.example.venusblog.data.Post;
import com.example.venusblog.data.User;
import com.example.venusblog.misc.FieldHelper;
import com.example.venusblog.repository.CategoriesRepository;
import com.example.venusblog.repository.PostsRepository;
import com.example.venusblog.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostsController {


    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;

    private EmailService emailService;

    @GetMapping("")
    public List<Post> fetchPosts() {
        return postsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> fetchPostById(@PathVariable long id) {
        return postsRepository.findById(id);
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
    String  userName = auth.getName();
    User author = usersRepository.findByUserName((userName));
//         use a fake author for the post
//        User author = usersRepository.findById(1L).get();
        newPost.setAuthor(author);
        newPost.setCategories(new ArrayList<>());
        Category cat1 = categoriesRepository.findById(1L).get();
        Category cat2 = categoriesRepository.findById(2L).get();

        newPost.getCategories().add(cat1);
        newPost.getCategories().add(cat2);
//
//        // make some fake categories and throw them in the new post
//        Category cat1 = new Category(1L, "bunnies", null);
//        Category cat2 = new Category(2L, "margaritas", null);
//        newPost.setCategories(new ArrayList<>());
//        newPost.getCategories().add(cat1);
//        newPost.getCategories().add(cat2);

        postsRepository.save(newPost);

        emailService.prepareAndSend(newPost, "you have a new post ", "the body");
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        Optional<Post> optionalPost = postsRepository.findById(id);
        if(optionalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id " + id + " not found");
        }
        postsRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
        Optional<Post> originalPost = postsRepository.findById(id);
        if(originalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + " not found");
        }

        // in case id is not in the request body (i.e., updatedPost), set it
        // with the path variable id
        updatedPost.setId(id);

        // copy any new field values FROM updatedPost TO originalPost
        BeanUtils.copyProperties(updatedPost, originalPost.get(), FieldHelper.getNullPropertyNames(updatedPost));

        postsRepository.save(originalPost.get());
    }


}