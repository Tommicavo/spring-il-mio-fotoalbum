package org.java.spring.springilmiofotoalbum.restController;

import java.util.List;
import org.java.spring.springilmiofotoalbum.db.pojo.Category;
import org.java.spring.springilmiofotoalbum.db.pojo.Photo;
import org.java.spring.springilmiofotoalbum.db.serv.CategoryService;
import org.java.spring.springilmiofotoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/categories/api")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<Category>> index() {
        List<Category> categories = categoryService.findAll();
        if (categories == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody String label) {
        Category category = new Category(label);
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if (category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<Photo> photos = category.getPhotos();

        for (Photo photo : photos) {
            photo.getCategories().remove(category);
            photoService.save(photo);
        }

        categoryService.delete(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
