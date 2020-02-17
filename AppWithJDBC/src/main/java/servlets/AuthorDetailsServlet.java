package servlets;


import entities.Author;
import entities.Book;
import services.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/authorDetailsServlet")
public class AuthorDetailsServlet extends HttpServlet {

    AuthorService authorService= new AuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String authorName = req.getParameter("an");
        try {
            Author author = authorService.getAuthorByName(authorName);
            session.setAttribute("author", author);
            resp.sendRedirect("authorDetails.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
