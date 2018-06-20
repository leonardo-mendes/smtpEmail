package com.energyControl.energycontrol.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Consume implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    private Integer quantity;

    private Double cost;

    private Date timeCurrency;

    public Consume() {
    }

    public Consume(User user, Company company, Integer quantity) {
        this.user = user;
        this.company = company;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getTimeCurrency() {
        return timeCurrency;
    }

    public void setTimeCurrency(Date timeCurrency) {
        this.timeCurrency = timeCurrency;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss");
        String message = sdf.format(this.timeCurrency)+"\n\n";
        message+="Hello, "+this.getUser().getName()+".";
        message+="\nNow you've used "+this.getQuantity()+" kilowatt (kW) of eletric energy from "+ this.getCompany().getName()+" with cost R$ "+this.getCompany().getCostRate()+" reais/kW.";
        message+="\nTotally: R$"+(this.getQuantity() * this.getCompany().getCostRate())+" reais.";
        message+="\nYou have the goal, spend R$ "+this.getUser().getLimitValue()+" reais for month.";
        message+="\n\nThanks.";
        return message;
    }
}
