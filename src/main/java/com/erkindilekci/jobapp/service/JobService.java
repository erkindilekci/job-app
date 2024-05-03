package com.erkindilekci.jobapp.service;

import com.erkindilekci.jobapp.model.Job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();

    Job findJobById(Long jobId);

    void createJob(Job job);

    boolean deleteJobById(Long jobId);

    boolean updateJob(Long jobId, Job job);
}
