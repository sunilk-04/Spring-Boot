package com.prominentpixel.jpawithdynamicquery;

import com.prominentpixel.jpawithdynamicquery.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class JpawithdynamicqueryApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(JpawithdynamicqueryApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // Adding students
        this.studentService.insert("Sunil", "sunilk@prominentpixel.com", "Rajkot");
        this.studentService.insert("John", "john@test.com", "Sydney");
        this.studentService.insert("Tom", "tom@example.com", "London");
        this.studentService.insert("Steve", "steve@gmail.com", "New York");
        this.studentService.insert("Joseph", "joseph@demo.com", "Paris");
        this.studentService.insert("David", "david@yahoo.com", "London");

        // Displaying students
        System.out.println("Active students : " + this.studentService.getAll());

        // Find by Id
        System.out.println("Student with id 4 : " + this.studentService.getById(4));

        // Find by Ids (Sort by name)
        List<Integer> idsToSearch = new ArrayList<>();
        idsToSearch.add(1);
        idsToSearch.add(2);
        idsToSearch.add(4);
        System.out.println("Students which have ids 1, 2 and 4 (Sort by name) : " + this.studentService.getByIds(idsToSearch, "name"));

        // Deactivate student with id 3 and 5
        this.studentService.deactivate(3);
        this.studentService.deactivate(5);
        System.out.println("Active student ids : " + this.studentService.getActiveIds());

        // Activate student with id 5
        this.studentService.activate(5);
        System.out.println("Active student ids : " + this.studentService.getActiveIds());
    }
}
