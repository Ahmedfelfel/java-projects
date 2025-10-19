package com.felfel.jopapp.Repository;

import com.felfel.jopapp.model.JobPost;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JobRepo {

    private List<JobPost> jobs = new ArrayList<>(Arrays.asList(
        new JobPost(1, "Software Engineer", "Develop and maintain software applications.", 2, List.of("Java", "Spring Boot", "SQL")),
        new JobPost(2, "Frontend Developer", "Create and optimize user interfaces.", 3, List.of("JavaScript", "React", "CSS")),
        new JobPost(3, "Data Scientist", "Analyze and interpret complex data.", 4, List.of("Python", "Machine Learning", "Statistics"))
    ));

    public List<JobPost> getAllJobs() {
        return jobs;
    }

    public void addJob(JobPost jobPost) {
        jobs.add(jobPost);
    }
}
