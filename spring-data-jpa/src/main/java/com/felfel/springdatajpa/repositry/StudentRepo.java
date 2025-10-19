package com.felfel.springdatajpa.repositry;

import com.felfel.springdatajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>
{

}
