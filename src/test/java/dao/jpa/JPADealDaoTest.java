package dao.jpa;

import dto.Customer;
import dto.Account;
import dto.Deal;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JPADealDaoTest {


   @Test
   public void get_deal() {
      JPADealDao dealDao = new JPADealDao();
      final Deal deal = dealDao.get(10001);

      assertThat(deal).isNotNull();
   }

   @Test
   public void all_deals() throws SQLException {
      JPADealDao dealDao = new JPADealDao();
      final List<Deal> deals = dealDao.find(null, null, null);

      assertThat(deals).hasSize(17);
   }

   @Test
   public void williams_deals() throws SQLException {
      JPADealDao dealDao = new JPADealDao();
      final List<Deal> deals = dealDao.find("Williams");

      assertThat(deals).hasSize(3);



      assertThat(deals.stream().
            map(Deal::getAccount).
            map(Account::getCustomer).
            map(Customer::getLastName).
            allMatch("Williams"::equalsIgnoreCase)).isTrue();
   }

   @Test
   public void complexSearch() throws SQLException {
      JPADealDao dealDao = new JPADealDao();
      final List<Deal> deals = dealDao.find("USD/EUR", "King", "CFD");

      assertThat(deals).hasSize(1);
      final Deal deal = deals.get(0);
      assertThat(deal.getAccount().getCustomer().getLastName()).isEqualTo("King");
      assertThat(deal.getAccount().getName()).isEqualTo("CFD");
      assertThat(deal.getInstrument().getName()).isEqualTo("USD/EUR");
   }


}



