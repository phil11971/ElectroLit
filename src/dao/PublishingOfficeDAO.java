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

    public static String getNamePOById(int id) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.\"PublishingOffice\" where id_po = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            String namePO = "";
            while (resultSet.next())
                namePO = resultSet.getString("name");
            return namePO;
        }
    }

    public static void insert(PublishingOfficeEntity publishingOffice) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"PublishingOffice\"\n" +
                    "\t\t\t(id_po, name, legal_adr)\n" +
                    "\tVALUES  (nextval('\"POSeq\"'), ?, ?);");
            statement.setString(1, publishingOffice.getName());
            statement.setString(2, publishingOffice.getLegal_adr());
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

    public static void update(PublishingOfficeEntity poe) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.\"PublishingOffice\" SET \"name\" = ?, \"legal_adr\" = ?  WHERE \"id_po\" = ?");
            statement.setString(1, poe.getName());
            statement.setString(2, poe.getLegal_adr());
            statement.setInt(3, poe.getId_po());
            statement.executeUpdate();
        }
    }

}
