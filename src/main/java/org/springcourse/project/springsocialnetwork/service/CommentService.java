package org.springcourse.project.springsocialnetwork.service;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.Comment;

public interface CommentService {

    public List<Comment> getComments();

    public Comment createComment(final Comment comment);

    public Comment updateComment(Comment comment, final long id);

    public void deleteComment(long id);
}
