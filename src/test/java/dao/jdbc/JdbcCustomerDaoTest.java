package dao.jdbc;

import dto.Customer;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbcCustomerDaoTest {

   private JdbcCustomerDao customerDao;

   @Before
   public void setUp() {
      customerDao = new JdbcCustomerDao();
   }

   @Test
   public void getCustomerById() throws SQLException {
      Customer customer = customerDao.get(1001);

      assertThat(customer).isNotNull();
   }

   @Test
   public void returnAllCustomers() throws SQLException {
      List<Customer> customers = customerDao.findAll();

      print(customers);

      assertThat(customers.size()).isGreaterThan(3);
   }

   @Test
   public void customersWithFirstNameJames() throws SQLException {
      List<Customer> customers = customerDao.findByFirstName("James");

      print(customers);

      assertThat(customers).hasSize(1);
   }

   @Test
   public void customersWithFirstSqlInjection() throws SQLException {
      List<Customer> customers = customerDao.findByFirstName("James'; DELETE FROM customers; --'");

      print(customers);

      assertThat(customers).hasSize(0);
   }

   @Test
   public void returnAllCustomersManyTimes() throws SQLException {
      for (int i = 0; i < 100; i++) {
         returnAllCustomers();
      }
   }


   @Test
   public void addCustomer() throws SQLException {
      Integer id = customerDao.create("Jan", "Kowalski");

      assertThat(id).isNotNull();
   }

   private void print(List<Customer> allCustomers) {
      allCustomers.forEach(System.out::println);
   }

}
