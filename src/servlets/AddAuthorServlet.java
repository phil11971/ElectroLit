package servlets;

import dao.AuthorDAO;
import dao.PublishingOfficeDAO;
import entities.AuthorEntity;
import entities.PublishingOfficeEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@WebServlet("/addAuthor")
public class AddAuthorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title","Добавить автора");
        request.getRequestDispatcher("jsp/addAuthor.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String lname = request.getParameter("lname");
        String fname = request.getParameter("fname");
        String patr = request.getParameter("patr");
        String mail = request.getParameter("mail");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"), formatter);

        AuthorEntity authorEntity = new AuthorEntity(lname, fname, patr, mail, dob.toString());
        try {
            AuthorDAO.insert(authorEntity);
        }
        catch (SQLException e) {}
    }

}
