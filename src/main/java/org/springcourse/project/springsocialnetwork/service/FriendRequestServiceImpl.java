package org.springcourse.project.springsocialnetwork.service;


import java.util.List;
import java.util.Optional;

import org.springcourse.project.springsocialnetwork.dao.FriendRequestRepository;
import org.springcourse.project.springsocialnetwork.dao.UserRepository;
import org.springcourse.project.springsocialnetwork.model.FriendRequest;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepo;
	
    @Autowired
    private FriendRequestRepository friendRequestRepo;
	
	@Override
	public void sendFriendRequest(String name) {
		User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
		User userToSendRequest = userRepo.findByName(name).get();
		Optional<FriendRequest> friendRequestFromOtherUser = friendRequestRepo.findByRequestFromAndRequestTo(userToSendRequest, loggedUSer);
		if (friendRequestFromOtherUser.isPresent()) {
		    FriendRequest friendRequestFromOtherUserEntity = friendRequestFromOtherUser.get();
		    addFriend(userToSendRequest, loggedUSer, friendRequestFromOtherUserEntity);
		    return;
		}
		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setRequestFrom(loggedUSer);
		friendRequest.setRequestTo(userToSendRequest);
		userToSendRequest.getFriendRequests().add(friendRequest);
		userRepo.save(userToSendRequest);
	}

	@Override
	public List<FriendRequest> getAllFriendRequestsTo() {
		User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
		return loggedUSer.getFriendRequests();
	}

    @Override
    public List<FriendRequest> getAllFriendRequestsFrom() {
        User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
        return friendRequestRepo.findByRequestFrom(loggedUSer);
    }

    @Override
    public void acceptFriendRequest(String name) {
        User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
        User userToSendRequest = userRepo.findByName(name).get();
        Optional<FriendRequest> friendRequestFromOtherUser = friendRequestRepo.findByRequestFromAndRequestTo(userToSendRequest, loggedUSer);
        if (friendRequestFromOtherUser.isPresent()) {
            FriendRequest friendRequestFromOtherUserEntity = friendRequestFromOtherUser.get();
            addFriend(userToSendRequest, loggedUSer, friendRequestFromOtherUserEntity);
        }
    }

    @Override
    public void declineFriendRequest(String name) {
        User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
        User userToSendRequest = userRepo.findByName(name).get();
        Optional<FriendRequest> friendRequestFromOtherUser = friendRequestRepo.findByRequestFromAndRequestTo(userToSendRequest, loggedUSer);
        removeRequest(loggedUSer, friendRequestFromOtherUser.get());
        userRepo.save(loggedUSer);
    }

    @Override
    public void cancelFriendRequest(String name) {
        User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
        User userToSendRequest = userRepo.findByName(name).get();
        Optional<FriendRequest> friendRequestFromOtherUser = friendRequestRepo.findByRequestFromAndRequestTo(loggedUSer, userToSendRequest);
        removeRequest(userToSendRequest, friendRequestFromOtherUser.get());
        userRepo.save(userToSendRequest);
    }

    private void addFriend(User userToSendRequest, User loggedUSer, FriendRequest friendRequestFromOtherUserEntity) {
        userToSendRequest.getFriends().add(loggedUSer);
        loggedUSer.getFriends().add(userToSendRequest);
        removeRequest(loggedUSer, friendRequestFromOtherUserEntity);
        userRepo.save(loggedUSer);
        userRepo.save(userToSendRequest);
    }
    
    private void removeRequest(User user, FriendRequest friendRequestFromOtherUserEntity) {
        user.getFriendRequests().remove(friendRequestFromOtherUserEntity);
        friendRequestFromOtherUserEntity.setRequestFrom(null);
        friendRequestFromOtherUserEntity.setRequestTo(null);
    }
}
