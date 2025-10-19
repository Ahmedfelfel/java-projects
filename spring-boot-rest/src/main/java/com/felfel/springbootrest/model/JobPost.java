package com.felfel.springbootrest.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Scope("prototype")
public class JobPost {
    @Id
    private int postId;
    private String postProfile;
    private String postDesc;
    private int ReqExperience;
    private List<String> postTechStack;
}
