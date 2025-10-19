package com.felfel.springdatarestjopapp;

import com.felfel.springdatarestjopapp.Repository.JobRepo;
import com.felfel.springdatarestjopapp.model.JobPost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringDataRestJopAppApplication {

    /**
     * Entry point for the SpringDataRestJopApp application.
     *
     * <p>This application boots a Spring context, obtains the JobRepo bean and
     * prints all JobPost entities to stdout. Typical use is local development
     * and demonstration of Spring Data JPA repository access.</p>
     *
     * @param args startup arguments forwarded to SpringApplication.run
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringDataRestJopAppApplication.class, args);
        System.out.println("Application Started Successfully");
        JobRepo jobRepo = context.getBean(JobRepo.class);
        //JobPost jobPost = context.getBean(JobPost.class);

        List<JobPost> allPosts = jobRepo.findAll();
        allPosts.forEach(System.out::println);

    }

}
