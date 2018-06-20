package com.energyControl.energycontrol.service;

import com.energyControl.energycontrol.domains.Company;
import com.energyControl.energycontrol.domains.User;
import com.energyControl.energycontrol.repository.CompanyRepository;
import com.energyControl.energycontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repo;

    public List<Company> findAll(){
        return repo.findAll();
    }

    public Optional<Company> findById(Integer company){
        return repo.findById(company);
    }

    public Company insert(Company company){
        return repo.save(company);
    }
}
