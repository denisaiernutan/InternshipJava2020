package com.arobs.servlets;

import com.arobs.services.ProductService;
import com.arobs.services.UserService;
import com.arobs.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns="/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final Logger logger
            = LoggerFactory.getLogger(LoginServlet.class);


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user= new User(req.getParameter("un"), req.getParameter("pw"));

        user= UserService.login(user);
        if(user.isValid()){
            HttpSession session = req.getSession(false);
            session.setAttribute("currentSessionUser",user);
            session.setAttribute("productsList", ProductService.productsList);
            session.setAttribute("userProductsList", user.getProductsList());
            logger.info("user "+ user.getUsername() + "logged in");
            resp.sendRedirect("userLogged.jsp"); //logged-in page
        }
        else
        {
            resp.sendRedirect("invalidLogin.jsp");
        }

    }



}
