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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/updateAuthor")
public class UpdateAuthorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title","Измнение записи");
        request.getRequestDispatcher("jsp/updateAuthor.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int aId = Integer.parseInt(request.getParameter("aId"));
        String lname = request.getParameter("lname");
        String fname = request.getParameter("fname");
        String patr = request.getParameter("patr");
        String mail = request.getParameter("mail");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"), formatter);

        AuthorEntity authorEntity = new AuthorEntity(aId, lname, fname, patr, mail, dob.toString());
        try {
            AuthorDAO.update(authorEntity);
        }
        catch (SQLException e) {}
    }

}
