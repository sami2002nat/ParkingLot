package com.parkinglot.parkinglot.servlets.users;

import com.parkinglot.parkinglot.common.UserDto;
import com.parkinglot.parkinglot.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditUser.class.getName());

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("id"));
        UserDto user = usersBean.findUserById(userId);
        request.setAttribute("user", user);

        List<String> userGroups = usersBean.getAllUserGroups();
        request.setAttribute("allGroups", userGroups);
        List<String> userGroupsForUser = usersBean.getUserGroupsByUsername(user.getUsername());
        request.setAttribute("userGroups", userGroupsForUser);

        request.getRequestDispatcher("/WEB-INF/pages/users/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String[] userGroups = request.getParameterValues("user_groups");

        usersBean.updateUser(userId, username, email, password, userGroups);

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}