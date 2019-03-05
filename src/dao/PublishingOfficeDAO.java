package dao;

import entities.PublishingOfficeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PublishingOfficeDAO {

    public static List<PublishingOfficeEntity> select() throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM public.\"PublishingOffice\"");
            List list = new LinkedList<PublishingOfficeEntity>();

            while (set.next())
                list.add(new PublishingOfficeEntity(
                                set.getInt("id_po"),
                                set.getString("name"),
                                set.getString("legal_adr")
                        )
                );
            return list;
        }
    }

    public static void insert(PublishingOfficeEntity publishingOffice) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"PublishingOffice\"\n" +
                    "\t\t\t(id_po, name)\n" +
                    "\tVALUES  (nextval('\"POSeq\"'), ?);");
            statement.setString(1, publishingOffice.getName());
            statement.executeUpdate();
        }
    }

    public static void delete(PublishingOfficeEntity publishingOffice) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.\"PublishingOffice\" WHERE \"id_po\" = ?");
            statement.setInt(1, publishingOffice.getId_po());
            statement.executeUpdate();
        }
    }

    public static void update(PublishingOfficeEntity oldT1, PublishingOfficeEntity newT2) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"PublishingOffice\" SET \"name\" = ? WHERE \"name\" = ?");
            statement.setString(1, newT2.getName());
            statement.setString(2, oldT1.getName());
            statement.executeUpdate();
        }
    }

}
