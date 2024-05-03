package com.erkindilekci.jobapp.repository;

import com.erkindilekci.jobapp.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
