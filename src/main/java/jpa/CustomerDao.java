package jpa;

import dto.Book;
import dto.Customer;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class CustomerDao {

   List<Customer> findAll() throws SQLException {
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
      final EntityManager entityManager = entityManagerFactory.createEntityManager();
      TypedQuery<Customer> query = entityManager.createQuery("from Customer", Customer.class);
      return query.getResultList();
   }
}
