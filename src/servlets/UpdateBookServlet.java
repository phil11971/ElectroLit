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

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {

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

        request.setAttribute("title","Изменение книги");
        request.setAttribute("idPOList",dictionary);
        request.getRequestDispatcher("jsp/updateBook.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int poId = Integer.parseInt(request.getParameter("poId"));
        BookEntity bookEntity = new BookEntity(id, title, year, pages, price, poId);
        try {
            BookDAO.update(bookEntity);
        }
        catch (SQLException e) {}
    }

}
