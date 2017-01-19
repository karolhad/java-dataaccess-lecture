package dao.jinq;

import dto.Account;

import javax.persistence.EntityManager;
import java.util.List;

class JinqAccountDao {

   private final EntityManager em;

   JinqAccountDao(EntityManager entityManager) throws NoSuchMethodException {
      this.em = entityManager;

   }

   List<Account> findAccountsForCustomerLastName(String customerLastName) {
      throw new UnsupportedOperationException("Not implemented yet");
   }


}
