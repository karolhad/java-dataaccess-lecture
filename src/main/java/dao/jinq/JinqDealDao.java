package dao.jinq;

import dao.DealDao;
import dto.Deal;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.tuples.Pair;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

class JinqDealDao implements DealDao {

   private final EntityManager em;
   private final JinqJPAStreamProvider streams;

   JinqDealDao(EntityManager entityManager) {
      this.em = entityManager;
      this.streams = new JinqJPAStreamProvider(entityManager.getMetamodel());
   }

   @Override
   public List<Deal> find(String instrumentName, String customerLastName, String accountName) {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   public BigDecimal findBestDeal() {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   public List<Pair<String, Long>> customerTradeCountReport() {
      throw new UnsupportedOperationException("Not implemented yet");
   }


   public List<Pair<String, BigDecimal>> customerTradeBestReport() {
      throw new UnsupportedOperationException("Not implemented yet");
   }
}


