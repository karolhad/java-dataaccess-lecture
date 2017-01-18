package dao;

import dto.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

   List<Customer> findAll() throws SQLException;

   Customer get(Integer id);

   List<Customer> findByFirstName(String firstName);

   Integer create(String firstName, String lastName);
}
