package com.erkindilekci.jobapp.service.impl;

import com.erkindilekci.jobapp.model.Job;
import com.erkindilekci.jobapp.repository.JobRepository;
import com.erkindilekci.jobapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJobById(Long jobId) {
        if (!jobRepository.existsById(jobId)) return false;
        jobRepository.deleteById(jobId);
        return true;
    }

    @Override
    public boolean updateJob(Long jobId, Job job) {
        if (!jobRepository.existsById(jobId)) return false;
        job.setId(jobId);
        jobRepository.save(job);
        return true;
    }
}
