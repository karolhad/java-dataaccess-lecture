package jpa;

import dto.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

import static jpa.EntityManagerProvider.getEntityManager;

class CustomerDao {

   List<Customer> findAll() throws SQLException {
      final EntityManager entityManager = getEntityManager();

      TypedQuery<Customer> query = entityManager.createQuery("from Customer c JOIN FETCH c.accounts", Customer.class);
      return query.getResultList();
   }

   Customer get(Integer id) {
      final EntityManager entityManager = getEntityManager();

      return entityManager.find(Customer.class, id);
   }

   List<Customer> findByFirstName(String firstName) {
      final EntityManager entityManager = getEntityManager();

      TypedQuery<Customer> query = entityManager.createQuery("from Customer where firstName=:firstName", Customer.class);
      query.setParameter("firstName", firstName);

      return query.getResultList();
   }

   Integer create(String firstName, String lastName) {
      final EntityManager entityManager = getEntityManager();
      final Customer entity = new Customer(firstName, lastName);

      entityManager.getTransaction().begin();

      entityManager.persist(entity);

      entityManager.getTransaction().commit();
      return entity.getId();
   }


}
