package dao;

import dto.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

   List<Customer> findAll() throws SQLException;

   Customer get(Integer id) throws SQLException;

   List<Customer> findByFirstName(String firstName) throws SQLException;

   Integer create(String firstName, String lastName) throws SQLException;
}
