package com.erkindilekci.jobapp.controller;

import com.erkindilekci.jobapp.model.Job;
import com.erkindilekci.jobapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> findJobById(@PathVariable Long jobId) {
        Job job = jobService.findJobById(jobId);
        return job == null
                ? new ResponseEntity<>(job, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId) {
        boolean isDeleted = jobService.deleteJobById(jobId);
        return isDeleted
                ? new ResponseEntity<>("Job deleted successfully", HttpStatus.OK)
                : new ResponseEntity<>("No job found with id: " + jobId, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        boolean isUpdated = jobService.updateJob(jobId, job);
        return isUpdated
                ? new ResponseEntity<>("Job updated successfully", HttpStatus.OK)
                : new ResponseEntity<>("No job found with id: " + jobId, HttpStatus.NOT_FOUND);
    }
}
