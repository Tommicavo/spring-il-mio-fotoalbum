package org.java.spring.springilmiofotoalbum.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.java.spring.springilmiofotoalbum.db.dto.Photo_DTO;
import org.java.spring.springilmiofotoalbum.db.pojo.Category;
import org.java.spring.springilmiofotoalbum.db.pojo.Photo;
import org.java.spring.springilmiofotoalbum.db.serv.CategoryService;
import org.java.spring.springilmiofotoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/photos/api")
public class PhotoRestController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Photo>> index() {
        List<Photo> photos = photoService.findAll();
        if (photos == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Photo> show(@PathVariable int id) {
        Photo photo = photoService.findById(id);
        if (photo == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Photo_DTO photo_DTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        String title = photo_DTO.getTitle();
        String description = photo_DTO.getDescription();
        String url = photo_DTO.getUrl();
        Boolean visible = photo_DTO.isVisible();
        List<Integer> categoryIds = photo_DTO.getCategoryIds();

        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setUrl(url);
        photo.setVisible(visible);

        List<Category> categories = new ArrayList<>();
        for (int id : categoryIds) {
            Category category = categoryService.findById(id);
            if (category != null)
                categories.add(category);
        }

        photo.setCategories(categories);
        photoService.save(photo);

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @Valid @RequestBody Photo_DTO photo_DTO,
            @PathVariable int id,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        String title = photo_DTO.getTitle();
        String description = photo_DTO.getDescription();
        String url = photo_DTO.getUrl();
        Boolean visible = photo_DTO.isVisible();
        List<Integer> categoryIds = photo_DTO.getCategoryIds();

        Photo photo = photoService.findById(id);
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setUrl(url);
        photo.setVisible(visible);

        photo.getCategories().clear();

        List<Category> categories = new ArrayList<>();
        for (int category_id : categoryIds) {
            Category category = categoryService.findById(category_id);
            if (category != null)
                categories.add(category);
        }

        photo.setCategories(categories);
        photoService.save(photo);

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Photo> delete(@PathVariable int id) {
        Photo photo = photoService.findById(id);
        if (photo == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        photo.getCategories().clear();
        photoService.save(photo);
        photoService.delete(photo);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }
}
