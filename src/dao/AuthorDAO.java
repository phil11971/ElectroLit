package dao;

import entities.AuthorEntity;

import java.sql.*;
import java.time.LocalDate;
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
                                set.getString("dob")
                        )
                );
            return list;
        }
    }

    public static void insert(AuthorEntity author) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"Author\"(\n" +
                    "\tid_a, lname, fname, patr, mail, dob)\n" +
                    "\tVALUES (nextval('\"AuthorSeq\"'), ?, ?, ?, ?, to_date(?, 'yyyy-MM-dd'));");
            statement.setString(1, author.getLname());
            statement.setString(2, author.getFname());
            statement.setString(3, author.getPatr());
            statement.setString(4, author.getMail());
            statement.setString(5, author.getDob().toString());

            statement.executeUpdate();
        }
    }

    public static void delete(AuthorEntity author) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.\"Author\" WHERE \"id_a\" = ?");
            statement.setInt(1, author.getId_a());
            statement.executeUpdate();
        }
    }

    public static void update(AuthorEntity authorEntity) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"Author\" SET \"lname\" = ?, \"fname\" = ?, \"patr\" = ?, \"mail\" = ?, \"dob\" = to_date(?, 'yyyy-MM-dd') WHERE \"id_a\" = ?");
            statement.setString(1, authorEntity.getLname());
            statement.setString(2, authorEntity.getFname());
            statement.setString(3, authorEntity.getPatr());
            statement.setString(4, authorEntity.getMail());
            statement.setString(5, authorEntity.getDob());
            statement.setInt(6, authorEntity.getId_a());

            statement.executeUpdate();
        }
    }

}
