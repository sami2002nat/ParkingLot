package com.parkinglot.parkinglot.entities;

import jakarta.persistence.*;

@Entity
public class CarPhoto {


    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Basic
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    byte[] fileContent;

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }


    @OneToOne
    private com.parkinglot.parkinglot.entities.Car Car;

    public com.parkinglot.parkinglot.entities.Car getCar() {
        return Car;
    }

    public void setCar(com.parkinglot.parkinglot.entities.Car car) {
        Car = car;
    }
}