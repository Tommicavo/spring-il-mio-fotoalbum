package org.java.spring.springilmiofotoalbum.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import org.hibernate.validator.constraints.Length;

@Entity
public class Photo {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64)
    @NotBlank(message = "Field 'Title' must not be empty")
    @Length(max = 64, message = "Field 'Title' must not be longer than 64 characters")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Field 'url' must not be empty")
    private String url;

    private boolean visible;

    // RELATIONSHIPS
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "photo_category", joinColumns = @JoinColumn(name = "photo_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    // CONSTRUCTORS
    public Photo() {
    }

    public Photo(String title, String description, String url, boolean visible, Category... categories) {
        setTitle(title);
        setDescription(description);
        setUrl(url);
        setVisible(visible);
        setCategories(categories);
    }

    // GETTERS AND SETTERS
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private void setCategories(Category... categories) {
        setCategories(Arrays.asList(categories));
    }

    // TO STRING
    @Override
    public String toString() {
        return "[" + getId() + "] - " + getTitle();
    }

    // METHODS
}
