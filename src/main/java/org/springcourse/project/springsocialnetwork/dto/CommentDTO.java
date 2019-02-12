package org.springcourse.project.springsocialnetwork.dto;

public class CommentDTO {

    private String text;

    private long postId;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public long getPostId() {
        return postId;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "CommentDTO [text=" + text + ", postId=" + postId + "]";
    }

}
