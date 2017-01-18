package dao.jinq;

import dao.CustomerDao;
import dao.jpa.EntityManagerProvider;
import dto.Customer;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JINQCustomerDao implements CustomerDao {

   private final JinqJPAStreamProvider streams;
   private final EntityManager em;
   private String sth;

   public JINQCustomerDao() {
      em = EntityManagerProvider.getEntityManager();
      streams = new JinqJPAStreamProvider(em.getMetamodel());
   }

   @Override
   public List<Customer> findAll() throws SQLException {
      return streamCustomer().collect(toList());
   }

   private JPAJinqStream<Customer> streamCustomer() {
      return streams.streamAll(em, Customer.class);
   }

   @Override
   public Customer get(Integer id) {
      return streamCustomer().where(c -> c.getId().equals(id)).findOne().orElseGet(null);
   }

   @Override
   public List<Customer> findByFirstName(String firstName) {


      return streamCustomer().where(c -> {
//         for (int i=0;i<10;i++) {
//            System.out.println("Test");
//         }
//         sth = "";
         return firstName.equals(c.getFirstName());}).joinFetchList(Customer::getAccounts).collect(toList());
   }

   @Override
   public Integer create(String firstName, String lastName) {
      return null;
   }
}
