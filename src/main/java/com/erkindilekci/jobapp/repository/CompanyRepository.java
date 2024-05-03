package com.erkindilekci.jobapp.repository;

import com.erkindilekci.jobapp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
