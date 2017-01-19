package dao.jinq;

import dao.CustomerDao;
import dto.Customer;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class JinqCustomerDao implements CustomerDao {

   private final EntityManager em;

   JinqCustomerDao(EntityManager em) {
      this.em = em;

   }

   @Override
   public List<Customer> findAll() throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public Customer get(Integer id) {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public List<Customer> findByFirstName(String firstName) {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public Integer create(String firstName, String lastName) {
      throw new UnsupportedOperationException("Not implemented yet");
   }

}
