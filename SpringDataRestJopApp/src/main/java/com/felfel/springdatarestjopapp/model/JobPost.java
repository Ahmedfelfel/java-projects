package com.felfel.springdatarestjopapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Represents a job posting persisted to the "job_post" table.
 *
 * <p>Fields map to columns; {@link #postTechStack} is stored as JSON text via
 * {@link StringListConverter} so the tech stack can be modelled as a list.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Primary key identifier for the job post. */
    private int postId;

    /** Human readable role or profile title, e.g. "Backend Developer". */
    private String postProfile;

    /** Short description for the job post. */
    private String PostDesc;

    /** Required years of experience for the position. */
    private int ReqExperience;

    /** List of technology tags associated with the job; persisted as JSON text. */
    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> postTechStack;
}
