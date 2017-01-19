package dao.jdbc;

import dto.Account;
import dto.Customer;
import dto.Deal;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JdbcDealDaoTest {

   @Test
   public void all_deals() throws SQLException {
      JdbcDealDao dealDao = new JdbcDealDao();
      final List<Deal> deals = dealDao.find(null, null, null);

      assertThat(deals).hasSize(17);
   }

   @Test
   public void williams_deals() throws SQLException {
      JdbcDealDao dealDao = new JdbcDealDao();
      final List<Deal> deals = dealDao.find(null, "Williams", null);

      assertThat(deals).hasSize(3);

      assertThat(deals.stream().
            map(Deal::getAccount).
            map(Account::getCustomer).
            map(Customer::getLastName).
            allMatch("Williams"::equalsIgnoreCase)).isTrue();
   }

   @Test
   public void complexSearch() throws SQLException {
      JdbcDealDao dealDao = new JdbcDealDao();
      final List<Deal> deals = dealDao.find("USD/EUR", "King", "CFD");

      assertThat(deals).hasSize(1);
      final Deal deal = deals.get(0);
      assertThat(deal.getAccount().getCustomer().getLastName()).isEqualTo("King");
      assertThat(deal.getAccount().getName()).isEqualTo("CFD");
      assertThat(deal.getInstrument().getName()).isEqualTo("USD/EUR");
   }


}



