package org.springcourse.project.springsocialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.dao.GroupRepository;
import org.springcourse.project.springsocialnetwork.dao.PostRepository;
import org.springcourse.project.springsocialnetwork.dao.UserRepository;
import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.Group;
import org.springcourse.project.springsocialnetwork.model.Post;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Post> getPosts() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Post createPost(Post post) {
        return repository.save(post);
    }

    @Override
    public Post createPostInGroup(Post post, final long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (!group.isPresent()) {
            throw new EntityNotFoundException(String.format("Group with id '%d' doesn't exist.", groupId));
        }
        post.setGroup(group.get());
        return repository.save(post);
    }

    @Override
    public Post updatePost(Post post, final long id) {
        Optional<Post> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("Post with id '%d' doesn't exist.", id));
        }
        Post existingPost = existing.get();
        existingPost.setText(post.getText());
        return repository.save(existingPost);
    }

    @Override
    public void deletePost(long id) {
        Optional<Post> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("Post with id '%d' doesn't exist.", id));
        }
        Post existingPost = existing.get();
        repository.delete(existingPost);
    }

    @Override
    public List<Post> getFeed() {
        User user = userRepo.findByName(securityService.getLoggedInName()).get();
        List<User> friends = user.getFriends();
        if (friends.isEmpty()) {
            return repository.findAllByUserOrderByUpdatedAtDesc(user);
        }
        return repository.findAllPostsFromUserAndFriends(user, friends);
    }

}
