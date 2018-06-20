package com.energyControl.energycontrol.resource;

import com.energyControl.energycontrol.domains.Consume;
import com.energyControl.energycontrol.domains.User;
import com.energyControl.energycontrol.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/consumes")
public class ConsumeResource {

    @Autowired
    private ConsumeService service;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Consume consume){
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
