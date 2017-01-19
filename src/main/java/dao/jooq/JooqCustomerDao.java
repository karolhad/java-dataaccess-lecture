package dao.jooq;

import dao.CustomerDao;
import dto.Customer;

import java.sql.SQLException;
import java.util.List;

public class JooqCustomerDao implements CustomerDao {

   @Override
   public List<Customer> findAll() throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public Customer get(Integer id) throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public List<Customer> findByFirstName(String firstName) throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public Integer create(String firstName, String lastName) throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

}
