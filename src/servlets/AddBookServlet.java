package servlets;

import dao.BookDAO;
import dao.PublishingOfficeDAO;
import entities.BookEntity;
import entities.PublishingOfficeEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer,String> dictionary = new HashMap<>();
        try {
            List<PublishingOfficeEntity> poList = PublishingOfficeDAO.select();
            for(PublishingOfficeEntity po : poList){
                dictionary.put(po.getId_po(), po.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("title","Добавить книгу");
        request.setAttribute("idPOList",dictionary);
        request.getRequestDispatcher("jsp/addBook.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int poId = Integer.parseInt(request.getParameter("poId"));
        BookEntity bookEntity = new BookEntity(title, year, pages, price, poId);
        try {
            BookDAO.insert(bookEntity);
        }
        catch (SQLException e) {}
    }
}
