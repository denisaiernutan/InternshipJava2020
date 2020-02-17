package servlets;


import entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@WebServlet(urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {

    BookService bookService= new BookService();

    private static final Logger logger
            = LoggerFactory.getLogger(BookServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (req.getParameter("bn") != null) {
            doDelete(req, resp);
            resp.sendRedirect("firstPage.jsp");
        } else {
            try {
                session.setAttribute("bookList", bookService.getAllBooks());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = Date.valueOf(req.getParameter("dp"));
        Book book = new Book(req.getParameter("nm"), date, Integer.parseInt(req.getParameter("ch")));
        String authorName = req.getParameter("ah");

        try {
            bookService.insertBook(book, authorName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("firstPage.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bookName = req.getParameter("bn");
        try {
            bookService.deleteBookByName(bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
