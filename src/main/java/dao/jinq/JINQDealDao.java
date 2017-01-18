package dao.jinq;

import dao.DealDao;
import dto.Deal;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.tuples.Pair;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JINQDealDao implements DealDao {

   private final EntityManager em;
   private final JinqJPAStreamProvider streams;

   public JINQDealDao(EntityManager entityManager) {
      this.em = entityManager;
      this.streams = new JinqJPAStreamProvider(entityManager.getMetamodel());
   }

   @Override
   public List<Deal> find(String instrumentName, String customerLastName, String accountName) {

      String instrumentNameLowerCase = instrumentName != null ? instrumentName.toLowerCase() : instrumentName;
//      return deals().where(deal ->
//            (instrumentNameLowerCase == null || deal.getInstrument().getName().toLowerCase().equals(instrumentNameLowerCase))
//                  && (customerLastName == null || customerLastName.equals(deal.getAccount().getCustomer().getLastName()))
//                  && (accountName == null || deal.getAccount().getName().equals(accountName))
//      ).collect(Collectors.toList());


      JPAJinqStream<Deal> deals = deals();

      if (instrumentName != null) {
         deals = deals.where(deal -> deal.getInstrument().getName().equals(instrumentName));
      }

      if (customerLastName != null) {
         deals = deals.where(deal -> deal.getAccount().getCustomer().getLastName().equals(customerLastName));
      }

      if (accountName != null) {
         deals = deals.where(deal -> deal.getAccount().getName().equals(accountName));
      }

      return deals.collect(toList());
   }

   public BigDecimal findBestDeal() {
      return deals().max(deal -> deal.getClosePrice().subtract(deal.getOpenPrice()));
   }

   public List<Pair<String, Long>> customerTradeCountReport() {
      return deals().
            group(deal -> deal.getAccount().getCustomer().getLastName(),
                  (name, deal) -> deal.count()
            ).collect(toList());
   }


   public List<Pair<String, BigDecimal>> customerTradeBestReport() {
      return deals().
            group(deal -> deal.getAccount().getCustomer().getLastName(),
                  (name, deal) -> deal.max( d -> d.getClosePrice().subtract(d.getOpenPrice()))
            ).collect(toList());
   }

   private JPAJinqStream<Deal> deals() {
      return streams.streamAll(em, Deal.class);
   }
}


