package com.felfel.springbootrest.Repository;

import com.felfel.springbootrest.model.JobPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
    //@Query("SELECT j FROM JobPost j WHERE j.postProfile LIKE %?1% OR j.PostDesc LIKE %?2%")
    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(String PostProfile,String PostDesc );
}

//    private List<JobPost> jobs = new ArrayList<>(Arrays.asList(
//        new JobPost(1, "Software Engineer", "Develop and maintain software applications.", 2, List.of("Java", "Spring Boot", "SQL")),
//        new JobPost(2, "Frontend Developer", "Create and optimize user interfaces.", 3, List.of("JavaScript", "React", "CSS")),
//        new JobPost(3, "Data Scientist", "Analyze and interpret complex data.", 4, List.of("Python", "Machine Learning", "Statistics"))
//    ));
//
//    public List<JobPost> getAllJobs() {
//        return jobs;
//    }
//
//    public void addJob(JobPost jobPost) {
//        jobs.add(jobPost);
//    }
//
//    public JobPost getJob(int id) {
//        return jobs.stream()
//                .filter(job -> job.getPostId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void updateJob(JobPost jobPost) {
//        for (JobPost jop : jobs) {
//            if (jop.getPostId() == jobPost.getPostId()) {
//                jop.setPostProfile(jobPost.getPostProfile());
//                jop.setPostDesc(jobPost.getPostDesc());
//                jop.setReqExperience(jobPost.getReqExperience());
//                jop.setPostTechStack(jobPost.getPostTechStack());
//                return;
//            }
//        }
//    }
//
//    public void deleteJob(int id) {
//        jobs.removeIf(job -> job.getPostId() == id);
//    }
//}
