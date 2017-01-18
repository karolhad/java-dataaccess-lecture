package dao.jinq;

import dto.Deal;
import org.jinq.tuples.Pair;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static dao.jpa.EntityManagerProvider.getEntityManager;
import static org.assertj.core.api.Assertions.assertThat;


public class JINQDealDaoTest {

   private JINQDealDao dealDao;

   @Before
   public void setUp() {
      dealDao = new JINQDealDao(getEntityManager());
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

      assertThat(deals).isEqualTo(new BigDecimal("3.26"));
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



