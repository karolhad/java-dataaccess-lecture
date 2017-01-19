package dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "deals")

public class Deal {
   @Id
   private Integer id;

   @ManyToOne()
   private Account account;

   @ManyToOne()
   private Instrument instrument;

   @Column(name = "open_timestamp")
   private Timestamp openTimestamp;

   @Column(name = "close_timestamp")
   private Timestamp closeTimestamp;

   @Column(name = "open_price")
   private BigDecimal openPrice;

   @Column(name = "close_price")
   private BigDecimal closePrice;

   private Deal() {
   }


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

}
