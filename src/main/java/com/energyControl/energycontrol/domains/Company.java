package com.energyControl.energycontrol.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double costRate;

    @OneToMany(mappedBy="company")
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy="company")
    @JsonIgnore
    private List<Consume> consume;

    public Company() {
    }

    public Company(String name, Double costRate) {
        this.name = name;
        this.costRate = costRate;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    public Double getCostRate() {
        return costRate;
    }

    public void setCostRate(Double costRate) {
        this.costRate = costRate;
    }
}
