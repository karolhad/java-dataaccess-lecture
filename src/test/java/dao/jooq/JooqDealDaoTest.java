package dao.jooq;

import dto.Deal;
import org.jooq.Record2;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JooqDealDaoTest {

   private JooqDealDao dealDao;

   @Before
   public void setUp() {
      dealDao = new JooqDealDao();
   }


   @Test
   public void allDeals() throws SQLException {
      final List<Deal> deals = dealDao.find(null, null, null);

      assertThat(deals).hasSize(17);
   }


   @Test
   public void complexSearch() throws SQLException {
      final List<Deal> deals = dealDao.find("USD/EUR", "King", "CFD");

      assertThat(deals).hasSize(1);
      final Deal deal = deals.get(0);
      assertThat(deal
            .getAccount()
            .getCustomer()
            .getLastName()).isEqualTo("King");
      assertThat(deal
            .getAccount()
            .getName()).isEqualTo("CFD");
      assertThat(deal
            .getInstrument()
            .getName()).isEqualTo("USD/EUR");
   }

   @Test
   public void findBestDeal() throws SQLException {
      final BigDecimal deals = dealDao.findBestDeal();

      assertThat(deals).isEqualTo(new BigDecimal("9.19"));
   }

   @Test
   public void totalBalanceReport() throws SQLException {
      dealDao.totalBalanceReport().forEach(System.out::println);
   }


   @Test
   public void bestTradeReport() throws SQLException {
      final Result<Record2<String, BigDecimal>> pairs = dealDao.customerTradeBestReport();

      pairs.forEach(pair -> System.out.println(pair.value1() + " - " + pair.value2()));
   }


}



