package com.felfel.crud.repository;

import com.felfel.crud.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public void save(Student s) {
        String query = "insert into student (id, name, gpa) values (?, ?, ?)";
        int row = jdbc.update(query, s.getId(), s.getName(), s.getGpa());
        if (row > 0) {
            System.out.println("A new student has been inserted.");
        }

    }

    public List<Student> findAll() {
        List <Student> students = jdbc.query("select * from student", (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setGpa(rs.getDouble("gpa"));
            return s;
        });
        return students;
    }
    public void removeById(int id) {
        String query = "delete from student where id = ?";
        int row = jdbc.update(query, id);
        if (row > 0) {
            System.out.println("A student has been deleted.");
        }
        else
            System.out.println("No student found with the given id.");
    }

    /**
     * get field
     *
     * @return jdbc
     */
    public JdbcTemplate getJdbc() {
        return this.jdbc;
    }

    /**
     * set field
     *
     * @param jdbc
     */
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void updateByID(int i, String name, double v) {
        String query = "update student set name = ?, gpa = ? where id = ?";
        int row = jdbc.update(query, name, v, i);
        if (row > 0) {
            System.out.println("A student has been updated.");
        }
        else
            System.out.println("No student found with the given id.");
    }
}
