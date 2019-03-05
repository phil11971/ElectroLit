package servlets;

import dao.BookAuthorDAO;
import entities.BookAuthorEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookAuthor")
public class BookAuthorServlet extends HttpServlet {
    List<BookAuthorEntity> chapterEntityList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            chapterEntityList = BookAuthorDAO.select();
        }
        catch (SQLException e) {}
        for(BookAuthorEntity te : chapterEntityList) {
            ArrayList<String> s = new ArrayList<>();
            s.add(te.getId_b()+"");
            s.add(te.getId_a()+"");
            arrayLists.add(s);
        }
        request.setAttribute("pagename","BookAuthor");
        request.setAttribute("columnList",new String[]{"id книги","id автора"});
        request.setAttribute("tableList",arrayLists);
        request.getRequestDispatcher("jsp/bookAuthor.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("delete")) {
            long id_b = Long.parseLong(request.getParameter("id_b"));
            BookAuthorEntity publishingOfficeEntity = new BookAuthorEntity((int)id_b);
            try {
                BookAuthorDAO.delete(publishingOfficeEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }
}
