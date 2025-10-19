package com.felfel.jopapp.service;

import com.felfel.jopapp.Repository.JobRepo;
import com.felfel.jopapp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    public void addJobPost(JobPost jobPost) {
        jobRepo.addJob(jobPost);
    }
    public List<JobPost> getAllJobPosts() {
        return jobRepo.getAllJobs();
    }
}
