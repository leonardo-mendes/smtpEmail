package com.energyControl.energycontrol.repository;

import com.energyControl.energycontrol.domains.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}

