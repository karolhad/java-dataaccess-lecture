package dao.jinq;

import dto.Account;
import dto.Customer;
import org.jinq.jpa.JinqJPAStreamProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class JINQAccountDao {

   private final EntityManager em;
   private final JinqJPAStreamProvider streams;

   public JINQAccountDao(EntityManager entityManager) throws NoSuchMethodException {
      this.em = entityManager;
      this.streams = new JinqJPAStreamProvider(entityManager.getMetamodel());


      streams.registerAssociationAttribute(Account.class.getMethod("getCustomer"), "customer", false);
      streams.registerAssociationAttribute(Customer.class.getMethod("getAccounts"), "accounts", true);
   }

   public List<Account> findAccountsForCustomerLastName(String customerLastName) {


//      return streams.streamAll(em, Account.class).where(a -> a.getCustomer().getLastName().equals(customerLastName)).collect(Collectors.toList());

      return streams.streamAll(em, Customer.class).
            where(customer -> customer.getLastName().equals(customerLastName)).
            selectAllList(Customer::getAccounts).collect(Collectors.toList());
   }



}
