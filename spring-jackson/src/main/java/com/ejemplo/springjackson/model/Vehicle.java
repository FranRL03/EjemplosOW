package com.ejemplo.springjackson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    @JsonProperty("cc")
    private Double cubicCentimeters;

    private Integer releasedYear;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Vehicle(){

    }

    public Vehicle(String modelo, Double cubicCentimeters, int releasedYear, Customer customer) {
        this.modelo = modelo;
        this.cubicCentimeters = cubicCentimeters;
        this.releasedYear = releasedYear;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getCubicCentimeters() {
        return cubicCentimeters;
    }

    public void setCubicCentimeters(Double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", cubicCentimeters=" + cubicCentimeters +
                ", releasedYear=" + releasedYear +
                '}';
    }
}
