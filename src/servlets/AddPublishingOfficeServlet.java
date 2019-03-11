package servlets;

import dao.PublishingOfficeDAO;
import entities.PublishingOfficeEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addPublishingOffice")
public class AddPublishingOfficeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title","Add publishing office");
        request.getRequestDispatcher("jsp/addPublishingOffice.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("namePO");
        String adr = request.getParameter("adr");
        PublishingOfficeEntity poe = new PublishingOfficeEntity(name, adr);
        try {
            PublishingOfficeDAO.insert(poe);
        }
        catch (SQLException e) {}
    }
}
