package org.springcourse.project.springsocialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.dao.CommentRepository;
import org.springcourse.project.springsocialnetwork.dao.PostRepository;
import org.springcourse.project.springsocialnetwork.dao.UserRepository;
import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.Comment;
import org.springcourse.project.springsocialnetwork.model.Post;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public List<Comment> getComments() {
        return repository.findAll();
    }

    @Override
    public Comment createComment(String text, final long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new EntityNotFoundException(String.format("Post with id '%d' doesn't exist.", postId));
        }
        Comment comment = new Comment();
        comment.setText(text);
        comment.setPost(post.get());
        User user = userRepository.findByName(securityService.getLoggedInName()).get();
        comment.setUser(user);
        return repository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment, final long id) {
        Optional<Comment> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("Comment with id '%d' doesn't exist.", id));
        }
        Comment existingComment = existing.get();
        existingComment.setText(comment.getText());
        return repository.save(existingComment);
    }

    @Override
    public void deleteComment(long id) {
        Optional<Comment> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("Comment with id '%d' doesn't exist.", id));
        }
        Comment existingComment = existing.get();
        repository.delete(existingComment);
    }

}
