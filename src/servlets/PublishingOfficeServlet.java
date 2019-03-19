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

@WebServlet("/publishingOffice")
public class PublishingOfficeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            List<PublishingOfficeEntity> publishingOfficeEntityList = PublishingOfficeDAO.select();
            for(PublishingOfficeEntity te : publishingOfficeEntityList) {
                ArrayList<String> s = new ArrayList<>();
                s.add(te.getId_po()+"");
                s.add(te.getName()+"");
                s.add(te.getLegal_adr()+"");
                arrayLists.add(s);
            }
        }
        catch (SQLException e) {}

        request.setAttribute("pagename","PublishingOffice");
        request.setAttribute("columnList",new String[]{"#","id издательства","название","юридический адрес"});
        request.setAttribute("tableList",arrayLists);
        request.getRequestDispatcher("jsp/publishingOffice.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("delete")) {
            long id_po = Long.parseLong(request.getParameter("id_po"));
            PublishingOfficeEntity publishingOfficeEntity = new PublishingOfficeEntity((int)id_po);
            try {
                PublishingOfficeDAO.delete(publishingOfficeEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }

}
