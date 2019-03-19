package servlets;

import dao.AuthorDAO;
import entities.AuthorEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            List<AuthorEntity> chapterEntityList = AuthorDAO.select();
            for(AuthorEntity te : chapterEntityList) {
                ArrayList<String> s = new ArrayList<>();
                s.add(te.getId_a()+"");
                s.add(te.getLname()+"");
                s.add(te.getFname()+"");
                s.add(te.getPatr()+"");
                s.add(te.getMail()+"");
                s.add(te.getDob()+"");

                arrayLists.add(s);
            }
        }
        catch (SQLException e) {}

        request.setAttribute("pagename","Author");
        request.setAttribute("columnList",new String[]{"#","id автора", "Фамилия", "Имя", "Отчество", "Мыло", "Дата рождения"});
        request.setAttribute("tableList",arrayLists);
        request.getRequestDispatcher("jsp/author.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("delete")) {
            long id_a = Long.parseLong(request.getParameter("id_a"));
            AuthorEntity publishingOfficeEntity = new AuthorEntity((int)id_a);
            try {
                AuthorDAO.delete(publishingOfficeEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }
}
