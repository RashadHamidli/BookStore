package com.company;

import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        run();
    }

    public void run() {
        String query = "Hibernate: select b.book_id,b.author_id,b.book_name from books b\n" +
                "Hibernate: select a.author_id,a.author_age,a.author_email,a.author_name,a.author_password from authors a where a.author_id=?\n" +
                "Hibernate: select a.author_id,a.book_id,a.book_name from books a where a.author_id=?";
    }

}
