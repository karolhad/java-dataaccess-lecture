import dto.Book;
import dto.Customer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * Created by karol on 14/01/2017.
 */
public class BookDao {

    List<Book> find(String title, String authorLastName, String subject) throws SQLException {

        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(prepareQuery(title, authorLastName, subject))
        ) {
            setParams(statement, title, authorLastName, subject);

            try (ResultSet resultSet = statement.executeQuery()) {

                List<Book> results = new LinkedList<>();

                Integer currentBookId = null;
                Book currentBook;
                while (resultSet.next()) {
                    Integer bookId = resultSet.getInt("books.id");
                    if (!bookId.equals(currentBookId)) {
                        currentBook = new
                        results.add(book);
                    }



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
        String sql = "SELECT * FROM books " +
                "INNER JOIN authors ON books.author_id = authors.id " +
                "INNER JOIN subjects ON books.subject_id = subjects.id ";

        List<String> whereConditions = Stream.of(
                addParamToQuery("title", title),
                addParamToQuery("authorLastName", authorLastName),
                addParamToQuery("subject", subject)).
                filter(Objects::nonNull).collect(Collectors.toList());

        if (!whereConditions.isEmpty()) {
            sql += "WHERE " + String.join(" AND ", whereConditions);
        }

        return sql + " ORDER BY books.id";
    }

    private String addParamToQuery(String fieldName, String value) {
        return value != null ? fieldName + "= ?": null;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bookstore",
                "postgres", "password1");
    }
}
