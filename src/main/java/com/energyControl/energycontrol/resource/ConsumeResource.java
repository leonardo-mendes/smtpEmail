package com.energyControl.energycontrol.resource;

import com.energyControl.energycontrol.domains.Company;
import com.energyControl.energycontrol.domains.Consume;
import com.energyControl.energycontrol.domains.User;
import com.energyControl.energycontrol.service.CompanyService;
import com.energyControl.energycontrol.service.ConsumeService;
import com.energyControl.energycontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        Consume consume = new Consume(user, company, quantity);
        Consume obj = service.insert(consume);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
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
