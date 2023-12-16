package org.java.spring.springilmiofotoalbum.restController;

import java.util.List;

import org.java.spring.springilmiofotoalbum.db.pojo.Photo;
import org.java.spring.springilmiofotoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/photos/api")
public class PhotoRestController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<Photo>> index() {
        List<Photo> photos = photoService.findAll();
        if (photos == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> show(@PathVariable int id) {
        Photo photo = photoService.findById(id);
        if (photo == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }
}
