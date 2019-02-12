package org.springcourse.project.springsocialnetwork.service;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.FriendRequest;

public interface FriendRequestService {

	public void sendFriendRequest(String name);

	public void acceptFriendRequest(String name);

	public void declineFriendRequest(String name);

	public void cancelFriendRequest(String name);

    public List<FriendRequest> getAllFriendRequestsTo();

    public List<FriendRequest> getAllFriendRequestsFrom();
	
}
