package dao.jpa;

import dao.CustomerDao;
import dto.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

class JpaCustomerDao implements CustomerDao {

   private EntityManager entityManager;

   JpaCustomerDao(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @Override
   public List<Customer> findAll() throws SQLException {
      TypedQuery<Customer> query = entityManager.createQuery("from Customer c JOIN FETCH c.accounts", Customer.class);
      return query.getResultList();
   }

   @Override
   public Customer get(Integer id) {
      return entityManager.find(Customer.class, id);
   }

   @Override
   public List<Customer> findByFirstName(String firstName) {
      TypedQuery<Customer> query = entityManager.createQuery("from Customer where firstName=:firstName", Customer.class);
      query.setParameter("firstName", firstName);

      return query.getResultList();
   }

   @Override
   public Integer create(String firstName, String lastName) {

      final Customer entity = new Customer(firstName, lastName);

      entityManager.getTransaction().begin();

      entityManager.persist(entity);

      entityManager.getTransaction().commit();

      return entity.getId();
   }


}
