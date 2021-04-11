package com.example.apieditdeleteinsert.models.Note;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Note implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", deletedAt=" + deletedAt +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
