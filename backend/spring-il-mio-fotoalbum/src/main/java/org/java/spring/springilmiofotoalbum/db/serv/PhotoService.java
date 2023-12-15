package org.java.spring.springilmiofotoalbum.db.serv;

import java.util.List;

import org.java.spring.springilmiofotoalbum.db.pojo.Photo;
import org.java.spring.springilmiofotoalbum.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepo photoRepo;

    public List<Photo> findAll() {
        return photoRepo.findAll();
    }

    public Photo findById(int id) {
        return photoRepo.findById(id).get();
    }

    public void save(Photo photo) {
        photoRepo.save(photo);
    }

    public void delete(Photo photo) {
        photoRepo.delete(photo);
    }
}
