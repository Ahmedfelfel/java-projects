package com.felfel.springdatarestjopapp.Repository;

import com.felfel.springdatarestjopapp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Spring Data JPA repository for JobPost entities.
 *
 * <p>Inherits standard CRUD and paging operations from JpaRepository.
 * Additional query methods can be declared here as needed.</p>
 */
public interface JobRepo extends JpaRepository<JobPost, Integer> {

}
