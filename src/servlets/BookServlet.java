package servlets;

import dao.BookDAO;
import entities.BookEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    List<BookEntity> chapterEntityList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            chapterEntityList = BookDAO.select();
        }
        catch (SQLException e) {}
        for(BookEntity te : chapterEntityList) {
            ArrayList<String> s = new ArrayList<>();
            s.add(te.getId_b()+"");
            s.add(te.getName()+"");
            s.add(te.getYear_pub()+"");
            s.add(te.getCnt()+"");
            s.add(te.getPrice()+"");
            s.add(te.getId_po()+"");
            arrayLists.add(s);
        }
        request.setAttribute("pagename","Book");
        request.setAttribute("columnList",new String[]{"id книги","название","год","количество","цена", "id издательства"});
        request.setAttribute("tableList",arrayLists);
        request.getRequestDispatcher("jsp/book.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("delete")) {
            long id_b = Long.parseLong(request.getParameter("id_b"));
            BookEntity bookEntity = new BookEntity((int)id_b);
            try {
                BookDAO.delete(bookEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }
}
