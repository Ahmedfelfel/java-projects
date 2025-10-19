package com.felfel.springbootrest.service;

import com.felfel.springbootrest.Repository.JobRepo;
import com.felfel.springbootrest.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    public void addJobPost(JobPost jobPost) {
        jobRepo.save(jobPost);
    }
    public List<JobPost> getAllJobPosts() {
        return jobRepo.findAll();
    }

    public JobPost getJobPost(int id) {

       return  jobRepo.findById(id).orElse(null);
    }

    public void updateJob(JobPost jobPost) {
        jobRepo.save(jobPost);
    }

    public void deleteJob(int id) {
        jobRepo.deleteById(id);
    }

    public List<JobPost> search(String keyword) {
        return jobRepo.findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(keyword, keyword);
    }

    public void load()
    {
        List<JobPost> jobs = new ArrayList<>(Arrays.asList(
        new JobPost(1, "Software Engineer", "Develop and maintain software applications.", 2, List.of("Java", "Spring Boot", "SQL")),
        new JobPost(2, "Frontend Developer", "Create and optimize user interfaces.", 3, List.of("JavaScript", "React", "CSS")),
        new JobPost(3, "Data Scientist", "Analyze and interpret complex data.", 4, List.of("Python", "Machine Learning", "Statistics"))
    ));
        jobRepo.saveAll(jobs);
    }


//    @Autowired
//    private JobRepo jobRepo;
//
//    public void addJobPost(JobPost jobPost) {
//        jobRepo.addJob(jobPost);
//    }
//    public List<JobPost> getAllJobPosts() {
//        return jobRepo.getAllJobs();
//    }
//
//    public JobPost getJobPost(int id) {
//        return jobRepo.getJob(id);
//    }
//
//    public void updateJob(JobPost jobPost) {
//        jobRepo.updateJob(jobPost);
//    }
//
//    public void deleteJob(int id) {
//        jobRepo.deleteJob(id);
//    }





}
