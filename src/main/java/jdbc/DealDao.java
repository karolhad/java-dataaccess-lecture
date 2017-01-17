package jdbc;


import dto.Account;
import dto.Customer;
import dto.Deal;
import dto.Instrument;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DealDao {

   List<Deal> find(String instrumentName, String customerLastName, String accountName) throws SQLException {

      try (Connection connection = createConnection();
           PreparedStatement statement = connection.prepareStatement(prepareQuery(instrumentName, customerLastName, accountName))
      ) {
         setParams(statement, instrumentName, customerLastName, accountName);

         try (ResultSet rs = statement.executeQuery()) {

            List<Deal> results = new LinkedList<>();

            while (rs.next()) {
               results.add(new Deal(rs.getInt("deal_id"),
                     new Account(
                           rs.getInt("account_id"),
                           rs.getString("account_name"),
                           new Customer(
                                 rs.getInt("customer_id"),
                                 rs.getString("first_name"),
                                 rs.getString("last_name")
                           )
                     ),
                     new Instrument(
                           rs.getInt("instrument_id"),
                           rs.getString("instrument_name")
                     ),
                     rs.getTimestamp("open_timestamp"),
                     rs.getTimestamp("close_timestamp"),
                     new BigDecimal(rs.getString("open_price")),
                     new BigDecimal(rs.getString("close_price"))
               ));

            }
            return results;
         }
      }
   }

   private void setParams(PreparedStatement statement, String instrumentName, String customerLastName, String accountName) throws SQLException {
      int paramIndex = 1;
      if (instrumentName != null) {
         statement.setString(paramIndex++, instrumentName);
      }
      if (customerLastName != null) {
         statement.setString(paramIndex++, customerLastName);
      }
      if (accountName != null) {
         statement.setString(paramIndex, accountName);
      }
   }

   private String prepareQuery(String instrumentName, String customerLastName, String accountName) {
      String sql = "SELECT deals.id as deal_id, open_timestamp, close_timestamp, open_price, close_price, " +
            "accounts.id as account_id, accounts.name as account_name, customers.id as customer_id, first_name, last_name, " +
            "instruments.id as instrument_id, instruments.name as instrument_name FROM deals " +
            "INNER JOIN accounts ON deals.account_id = accounts.id " +
            "INNER JOIN customers ON accounts.customer_id = customers.id " +
            "INNER JOIN instruments ON deals.instrument_id = instruments.id ";

      List<String> whereConditions = Stream.of(
            addParamToQuery("instruments.name", instrumentName),
            addParamToQuery("last_name", customerLastName),
            addParamToQuery("accounts.name", accountName)).
            filter(Objects::nonNull).collect(Collectors.toList());

      if (!whereConditions.isEmpty()) {
         sql += "WHERE " + String.join(" AND ", whereConditions);
      }

      return sql + " ORDER BY deals.id";
   }

   private String addParamToQuery(String fieldName, String value) {
      return value != null ? fieldName + "= ?" : null;
   }

   private Connection createConnection() throws SQLException {
      return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/dealing",
            "postgres", "password1");
   }
}
