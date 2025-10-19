package com.felfel.springbootrest;

import com.felfel.springbootrest.model.JobPost;
import com.felfel.springbootrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:3000") // Enable CORS for the specified origin
public class JopRestController {

    @Autowired
    private JobService service;

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobs() {
        return service.getAllJobPosts();
    }
    @GetMapping("/jobPost/{id}")
    public JobPost getJob(@PathVariable int id) {
        return service.getJobPost(id);
    }
    @GetMapping("/jobPosts/keyword/{keyword}")
    public List<JobPost> getJobsByKeyword(@PathVariable String keyword) {
        return service.search(keyword);
    }


    @PostMapping("/jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost) {
        service.addJobPost(jobPost);
        return service.getJobPost(jobPost.getPostId());
    }
    @PutMapping("/jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJobPost(jobPost.getPostId());
    }
    @DeleteMapping("/jobPost/{id}")
    public String deleteJob(@PathVariable int id) {
        service.deleteJob(id);
        return "Job with id " + id + " deleted.";
    }

    @GetMapping("/jobPosts/load")
    public String load()
    {
        service.load();
        return "Data Loaded";
    }
}
