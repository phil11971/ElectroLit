package dao;

import entities.ChapterEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ChapterDAO {

    public static List<ChapterEntity> select() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM public.\"Chapter\"");
            List list = new LinkedList<ChapterEntity>();

            while (set.next())
                list.add(new ChapterEntity(
                                set.getInt("id_c"),
                                set.getString("name"),
                                set.getInt("id_b")
                        )
                );
            return list;
        }
    }

    public static void insert(ChapterEntity book) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"Chapter\"\n" +
                    "(id_c, name, id_b)\n" +
                    "\tVALUES  (nextval('\"ChapterSeq\"'), ?, ?);");
            statement.setString(1, book.getName());
            statement.setInt(2, book.getId_b());
            statement.executeUpdate();
        }
    }

    public static void delete(ChapterEntity book) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.\"Chapter\" WHERE \"name\" = ?");
            statement.setString(1, book.getName());
            statement.executeUpdate();
        }
    }

    public static void update(ChapterEntity oldT1, ChapterEntity newT2) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"Chapter\" SET \"name\" = ? WHERE \"name\" = ?");
            statement.setString(1, newT2.getName());
            statement.setString(2, oldT1.getName());
            statement.executeUpdate();
        }
    }

}
