package com.energyControl.energycontrol;

import com.energyControl.energycontrol.domains.Company;
import com.energyControl.energycontrol.domains.Consume;
import com.energyControl.energycontrol.domains.User;
import com.energyControl.energycontrol.service.CompanyService;
import com.energyControl.energycontrol.service.ConsumeService;
import com.energyControl.energycontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnergycontrolApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ConsumeService consumeService;

    public static void main(String[] args) {
        SpringApplication.run(EnergycontrolApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        Company company = new Company("Cemig", 1.27);
        companyService.insert(company);
        company.setId(1);

        User user = new User("John White", "leonardo@webmendes.com", company,  200.0);
        userService.insert(user);
        user.setId(1);

        Consume consume = new Consume(user, company, 200);
        consumeService.insert(consume);
    }
}
