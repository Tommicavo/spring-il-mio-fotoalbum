package org.java.spring.springilmiofotoalbum.db.dto;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.java.spring.springilmiofotoalbum.auth.db.pojo.User;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public class Photo_DTO {

    private int id;

    @NotBlank(message = "Field 'Title' must not be empty")
    @Length(max = 64, message = "Field 'Title' must not be longer than 64 characters")
    private String title;

    private String description;

    @NotBlank(message = "Field 'url' must not be empty")
    private String url;

    private Boolean visible;

    private List<Integer> categoryIds;

    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @AssertTrue(message = "Custom validation message")
    public boolean isCustomValidationTrue() {
        // Add custom validation logic if needed
        System.out.println("Custom validation called");
        return true;
    }
}
