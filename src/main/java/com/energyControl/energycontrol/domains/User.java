package com.energyControl.energycontrol.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique=true)
    private String email;

    private Double limitValue;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Consume> consume;

    public User() {
    }

    public User(String name, String email, Company company, Double limitValue) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.limitValue = limitValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Double limitValue) {
        this.limitValue = limitValue;
    }
}
