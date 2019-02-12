package org.springcourse.project.springsocialnetwork.dao;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.model.Group;
import org.springcourse.project.springsocialnetwork.model.Post;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT p FROM POSTS p WHERE p.user = ?1 OR p.user IN ?2 ORDER BY p.updatedAt DESC")
    public List<Post> findAllPostsFromUserAndFriends(User user, List<User> friends);
    public List<Post> findAllByUserOrderByUpdatedAtDesc(User user);
    public List<Post> findAllByOrderByCreatedAtDesc();
    public Optional<Post> findByGroup(Group group);
    public Optional<Post> findByUser(User user);
}
