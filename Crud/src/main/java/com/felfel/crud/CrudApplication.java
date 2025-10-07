package com.felfel.crud;

import com.felfel.crud.model.Student;
import com.felfel.crud.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(CrudApplication.class, args);
        Student s = context.getBean(Student.class);
        StudentService service = context.getBean(StudentService.class);
        s.setId(4);
        s.setName("felfel");
        s.setGpa(3.8);
        service.addStudent(s);

        List<Student> students = service.getAllStudents();
        System.out.println(students);

        service.deleteStudent(4);

        students = service.getAllStudents();
        System.out.println(students);

        service.updateStudent(3, "Charlie", 3.9);
        students = service.getAllStudents();
        System.out.println(students);
    }


}
