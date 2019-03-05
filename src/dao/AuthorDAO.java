package dao;

import entities.AuthorEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AuthorDAO {

    public static List<AuthorEntity> select() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM public.\"Author\"");
            List list = new LinkedList<AuthorEntity>();

            while (set.next())
                list.add(new AuthorEntity(
                                set.getInt("id_a"),
                                set.getString("lname"),
                                set.getString("fname"),
                                set.getString("patr"),
                                set.getString("mail"),
                                set.getDate("dob")
                        )
                );
            return list;
        }
    }

    public static void insert(AuthorEntity author) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"Author\"(\n" +
                    "\tid_a, lname, fname)\n" +
                    "\tVALUES (nextval('\"AuthorSeq\"'), ?, ?);");
            statement.setString(1, author.getLname());
            statement.setString(2, author.getFname());
            statement.executeUpdate();
        }
    }

    public static void delete(AuthorEntity author) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.\"Author\" WHERE \"lname\" = ?");
            statement.setString(1, author.getLname());
            statement.executeUpdate();
        }
    }

    public static void update(AuthorEntity oldT1, AuthorEntity newT2) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"Author\" SET \"lname\" = ? WHERE \"lname\" = ?");
            statement.setString(1, newT2.getLname());
            statement.setString(2, oldT1.getLname());
            statement.executeUpdate();
        }
    }

}
