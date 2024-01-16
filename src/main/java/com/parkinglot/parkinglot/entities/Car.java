package com.parkinglot.parkinglot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@Entity
public class Car {


    @Size(min = 3, max = 100)
    @Column(unique = true, nullable = false, length = 100)
    String licensePlate;
    @Size(min = 3, max = 100)
    @Column(unique = true, nullable = false, length = 100)
    String parkingSpot;

    @ManyToOne
    private User owner;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "Car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarPhoto photo;


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public CarPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(CarPhoto photo) {
        this.photo = photo;
    }
}