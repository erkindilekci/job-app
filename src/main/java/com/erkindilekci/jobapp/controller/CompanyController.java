package com.erkindilekci.jobapp.controller;

import com.erkindilekci.jobapp.model.Company;
import com.erkindilekci.jobapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.findAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId) {
        return new ResponseEntity<>(companyService.findCompanyById(companyId), HttpStatus.OK);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long companyId, @RequestBody Company company) {
        return companyService.updateCompany(companyId, company)
                ? new ResponseEntity<>("Company updated successfully", HttpStatus.OK)
                : new ResponseEntity<>("No company found with id: " + companyId, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long companyId) {
        return companyService.deleteCompanyById(companyId)
                ? new ResponseEntity<>("Company deleted successfully", HttpStatus.OK)
                : new ResponseEntity<>("No company found with id: " + companyId, HttpStatus.NOT_FOUND);
    }
}
