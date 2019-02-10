package org.springcourse.project.springsocialnetwork.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name="FRIENDREQUEST")
@Table(name="FRIENDREQUEST")
public class FriendRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@ManyToOne
    @JoinColumn(name = "FROM_USER_ID"/*, nullable = false*/)
	private User requestFrom;
	@ManyToOne
    @JoinColumn(name = "TO_USER_ID"/*, nullable = false*/)
	private User requestTo;
	
	
	public long getId() {
		return id;
	}
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
