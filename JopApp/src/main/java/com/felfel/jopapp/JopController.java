package com.felfel.jopapp;

import com.felfel.jopapp.model.JobPost;
import com.felfel.jopapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JopController {

    @Autowired
    private JobService service;

    // Controller methods will go here

    @GetMapping({"/","home"})
    public String home() {
        System.out.println("home page requested");
        return "home"; // returns the name of the view
    }

    @GetMapping("/addjob")
    public String addjob() {
        System.out.println("addjop page requested");
        return "addjob"; // returns the name of the view
    }

    @GetMapping("/viewalljobs")
    public String viewalljobs(Model m) {
        System.out.println("viewalljobs page requested");
        List<JobPost> jobPosts = service.getAllJobPosts();
        m.addAttribute("jobPosts", jobPosts);
        return "viewalljobs"; // returns the name of the view
    }

    @PostMapping("/handleForm")
    public String success(JobPost jobPost) {
        System.out.println("success page requested");
        service.addJobPost(jobPost);
        return "success"; // returns the name of the view
    }


}
