package dao;

import entities.BookAuthorEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookAuthorDAO {

    public static List<BookAuthorEntity> select() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM public.\"BookAuthor\"");
            List list = new LinkedList<BookAuthorEntity>();

            while (set.next())
                list.add(new BookAuthorEntity(
                                set.getInt("id_b"),
                                set.getInt("id_a")
                        )
                );
            return list;
        }
    }

    public static void insert(BookAuthorEntity bookAuthor) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"BookAuthor\"(\n" +
                    "\tid_b, id_a)\n" +
                    "\tVALUES (?, ?);");
            statement.setInt(1, bookAuthor.getId_b());
            statement.setInt(2, bookAuthor.getId_a());
            statement.executeUpdate();

        }
    }

    public static void delete(BookAuthorEntity bookAuthor) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.\"BookAuthor\" WHERE \"id_b\" = ?");
            statement.setInt(1, bookAuthor.getId_b());
            statement.executeUpdate();
        }
    }

    public static void update(BookAuthorEntity oldT1, BookAuthorEntity newT2) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"BookAuthor\" SET \"id_b\" = ? WHERE \"id_b\" = ?");
            statement.setInt(1, newT2.getId_b());
            statement.setInt(2, oldT1.getId_b());
            statement.executeUpdate();
        }
    }
}
