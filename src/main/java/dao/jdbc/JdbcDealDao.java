package dao.jdbc;


import dto.Deal;

import java.sql.SQLException;
import java.util.List;

class JdbcDealDao {

   List<Deal> find(String instrumentName, String customerLastName, String accountName) throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   private String prepareQuery(String instrumentName, String customerLastName, String accountName) {
      String sql = "SELECT deals.id as deal_id, open_timestamp, close_timestamp, open_price, close_price, " +
            "accounts.id as account_id, accounts.name as account_name, customers.id as customer_id, first_name, last_name, " +
            "instruments.id as instrument_id, instruments.name as instrument_name FROM deals " +
            "INNER JOIN accounts ON deals.account_id = accounts.id " +
            "INNER JOIN customers ON accounts.customer_id = customers.id " +
            "INNER JOIN instruments ON deals.instrument_id = instruments.id ";

      return sql;
   }

}
