package org.springcourse.project.springsocialnetwork.service;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.User;

public interface UserService {

    public List<User> getUsers();

    public User findByName(final String name);
    
    public List<User> findByNonFriends(final String name);
    
    public User createUser(final User user);

    public User updateUser(User user, final long id);

    public void deleteUser(long id);
}
