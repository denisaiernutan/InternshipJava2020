package servlets;


import entities.Book;
import services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
//        String action=(String) session.getAttribute("action");
//        if(action==null)
//        try {
//            session.setAttribute("bookList", BookService.getAllBooks());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        else {
//            doDelete(req,resp);
//        }

        try {
            session.setAttribute("bookList", BookService.getAllBooks());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = Date.valueOf(req.getParameter("dp"));
        Book book = new Book(req.getParameter("nm"), date, Integer.parseInt(req.getParameter("ch")));

        try {
            BookService.insertBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("firstPage.jsp");
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session= req.getSession();
//        String bookName=(String) session.getAttribute("bn");
//        try {
//            BookService.deleteBookByName(bookName);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
