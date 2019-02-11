package org.springcourse.project.springsocialnetwork.service;


import java.util.List;

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

}
