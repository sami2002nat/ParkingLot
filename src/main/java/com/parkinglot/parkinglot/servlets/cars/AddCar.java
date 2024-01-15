package com.parkinglot.parkinglot.servlets.cars;

import com.parkinglot.parkinglot.common.UserDto;
import com.parkinglot.parkinglot.ejb.CarsBean;
import com.parkinglot.parkinglot.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_CARS"}))
@WebServlet(name = "AddCar", value = "/AddCar")
public class AddCar extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<UserDto> users = null;
        try {
            users = usersBean.findAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/cars/addCar.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long userId = Long.parseLong(request.getParameter("owner_id"));

        carsBean.createCar(licensePlate,parkingSpot,userId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}