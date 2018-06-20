package com.energyControl.energycontrol.service;

import com.energyControl.energycontrol.domains.Consume;
import com.energyControl.energycontrol.repository.ConsumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumeService {

    @Autowired
    private ConsumeRepository repo;

    @Autowired
    private EmailService emailService;

    public List<Consume> findByUser(Integer user){
        return repo.findByUserId(user);
    }

    public List<Consume> findAll(){
        return repo.findAll();
    }

    public Consume insert(Consume consume){
        consume.setId(null);
        consume.setCost(consume.getCompany().getCostRate() * consume.getQuantity());
        consume.setTimeCurrency(new Date());
        emailService.sendConsumeConfirmationEmail(consume);
        return repo.save(consume);
    }

}
