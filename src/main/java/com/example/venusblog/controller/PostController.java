package com.example.venusblog.controller;

import com.example.venusblog.data.Post;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;

 // make a method which populates this list after


@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")

public class PostController {
    private List<Post> posts = new ArrayList<>();


        @GetMapping("")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Post> fetchPosts() {


        return posts;
    }



        @GetMapping("/{id}")
        public Post fetchPostsById ( @PathVariable long id){
            //search through the list of post and return the post that matches the given id
            for (Post post:posts) {
               if (post.getId()== id) {
                   return post;
               }
            }
            throw new  RuntimeException("try again");
        }

        private Post findPostById(long id) {
            for (Post post : posts) {
                if (post.getId() == id) {
                    return post;
                }

            }
            return null;
        }

        @PostMapping("")
        private void createPost (@RequestBody Post newPost ){
            posts.add(newPost);

        }
        @PutMapping("/{id}")
        public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
            // find the post to update in the posts list

            Post post = findPostById(id);
            if(post == null) {
                System.out.println("Post not found");
            } else {
                if(updatedPost.getTitle() != null) {
                    post.setTitle(updatedPost.getTitle());
                }
                if(updatedPost.getContent() != null) {
                    post.setContent(updatedPost.getContent());
                }
                return;
            }
            throw new RuntimeException("Post not found");
        }


        @DeleteMapping("/{id}")
        public void deletePostById (@PathVariable Long id){
//            System.out.println("delete function");
//            search through the list of posts and delete
            for (Post post:posts) {
                if (post.getId()== id) {
                    //if we fin the post then delete it
                    posts.remove(post);
                    return; // so you don't run into the throw new exceptions
                }
            }
            throw new  RuntimeException("try again");
        }

        }

