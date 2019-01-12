package org.springcourse.project.springsocialnetwork.dao;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
    public Optional<User> findByName(String name);
    public Optional<User> findByEmail(String email);
}
