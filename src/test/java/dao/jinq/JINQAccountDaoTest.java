package dao.jinq;

import dto.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static dao.jpa.EntityManagerProvider.getEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

public class JINQAccountDaoTest {

   private JINQAccountDao accountDao;

   @Before
   public void setUp() throws NoSuchMethodException {
      accountDao = new JINQAccountDao(getEntityManager());
   }

   @Test
   public void getAccountsForCustomerWithLastName() {
      final List<Account> accounts = accountDao.findAccountsForCustomerLastName("King");

      assertThat(accounts).isNotNull();
   }
}
