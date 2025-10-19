package com.felfel.springdatajpa;

import com.felfel.springdatajpa.model.Student;
import com.felfel.springdatajpa.repositry.StudentRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(SpringDataJpaApplication.class, args);
        StudentRepo repo = context.getBean(StudentRepo.class);
        Student s1 = context.getBean(Student.class);
        Student s2 = context.getBean(Student.class);
        Student s3 = context.getBean(Student.class);

//        s1.setId(1);
//        s1.setName("Alice");
//        s1.setGpa(3.5);
//        repo.save(s1);
//
//        s2.setId(2);
//        s2.setName("Bob");
//        s2.setGpa(3.7);
//        repo.save(s2);
//
//        s3.setId(3);
//        s3.setName("Charlie");
//        s3.setGpa(3.9);
//        repo.save(s3);
        System.out.println(repo.findAll());
        System.out.println(repo.findById(2));

    }

}
