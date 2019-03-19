package servlets;

import dao.ChapterDAO;
import entities.ChapterEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chapter")
public class ChapterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        try {
            List<ChapterEntity> chapterEntityList = ChapterDAO.select();
            for(ChapterEntity te : chapterEntityList) {
                ArrayList<String> s = new ArrayList<>();
                s.add(te.getId_c()+"");
                s.add(te.getName()+"");
                s.add(te.getId_b()+"");
                arrayLists.add(s);
            }
        }
        catch (SQLException e) {}

        request.setAttribute("pagename","Chapter");
        request.setAttribute("columnList",new String[]{"#","id главы","название","id книги"});
        request.setAttribute("tableList",arrayLists);
        request.getRequestDispatcher("jsp/chapter.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("delete")) {
            long id_b = Long.parseLong(request.getParameter("id_b"));
            ChapterEntity chapterEntity = new ChapterEntity((int)id_b);
            try {
                ChapterDAO.delete(chapterEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }
}
