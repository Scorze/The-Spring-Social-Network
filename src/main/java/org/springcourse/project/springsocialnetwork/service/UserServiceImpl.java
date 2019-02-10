package org.springcourse.project.springsocialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.dao.FriendRequestRepository;
import org.springcourse.project.springsocialnetwork.dao.UserRepository;
import org.springcourse.project.springsocialnetwork.exception.DuplicateUserException;
import org.springcourse.project.springsocialnetwork.exception.EntityNotFoundException;
import org.springcourse.project.springsocialnetwork.model.FriendRequest;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springcourse.project.springsocialnetwork.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private FriendRequestRepository friendRequestRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private SecurityService securityService;

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User createUser(final User user) {
        repository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new DuplicateUserException(String.format("User with email '%s' already exists.", user.getEmail()));
        });
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return repository.save(user);
    }

    @Override
    public User updateUser(User user, final long id) {
        Optional<User> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("User with id '%d' doesn't exist.", id));
        }
        User existingUser = existing.get();
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        if (user.getPassword() != null) {
            existingUser.setPassword(encoder.encode(user.getPassword()));
        }
        return repository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new EntityNotFoundException(String.format("User with id '%d' doesn't exist.", id));
        }
        User existingUser = existing.get();
        repository.delete(existingUser);
    }

    @Override
    public User findByName(String name) {
        Optional<User> existing = repository.findByName(name);
        if (existing.isPresent()) {
            return existing.get();
        }
        return null;
    }

	@Override
	public List<User> findByNonFriends(String name) {
		List<User> allUsers = repository.findByNameContaining(name);
		String loggedUserName = securityService.getLoggedInName();
		User loggedUser = repository.findByName(loggedUserName).get();
		List<User> loggedUserFriends = loggedUser.getFriends();
		List<FriendRequest> allSendFriendRequest = friendRequestRepo.findByRequestFrom(loggedUser);
		for (FriendRequest fr : allSendFriendRequest) {
			allUsers.remove(fr.getRequestTo());
		}
		allUsers.removeAll(loggedUserFriends);
		allUsers.remove(loggedUser);
		return allUsers;
	}

}
