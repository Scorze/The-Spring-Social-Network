package org.springcourse.project.springsocialnetwork.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name="USERS")
@Table(name="USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"id", "createdAt", "updatedAt", "userRole"}
)
public class User {

    public static final String EMPTY_NAME = "Your name must not be empty";
    public static final String EMPTY_PASS = "Please provide your password";
    public static final String EMPTY_EMAIL = "Please provide your e-mail";
    public static final String INVALID_EMAIL = "Please provide a valid e-mail";
    public static final String INVALID_PASS = "Your password must contain at least 8 characters";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name="EMAIL", nullable=false, unique=true, length=128)
    @Email(message = INVALID_EMAIL)
    @NotEmpty(message = EMPTY_EMAIL)
    private String email;

    @Column(name="NAME", nullable=false, unique=false, length=128)
    @NotEmpty(message = EMPTY_NAME)
    private String name;

    @Column(name = "PASSWORD")
    @Size(min = 8, message = INVALID_PASS)
    @NotEmpty(message = EMPTY_PASS)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole userRole) {
        this.role = userRole;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", email=" + email + ", name=" + name + ", role=" + role + "]";
    }

}
