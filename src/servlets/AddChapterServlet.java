package servlets;

import dao.BookDAO;
import dao.ChapterDAO;
import dao.PublishingOfficeDAO;
import entities.BookEntity;
import entities.ChapterEntity;
import entities.PublishingOfficeEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addChapter")
public class AddChapterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> idBookList=new ArrayList<>();
        try {
            List<BookEntity> bookList = BookDAO.select();
            for(BookEntity book : bookList){
                idBookList.add(book.getId_b()+"");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("title","Add chapter");
        request.setAttribute("idBookList",idBookList);
        request.getRequestDispatcher("jsp/addChapter.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        ChapterEntity chapterEntity = new ChapterEntity(title, bookId);

        try {
            ChapterDAO.insert(chapterEntity);
        }
        catch (SQLException e) {}
    }

}
