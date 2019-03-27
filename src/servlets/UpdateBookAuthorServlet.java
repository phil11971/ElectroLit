package servlets;

import dao.AuthorDAO;
import dao.BookAuthorDAO;
import dao.BookDAO;
import entities.AuthorEntity;
import entities.BookAuthorEntity;
import entities.BookEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/updateBookAuthor")
public class UpdateBookAuthorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer,String> books = new HashMap<>();
        Map<Integer,String> authors = new HashMap<>();
        try {
            List<BookEntity> bookEntities = BookDAO.select();
            List<AuthorEntity> authorEntities = AuthorDAO.select();
            for(BookEntity book : bookEntities){
                books.put(book.getId_b(), book.getName());
            }
            for(AuthorEntity author : authorEntities){
                authors.put(author.getId_a(), author.getFname() + " " + author.getLname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("title","Изменение книги-автора");
        request.setAttribute("bookList", books);
        request.setAttribute("authorList", authors);
        request.getRequestDispatcher("jsp/updateBookAuthor.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        String desc = request.getParameter("desc");

        BookAuthorEntity bookAuthorEntity = new BookAuthorEntity(bookId, authorId, desc);

        try {
            BookAuthorDAO.update(bookAuthorEntity);
        }
        catch (SQLException e) {}
    }

}
