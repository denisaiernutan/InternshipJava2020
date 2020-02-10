package com.arobs.servlets;

import com.arobs.entities.Product;
import com.arobs.entities.User;
import com.arobs.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        ServletContext context= getServletContext();
        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/userLogged.jsp");
        requestDispatcher.forward(req,resp);
    }


    //delete product for user
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(req.getParameter("pr"), Integer.parseInt(req.getParameter("qu")));
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("currentSessionUser");

        boolean response=UserService.deleteProduct(user,product);
        if(!response){
            resp.sendRedirect("invalidDeleteProduct.jsp");
        }
        else
        {
            resp.sendRedirect("userLogged.jsp");
        }



    }
}
