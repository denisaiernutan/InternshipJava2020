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


    //not working
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(req.getParameter("pr"), Integer.parseInt(req.getParameter("qu")));

        //nu se pastreaza sesiunea!!!!
        HttpSession session = req.getSession(true);

        User user = (User) session.getAttribute("currentUserSession");

        if (user == null)
            resp.sendRedirect("invalidAddProduct.jsp");
        else {
            boolean response = ProductService.addProduct(user, product);
            if (!response) {
                resp.sendRedirect("invalidAddProduct.jsp");
            } else {
                resp.setHeader("Refresh", "0; URL=" + req.getContextPath());
            }
        }
    }
}


