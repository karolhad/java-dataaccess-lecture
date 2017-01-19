package dao.jinq;

import dto.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JinqAccountDaoTest {

   private JinqAccountDao accountDao;
   private EntityManagerFactory entityManagerFactory;
   private EntityManager entityManager;

   @Before
   public void setUp() throws NoSuchMethodException {
      entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
      entityManager = entityManagerFactory.createEntityManager();

      accountDao = new JinqAccountDao(entityManager);
   }

   @After
   public void tearDown() {
      entityManager.close();
      entityManagerFactory.close();
   }

   @Test
   public void getAccountsForCustomerWithLastName() {
      final List<Account> accounts = accountDao.findAccountsForCustomerLastName("King");

      assertThat(accounts).isNotNull();
   }
}
