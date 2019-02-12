package org.springcourse.project.springsocialnetwork.web;

import java.util.List;

import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.Comment;
import org.springcourse.project.springsocialnetwork.service.CommentService;
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
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public List<Comment> getComments() {
        return service.getComments();
    }

    /*@PostMapping("{postId}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable("postId") final Long postId) {
        final Comment createdComment = service.createComment(comment, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }*/

    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("id") final Long id) {
        Comment updatedComment = null;
        try {
            updatedComment = service.updateComment(comment, id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") final Long id) {
        try {
            service.deleteComment(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
