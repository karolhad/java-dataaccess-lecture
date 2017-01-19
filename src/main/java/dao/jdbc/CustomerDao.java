package dao.jdbc;

import dto.Customer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static dao.jdbc.ConnectionProvider.createConnection;

class CustomerDao {

    List<Customer> findAll() throws SQLException {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from customers")
        ) {
            List<Customer> results = new LinkedList<>();

            while (resultSet.next()) {
                results.add(new Customer(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }
            return results;
        }
    }

    List<Customer> findByFirstName(String firstName) throws SQLException {
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement("select * from customers where first_name=?");
        ) {
            statement.setString(1, firstName);
            try (ResultSet resultSet = statement.executeQuery()) {

                List<Customer> results = new LinkedList<>();

                while (resultSet.next()) {
                    results.add(new Customer(resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")));
                }
                return results;
            }
        }
    }

    Integer create(String firstName, String lastName) throws SQLException {
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement("insert into customers(first_name, last_name) values (?, ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            throw new IllegalStateException("Object not inserted");
        }
    }


}
