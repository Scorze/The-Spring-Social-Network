package org.springcourse.project.springsocialnetwork.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name="POSTS")
@Table(name="POSTS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"id", "createdAt", "updatedAt", "attachmentId"}
)
public class Post {

    public static final String EMPTY_POST = "Your post must containt some text";

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

    @Column(name="TEXT", nullable=false, unique=false, length=4096)
    @NotEmpty(message = EMPTY_POST)
    private String text;

    @Column(name="ATTACHMENT_ID", nullable=true, unique=false, length=256)
    private String attachmentId;

    @ManyToOne
    @JoinColumn(name = "USER_ID"/*, nullable = false*/)
    @JsonProperty(access = Access.READ_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Group group;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        mappedBy = "post")
    @JsonProperty(access = Access.READ_ONLY)
    private List<Comment> comments;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", text=" + text + ", user="
                + user + "]";
    }

}
