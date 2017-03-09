package mhci.teamsix.ugs.incampus.util;

import java.io.Serializable;

/**
 * Created by Vincey on 7/3/2017.
 */

public class Comment implements Serializable{
    private String commentId;
    private String name;
    private String description;
    private float ratings;
    private String timestamp;

    public Comment() {
    }

    public Comment(String commentId, String name, String description, float ratings, String timestamp) {
        this.commentId = commentId;
        this.name = name;
        this.description = description;
        this.ratings = ratings;
        this.timestamp = timestamp;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
