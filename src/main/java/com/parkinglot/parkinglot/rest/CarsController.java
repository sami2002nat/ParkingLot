package com.parkinglot.parkinglot.rest;

import com.parkinglot.parkinglot.common.CarDto;
import com.parkinglot.parkinglot.ejb.CarsBean;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@RolesAllowed("READ_CARS")
@Path("/Cars")
public class CarsController {

    @Inject
    CarsBean carsBean;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarDto> findAllCars() throws Exception {
        return carsBean.findAllCars();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CarDto findCar(@PathParam("id") Long id) {
        return carsBean.findById(id);
    }
}