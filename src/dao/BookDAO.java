package dao;

import entities.BookEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookDAO{

    public static List<BookEntity> select() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM public.\"Book\"");
            List list = new LinkedList<BookEntity>();

            while (set.next())
                list.add(new BookEntity(
                                set.getInt("id_b"),
                                set.getString("name"),
                                set.getInt("year_pub"),
                                set.getInt("cnt"),
                                set.getBigDecimal("price"),
                                set.getInt("id_po")
                                )
                );
            return list;
        }
    }

    public static void insert(BookEntity book) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"Book\"\n" +
                    "(id_b, name, year_pub, cnt, price, id_po)\n" +
                    "\tVALUES  (nextval('\"BookSeq\"'), ?, ?, ?, ?, ?);");
            statement.setString(1, book.getName());
            statement.setInt(2, book.getYear_pub());
            statement.setInt(3, book.getCnt());
            statement.setBigDecimal(4, book.getPrice());
            statement.setInt(5, book.getId_po());
            statement.executeUpdate();
        }
    }

    public static void delete(BookEntity book) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.\"Book\" WHERE \"id_b\" = ?");
            statement.setInt(1, book.getId_b());
            statement.executeUpdate();
        }
    }

    public static void update(BookEntity bookEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"Book\" SET \"name\" = ?, \"year_pub\"  = ?, \"cnt\" = ?, \"price\" = ?, \"id_po\" = ? WHERE \"id_b\" = ?");
            statement.setString(1, bookEntity.getName());
            statement.setInt(2, bookEntity.getYear_pub());
            statement.setInt(3, bookEntity.getCnt());
            statement.setBigDecimal(4, bookEntity.getPrice());
            statement.setInt(5, bookEntity.getId_po());
            statement.setInt(6, bookEntity.getId_b());

            statement.executeUpdate();
        }
    }

}
