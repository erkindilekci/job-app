package com.erkindilekci.jobapp.service.impl;

import com.erkindilekci.jobapp.model.Company;
import com.erkindilekci.jobapp.repository.CompanyRepository;
import com.erkindilekci.jobapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public boolean updateCompany(Long companyId, Company company) {
        if (!companyRepository.existsById(companyId)) return false;
        company.setId(companyId);
        companyRepository.save(company);
        return true;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long companyId) {
        if (!companyRepository.existsById(companyId)) return false;
        companyRepository.deleteById(companyId);
        return true;
    }
}
