package com.felfel.crud.service;

import com.felfel.crud.model.Student;
import com.felfel.crud.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepo repo;

    public void addStudent(Student s) {
        repo.save(s);
    }

    /**
     * get field
     *
     * @return repo
     */
    public StudentRepo getRepo() {
        return this.repo;
    }

    /**
     * set field
     *
     * @param repo
     */
    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public void deleteStudent(int i) {
        repo.removeById(i);
    }

    public void updateStudent(int i, String name, double v) {
        repo.updateByID(i,name,v);
    }
}
