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

@WebServlet("/updatePublishingOffice")
public class UpdatePublishingOfficeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title","Измнение записи");
        request.getRequestDispatcher("jsp/updatePublishingOffice.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int poId = Integer.parseInt(request.getParameter("poId"));
        String namePO = request.getParameter("namePO");
        String adr = request.getParameter("adr");
        PublishingOfficeEntity poe = new PublishingOfficeEntity(poId, namePO, adr);
        try {
            PublishingOfficeDAO.update(poe);
        }
        catch (SQLException e) {}
    }

}
