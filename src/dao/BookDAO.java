package dao;

import entities.AuthorEntity;
import entities.BookEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public static List<BookEntity> selectAll() throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery("SELECT * \n" +
                    "FROM public.\"Book\" \n" +
                    "left JOIN public.\"BookAuthor\" \n" +
                    "using (id_b) \n" +
                    "left JOIN public.\"Author\" \n" +
                    "using (id_a) \n" +
                    "order by id_b;");
            Integer id = Integer.valueOf(0);
            List<BookEntity> books = new ArrayList<BookEntity>();
            while (set.next()) {
                if (id != set.getInt("id_b")) {
                    books.add(new BookEntity(
                            set.getInt("id_b"),
                            set.getString("name"),
                            set.getInt("year_pub"),
                            set.getInt("cnt"),
                            set.getBigDecimal("price"),
                            set.getInt("id_po"),
                            new ArrayList<AuthorEntity>()
                            )
                    );
                    id = books.get(books.size() - 1).getId_b();
                }
                if (set.getInt("id_a") > 0) {
                    AuthorEntity authorEntity = new AuthorEntity(
                            set.getInt("id_a"),
                            set.getString("lname"),
                            set.getString("fname"),
                            set.getString("patr"),
                            set.getString("mail"),
                            set.getString("dob"));
                    if (!books.get(books.size() - 1).getAuthorEntities().contains(authorEntity))
                        books.get(books.size() - 1).getAuthorEntities().add(authorEntity);

                }
            }
            return books;
        }
    }

    public static String getNameBookById(int id) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.\"Book\" where id_b = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            String nameBook = "";
            while (resultSet.next())
                nameBook = resultSet.getString("name");
            return nameBook;
        }
    }

    public static BookEntity selectByID(long bookId) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.\"Book\" WHERE id_b = ?");
            statement.setLong(1, bookId);
            ResultSet set = statement.executeQuery();
            set.next();
            BookEntity book = new BookEntity(
                    set.getInt("id_b"),
                    set.getString("name"),
                    set.getInt("year_pub"),
                    set.getInt("cnt"),
                    set.getBigDecimal("price"),
                    set.getInt("id_po"),
                    null
            );
            statement = connection.prepareStatement("SELECT * FROM public.\"Book\" b left JOIN public.\"BookAuthor\" using (id_b) left JOIN public.\"Author\" using (id_a) where b.id_b=?;");
            statement.setLong(1, bookId);
            set = statement.executeQuery();
            ArrayList authors = new ArrayList<AuthorEntity>();
            statement.setLong(1, bookId);
            set = statement.executeQuery();
            while (set.next()) {
                authors.add(new AuthorEntity(
                        set.getInt("id_a"),
                        set.getString("lname"),
                        set.getString("fname"),
                        set.getString("patr"),
                        set.getString("mail"),
                        set.getString("dob")
                                            )
                );
            }
            book.setAuthorEntities(authors);
            return book;
        }
    }

    public static void insert(BookEntity book) throws SQLException {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"Book\"\n" +
                    "(id_b, name, year_pub, cnt, price, id_po)\n" +
                    "\tVALUES  (nextval('\"BookSeq\"'), ?, ?, ?, ?, ?) RETURNING id_b");
            statement.setString(1, book.getName());
            statement.setInt(2, book.getYear_pub());
            statement.setInt(3, book.getCnt());
            statement.setBigDecimal(4, book.getPrice());
            statement.setInt(5, book.getId_po());
            ResultSet rs = statement.executeQuery();
            int id = 0;
            while(rs.next()){
                id = rs.getInt(1);
            }

            book.setId_b(id);
            insertManyToManyFields(book, connection);
        }
    }

    private static void insertManyToManyFields(BookEntity bookEntity, Connection connection) throws SQLException {
        PreparedStatement statement;
        if (bookEntity.getAuthorEntities().size() > 0) {
            statement = connection.prepareStatement("INSERT INTO public.\"BookAuthor\" (id_a,id_b) VALUES(?,?)");
            for (AuthorEntity author : bookEntity.getAuthorEntities()) {
                statement.setInt(1, author.getId_a());
                statement.setInt(2, bookEntity.getId_b());
                statement.addBatch();
            }
            statement.executeBatch();
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

            statement = connection.prepareStatement("DELETE FROM public.\"BookAuthor\" WHERE id_b=?");
            statement.setLong(1, bookEntity.getId_b());
            statement.executeUpdate();
            insertManyToManyFields(bookEntity, connection);
        }
    }

}
