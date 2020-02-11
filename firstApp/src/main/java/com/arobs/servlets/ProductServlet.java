package com.arobs.servlets;

import com.arobs.entities.Product;
import com.arobs.entities.User;
import com.arobs.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/productServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(req.getParameter("pr"), Integer.parseInt(req.getParameter("qu")));
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("currentSessionUser");

        boolean response = ProductService.addProduct(user, product);
        if (!response) {
            resp.sendRedirect("invalidAddProduct.jsp");
        } else {
            session.setAttribute("userProductsList", user.getProductsList());
            resp.setHeader("Refresh", "0; URL=" + req.getContextPath()+"/userLogged.jsp");
        }
        //forward -- pastrez params
    }




}


