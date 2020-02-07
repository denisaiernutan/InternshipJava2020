package com.arobs.servlets;

import com.arobs.entities.User;
import com.arobs.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {

    //update user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newUsername = req.getParameter("un");
        String newFirstName = req.getParameter("fn");
        String newLastName = req.getParameter("ln");
        User updateUser = new User();
        updateUser.setLastName(newLastName);
        updateUser.setFirstName(newFirstName);
        updateUser.setUsername(newUsername);
        User oldUser = (User) req.getSession().getAttribute("currentSessionUser");
        UserService.updateUser(oldUser, updateUser);

        resp.sendRedirect("userLogged.jsp");

    }
}
