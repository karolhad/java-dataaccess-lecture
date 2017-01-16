package jdbc;

import dto.Author;
import dto.Book;
import dto.Subject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDao {

   List<Book> find(String title, String authorLastName, String subject) throws SQLException {

      try (Connection connection = createConnection();
           PreparedStatement statement = connection.prepareStatement(prepareQuery(title, authorLastName, subject))
      ) {
         setParams(statement, title, authorLastName, subject);

         try (ResultSet rs = statement.executeQuery()) {

            List<Book> results = new LinkedList<>();

            while (rs.next()) {
               results.add(new Book(rs.getInt("book_id"),
                           rs.getString("title"),
                           new Subject(
                                 rs.getInt("subject_id"),
                                 rs.getString("subject")
                           ),
                           new Author(
                                 rs.getInt("author_id"),
                                 rs.getString("first_name"),
                                 rs.getString("last_name")
                           )
                     )
               );

            }
            return results;
         }
      }
   }

   private void setParams(PreparedStatement statement, String title, String authorLastName, String subject) throws SQLException {
      int paramIndex = 1;
      if (title != null) {
         statement.setString(paramIndex++, title);
      }
      if (authorLastName != null) {
         statement.setString(paramIndex++, authorLastName);
      }
      if (subject != null) {
         statement.setString(paramIndex, subject);
      }
   }

   private String prepareQuery(String title, String authorLastName, String subject) {
      String sql = "SELECT books.id as book_id, title, authors.id as author_id, first_name, last_name, subjects.id as subject_id, subject FROM books " +
            "INNER JOIN authors ON books.author_id = authors.id " +
            "INNER JOIN subjects ON books.subject_id = subjects.id ";

      List<String> whereConditions = Stream.of(
            addParamToQuery("title", title),
            addParamToQuery("last_name", authorLastName),
            addParamToQuery("subject", subject)).
            filter(Objects::nonNull).collect(Collectors.toList());

      if (!whereConditions.isEmpty()) {
         sql += "WHERE " + String.join(" AND ", whereConditions);
      }

      return sql + " ORDER BY books.id";
   }

   private String addParamToQuery(String fieldName, String value) {
      return value != null ? fieldName + "= ?" : null;
   }

   private Connection createConnection() throws SQLException {
      return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/bookstore",
            "postgres", "password1");
   }
}
