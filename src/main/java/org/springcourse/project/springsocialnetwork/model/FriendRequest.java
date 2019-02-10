package org.springcourse.project.springsocialnetwork.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="FRIENDREQUEST")
@Table(name="FRIENDREQUEST")
public class FriendRequest {
	
	User requestFrom;
	User requestTo;
	
	
	public User getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(User requestFrom) {
		this.requestFrom = requestFrom;
	}
	public User getRequestTo() {
		return requestTo;
	}
	public void setRequestTo(User requestTo) {
		this.requestTo = requestTo;
	}
	
	
}
