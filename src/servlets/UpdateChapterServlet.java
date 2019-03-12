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
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateChapter")
public class UpdateChapterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> idBookList = new ArrayList<>();
        try {
            List<BookEntity> bookList = BookDAO.select();
            for(BookEntity book : bookList){
                idBookList.add(book.getId_b()+"");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("title","Измнение записи");
        request.setAttribute("idBookList",idBookList);
        request.getRequestDispatcher("jsp/updateChapter.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        String title = request.getParameter("title");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        ChapterEntity chapterEntity = new ChapterEntity(chapterId, title, bookId);

        try {
            ChapterDAO.update(chapterEntity);
        }
        catch (SQLException e) {}
    }

}
