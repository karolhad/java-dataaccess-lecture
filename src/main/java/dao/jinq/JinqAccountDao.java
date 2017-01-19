package dao.jinq;

import dto.Account;
import dto.Customer;
import org.jinq.jpa.JinqJPAStreamProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

class JinqAccountDao {

   private final EntityManager em;
   private final JinqJPAStreamProvider streams;

   JinqAccountDao(EntityManager entityManager) throws NoSuchMethodException {
      this.em = entityManager;
      this.streams = new JinqJPAStreamProvider(entityManager.getMetamodel());

      streams.registerAssociationAttribute(Account.class.getMethod("getCustomer"), "customer", false);
      streams.registerAssociationAttribute(Customer.class.getMethod("getAccounts"), "accounts", true);
   }

   List<Account> findAccountsForCustomerLastName(String customerLastName) {

      return streams
            .streamAll(em, Customer.class)
            .
                  where(customer -> customer
                        .getLastName()
                        .equals(customerLastName))
            .
                  selectAllList(Customer::getAccounts)
            .collect(Collectors.toList());
   }


}
