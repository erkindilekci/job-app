package com.erkindilekci.jobapp.service;

import com.erkindilekci.jobapp.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAllCompanies();

    Company findCompanyById(Long companyId);

    boolean updateCompany(Long companyId, Company company);

    void createCompany(Company company);

    boolean deleteCompanyById(Long companyId);
}
