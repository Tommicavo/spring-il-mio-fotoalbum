package org.java.spring.springilmiofotoalbum;

import java.util.List;
import org.java.spring.springilmiofotoalbum.db.pojo.Category;
import org.java.spring.springilmiofotoalbum.db.pojo.Photo;
import org.java.spring.springilmiofotoalbum.db.serv.CategoryService;
import org.java.spring.springilmiofotoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PhotoService photoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Creating Categories
		categoryService.save(new Category("Mare"));
		categoryService.save(new Category("Montagna"));
		categoryService.save(new Category("Persone"));
		categoryService.save(new Category("Oggetti"));
		categoryService.save(new Category("Animali"));

		List<Category> categories = categoryService.findAll();

		// Creating Photos
		photoService.save(new Photo("Titolo_1", "descrizione_1", "https://picsum.photos/450/300?random=1", true,
				categories.get(0)));
		photoService.save(new Photo("Titolo_2", "descrizione_2", "https://picsum.photos/450/300?random=2", true,
				categories.get(0), categories.get(2)));
		photoService.save(new Photo("Titolo_3", "descrizione_3", "https://picsum.photos/450/300?random=3", true,
				categories.get(1), categories.get(3), categories.get(4)));
		photoService.save(new Photo("Titolo_4", "descrizione_4", "https://picsum.photos/450/300?random=4", true));
		photoService.save(new Photo("Titolo_5", "descrizione_5", "https://picsum.photos/450/300?random=5", true,
				categories.get(2), categories.get(3)));
	}
}
