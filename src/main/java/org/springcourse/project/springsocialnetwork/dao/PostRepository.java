package org.springcourse.project.springsocialnetwork.dao;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.model.Group;
import org.springcourse.project.springsocialnetwork.model.Post;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findAll();
    public Optional<Post> findByGroup(Group group);
    public Optional<Post> findByUser(User user);
}
