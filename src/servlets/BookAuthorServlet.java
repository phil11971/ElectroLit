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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            List<BookAuthorEntity> chapterEntityList = BookAuthorDAO.select();
            for(BookAuthorEntity te : chapterEntityList) {
                ArrayList<String> s = new ArrayList<>();
                s.add(te.getId_b()+"");
                s.add(te.getId_a()+"");
                arrayLists.add(s);
            }
        }
        catch (SQLException e) {}

        request.setAttribute("pagename","Книга-Автор");
        request.setAttribute("columnList",new String[]{"#","id книги","id автора"});
        request.setAttribute("tableList",arrayLists);
        request.getRequestDispatcher("jsp/bookAuthor.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("delete")) {
            int id_b = Integer.parseInt(request.getParameter("id_b"));
            int id_a = Integer.parseInt(request.getParameter("id_a"));
            BookAuthorEntity bookAuthorEntity = new BookAuthorEntity(id_b, id_a);
            try {
                BookAuthorDAO.delete(bookAuthorEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }
}
