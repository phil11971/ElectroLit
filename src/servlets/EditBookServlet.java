package servlets;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.PublishingOfficeDAO;
import entities.AuthorEntity;
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

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<Integer,String> dictionary = new HashMap<>();
            List<PublishingOfficeEntity> poList = PublishingOfficeDAO.select();
            for(PublishingOfficeEntity po : poList){
                dictionary.put(po.getId_po(), po.getName());
            }
            request.setAttribute("idPOList",dictionary);

            request.setCharacterEncoding("UTF-8");
            String oper = request.getParameter("action");
            ArrayList<AuthorEntity> authorEntityArrayList = AuthorDAO.select();
            request.setAttribute("authors", authorEntityArrayList);
            BookEntity book;
            if (oper.equals("insert")) {
                book = new BookEntity(0, "", 0, 0, new BigDecimal(0), 0, new ArrayList<>());
                request.setAttribute("pagename", "Добавление книги");
                request.setAttribute("action", "insert");
            } else {
                long id = Long.parseLong(request.getParameter("id_b"));
                book = BookDAO.selectByID(id);
                request.setAttribute("idpo", book.getId_po());
                request.setAttribute("pagename", "Изменение книги");
                request.setAttribute("action", "update");
            }
            request.setAttribute("book", book);

            request.getRequestDispatcher("jsp/editBook.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String oper = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int poId = Integer.parseInt(request.getParameter("poId"));
        String[] authors = request.getParameterValues("authors");
        ArrayList<AuthorEntity> authorEntities = new ArrayList<>();
        if (authors != null)
            for (String author : authors) {
                authorEntities.add(new AuthorEntity(Integer.valueOf(author), null, null, null, null, null));
            }

        BookEntity bookEntity = new BookEntity(id, title, year, pages, price, poId, authorEntities);
        try {
            if (oper.equals("insert"))
                BookDAO.insert(bookEntity);
            else
                BookDAO.update(bookEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
