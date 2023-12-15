package org.java.spring.springilmiofotoalbum.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64)
    @NotBlank(message = "Field 'Label' must not be empty")
    @Length(max = 64, message = "Field 'Label' must not be longer than 64 characters")
    private String label;

    // RELATIONSHIPS
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Photo> photos;

    // CONSTRUCTORS
    public Category() {
    }

    public Category(String label) {
        setLabel(label);
    }

    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    // TO STRING
    @Override
    public String toString() {
        return "[" + getId() + "] - " + getLabel();
    }

    // METHODS
}
