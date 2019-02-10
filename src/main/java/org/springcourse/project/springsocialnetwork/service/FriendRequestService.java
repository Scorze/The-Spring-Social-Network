package org.springcourse.project.springsocialnetwork.service;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.FriendRequest;
import org.springcourse.project.springsocialnetwork.model.User;

public interface FriendRequestService {
	
	public void sendFriendRequest(User user);
    
    public List<FriendRequest> getAllFriendRequest();
	
}
