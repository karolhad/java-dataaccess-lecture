package dao.jinq;

import dto.Deal;
import org.jinq.tuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JinqDealDaoTest {

   private JinqDealDao dealDao;
   private EntityManagerFactory entityManagerFactory;
   private EntityManager entityManager;

   @Before
   public void setUp() throws NoSuchMethodException {
      entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
      entityManager = entityManagerFactory.createEntityManager();

      dealDao = new JinqDealDao(entityManager);
   }

   @After
   public void tearDown() {
      entityManager.close();
      entityManagerFactory.close();
   }

   @Test
   public void all_deals() throws SQLException {
      final List<Deal> deals = dealDao.find(null, null, null);

      assertThat(deals).hasSize(17);
   }

   @Test
   public void complexSearch() throws SQLException {
      final List<Deal> deals = dealDao.find("USD/EUR", "King", "CFD");

      assertThat(deals).hasSize(1);
      final Deal deal = deals.get(0);
      assertThat(deal.getAccount().getCustomer().getLastName()).isEqualTo("King");
      assertThat(deal.getAccount().getName()).isEqualTo("CFD");
      assertThat(deal.getInstrument().getName()).isEqualTo("USD/EUR");
   }

   @Test
   public void findBestDeal() throws SQLException {
      final BigDecimal deals = dealDao.findBestDeal();

      assertThat(deals).isEqualTo(new BigDecimal("9.19"));
   }

   @Test
   public void tradeCountReport() {
      final List<Pair<String, Long>> pairs = dealDao.customerTradeCountReport();

      pairs.forEach(pair -> System.out.println(pair.getOne() + " - " + pair.getTwo()));
   }

   @Test
   public void bestTradeReport() {
      final List<Pair<String, BigDecimal>> pairs = dealDao.customerTradeBestReport();

      pairs.forEach(pair -> System.out.println(pair.getOne() + " - " + pair.getTwo()));
   }



}



