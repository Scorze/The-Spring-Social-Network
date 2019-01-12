package org.springcourse.project.springsocialnetwork.service;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.Post;

public interface PostService {

    public List<Post> getPosts();

    public Post createPost(final Post post);

    public Post createPostInGroup(final Post post, final long groupId);

    public Post updatePost(Post post, final long id);

    public void deletePost(long id);
}
