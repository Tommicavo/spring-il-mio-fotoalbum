package org.java.spring.springilmiofotoalbum.db.repo;

import org.java.spring.springilmiofotoalbum.db.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {
}
