package org.springcourse.project.springsocialnetwork.web;

import java.util.List;

import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.Post;
import org.springcourse.project.springsocialnetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public List<Post> getPosts() {
        return service.getPosts();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        final Post createdPost = service.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PostMapping("{groupId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("groupId") final Long groupId) {
        final Post createdPost = service.createPostInGroup(post, groupId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("id") final Long id) {
        Post updatedPost = null;
        try {
            updatedPost = service.updatePost(post, id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") final Long id) {
        try {
            service.deletePost(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
