package org.springcourse.project.springsocialnetwork.dao;

import java.util.List;

import org.springcourse.project.springsocialnetwork.model.FriendRequest;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long>{
	
	public List<FriendRequest> findByRequestFrom(User user);
	
}
