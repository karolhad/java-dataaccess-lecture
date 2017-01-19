package dao.jooq;

import dao.DealDao;
import dto.Deal;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class JooqDealDao implements DealDao {
   @Override
   public List<Deal> find(String instrumentName, String customerLastName, String accountName) throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   public BigDecimal findBestDeal() throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   public Result<Record> totalBalanceReport() throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   public Result<Record2<String, BigDecimal>> customerTradeBestReport() throws SQLException {
      throw new UnsupportedOperationException("Not implemented yet");
   }
}
