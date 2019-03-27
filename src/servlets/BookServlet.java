package servlets;

import dao.BookDAO;
import dao.PublishingOfficeDAO;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            List<BookEntity> bookEntityList = BookDAO.select();
            for(BookEntity book : bookEntityList) {
                ArrayList<String> s = new ArrayList<>();
                s.add(book.getId_b()+"");
                s.add(book.getName()+"");
                s.add(book.getYear_pub()+"");
                s.add(book.getCnt()+"");
                s.add(book.getPrice()+"");
                String namePO = PublishingOfficeDAO.getNamePOById(book.getId_po());
                s.add(namePO);
                arrayLists.add(s);
            }
        }
        catch (SQLException e) {}

        request.setAttribute("pagename","Книга");
        request.setAttribute("columnList",new String[]{"#","id книги","название","год","количество","цена", "издательство"});
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
