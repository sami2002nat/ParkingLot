package com.parkinglot.parkinglot.servlets.users;

import com.parkinglot.parkinglot.common.UserDto;
import com.parkinglot.parkinglot.ejb.InvoiceBean;
import com.parkinglot.parkinglot.ejb.UsersBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@DeclareRoles({"READ_USERS","WRITE_USERS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS"})})
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        List<UserDto> users = null;
        try {
            users = usersBean.findAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(!invoiceBean.getUserIds().isEmpty()) {
            Collection<String> usernames = usersBean.findUsernameByUserIds(invoiceBean.getUserIds());
            request.setAttribute("invoices", usernames);
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/users/users.jsp").forward(request,
                response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String[] userIdsAsString = request.getParameterValues("user_ids");
        if(userIdsAsString != null)
        {

            List<Long> userIds = new ArrayList<>();
            for ( String userIdAsString : userIdsAsString)
            {
                userIds.add(Long.parseLong(userIdAsString));
            }
            invoiceBean.getUserIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath() + "/Users");
    }
}