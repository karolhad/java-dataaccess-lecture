package jdbc;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Deal {
   private Integer id;

   private Account account;

   private Instrument instrument;

   private Timestamp openTimestamp;

   private Timestamp closeTimestamp;

   private BigDecimal openPrice;

   private BigDecimal closePrice;

   public Deal(Integer id, Account account, Instrument instrument, Timestamp openTimestamp, Timestamp closeTimestamp, BigDecimal openPrice, BigDecimal closePrice) {
      this.id = id;
      this.account = account;
      this.instrument = instrument;
      this.openTimestamp = openTimestamp;
      this.closeTimestamp = closeTimestamp;
      this.openPrice = openPrice;
      this.closePrice = closePrice;
   }

   public Integer getId() {
      return id;
   }

   public Account getAccount() {
      return account;
   }

   public Instrument getInstrument() {
      return instrument;
   }

   public Timestamp getOpenTimestamp() {
      return openTimestamp;
   }

   public Timestamp getCloseTimestamp() {
      return closeTimestamp;
   }

   public BigDecimal getOpenPrice() {
      return openPrice;
   }

   public BigDecimal getClosePrice() {
      return closePrice;
   }

   @Override
   public String toString() {
      return "Deal{" +
            "id=" + id +
            ", account=" + account +
            ", instrument=" + instrument +
            ", openTimestamp=" + openTimestamp +
            ", closeTimestamp=" + closeTimestamp +
            ", openPrice=" + openPrice +
            ", closePrice=" + closePrice +
            '}';
   }
}
