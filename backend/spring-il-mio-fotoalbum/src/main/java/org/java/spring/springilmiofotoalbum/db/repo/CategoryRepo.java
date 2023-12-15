package org.java.spring.springilmiofotoalbum.db.repo;

import org.java.spring.springilmiofotoalbum.db.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
