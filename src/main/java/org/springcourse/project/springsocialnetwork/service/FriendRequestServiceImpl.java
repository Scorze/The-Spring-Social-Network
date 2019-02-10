package org.springcourse.project.springsocialnetwork.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springcourse.project.springsocialnetwork.dao.UserRepository;
import org.springcourse.project.springsocialnetwork.model.FriendRequest;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendRequestServiceImpl implements FriendRequestService {

	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public void sendFriendRequest(User user) {
		User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setRequestFrom(loggedUSer);
		friendRequest.setRequestTo(user);
		user.getFriendRequests().add(friendRequest);
		userRepo.save(user);
	}

	@Override
	public List<FriendRequest> getAllFriendRequest() {
		User loggedUSer = userRepo.findByName(securityService.getLoggedInName()).get();
		return loggedUSer.getFriendRequests();
	}

}
