package com.energyControl.energycontrol.resource;

import com.energyControl.energycontrol.domains.Company;
import com.energyControl.energycontrol.domains.Consume;
import com.energyControl.energycontrol.domains.User;
import com.energyControl.energycontrol.service.CompanyService;
import com.energyControl.energycontrol.service.ConsumeService;
import com.energyControl.energycontrol.service.EmailService;
import com.energyControl.energycontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/consumes")
public class ConsumeResource {

    @Autowired
    private ConsumeService service;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Consume consume) {
        Consume obj = service.insert(consume);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/user/{userId}/company/{companyId}/quantity/{quantity}", method = RequestMethod.GET)
    public ResponseEntity<Void> insertByGet(
            @PathVariable("userId") Integer userId,
            @PathVariable("companyId") Integer companyId,
            @PathVariable("quantity") Integer quantity
    ) {
        User user = new User();
        Optional<User> optionalUser = userService.findById(userId);
        optionalUser.ifPresent(p -> user.setId(p.getId()));
        optionalUser.ifPresent(p -> user.setName(p.getName()));
        optionalUser.ifPresent(p -> user.setEmail(p.getEmail()));
        optionalUser.ifPresent(p -> user.setLimitValue(p.getLimitValue()));

        Company company = new Company();
        Optional<Company> optionalCompany = companyService.findById(companyId);
        optionalCompany.ifPresent(p -> company.setId(p.getId()));
        optionalCompany.ifPresent(p -> company.setName(p.getName()));
        optionalCompany.ifPresent(p -> company.setCostRate(p.getCostRate()));

        Integer kilowatts = (quantity / 1000);

        Consume consume = new Consume(user, company, kilowatts);
        Consume obj = service.insert(consume);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/user/{userId}/company/{companyId}/total", method = RequestMethod.GET)
    public ResponseEntity<Void> getTotal(
            @PathVariable("userId") Integer userId,
            @PathVariable("companyId") Integer companyId
    ) {

        User user = new User();
        Optional<User> optionalUser = userService.findById(userId);
        optionalUser.ifPresent(p -> user.setId(p.getId()));
        optionalUser.ifPresent(p -> user.setName(p.getName()));
        optionalUser.ifPresent(p -> user.setEmail(p.getEmail()));
        optionalUser.ifPresent(p -> user.setLimitValue(p.getLimitValue()));

        Company company = new Company();
        Optional<Company> optionalCompany = companyService.findById(companyId);
        optionalCompany.ifPresent(p -> company.setId(p.getId()));
        optionalCompany.ifPresent(p -> company.setName(p.getName()));
        optionalCompany.ifPresent(p -> company.setCostRate(p.getCostRate()));


        List<Consume> obj = service.findAll();


        Long total = obj.stream().mapToLong(Consume::getQuantity).sum();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setFrom("joaopedrokyros@gmail.com");
        msg.setSubject("Total Consume Client Today");
        msg.setSentDate(new Date(System.currentTimeMillis()));

        String message = "";
        message+="Hello, "+user.getName()+".";
        message+="\nToday you've used "+total+" kilowatt (kW) of eletric energy from "+ company.getName()
                +" with cost R$ "+company.getCostRate()+" reais/kW.";
        message+="\nTotally: R$"+(total * company.getCostRate())+" reais.";
        message+="\nYou have the goal, spend R$ "+user.getLimitValue()+" reais for month.";
        message+="\n\nThanks.";

        msg.setText(message);
        emailService.sendEmail(msg);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*
        {
            "user": {
                "id": 1,
                "name": "John White",
                "email": "johnwhite@email.com",
                "limitValue": 200
            },
           "company": {
                "id": 1,
                "name": "Cemig",
                "costRate": 1.27
            },
            "quantity": 200
        }
    */

}
