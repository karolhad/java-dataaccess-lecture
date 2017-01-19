package dao.jooq;

import dao.DealDao;
import dto.Account;
import dto.Customer;
import dto.Deal;
import dto.Instrument;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static dao.jdbc.ConnectionProvider.createConnection;
import static org.jooq.impl.DSL.*;
import static org.jooq.model.Tables.*;

public class JooqDealDao implements DealDao {
   @Override
   public List<Deal> find(String instrumentName, String customerLastName, String accountName) throws SQLException {

      try (Connection connection = createConnection()) {
         SelectConditionStep<Record> query = dsl(connection)
               .select()
               .from(DEALS)
               .innerJoin(INSTRUMENTS)
               .on(DEALS.INSTRUMENT_ID.eq(INSTRUMENTS.ID))
               .innerJoin(ACCOUNTS)
               .on(DEALS.ACCOUNT_ID.eq(ACCOUNTS.ID))
               .innerJoin(CUSTOMERS)
               .on(ACCOUNTS.CUSTOMER_ID.eq(CUSTOMERS.ID))
               .where();


         if (instrumentName != null) {
            query = query.and(INSTRUMENTS.NAME.eq(instrumentName));
         }
         if (customerLastName != null) {
            query = query.and(CUSTOMERS.LAST_NAME.eq(customerLastName));
         }
         if (accountName != null) {
            query = query.and(ACCOUNTS.NAME.eq(accountName));
         }

         return query
               .fetch()
               .map(this::map);
      }
   }

   public BigDecimal findBestDeal() throws SQLException {
      try (Connection connection = createConnection()) {
         return dsl(connection)
               .select(max(DEALS.CLOSE_PRICE.sub(DEALS.OPEN_PRICE)))
               .from(DEALS)
               .fetchOne()
               .value1();
      }
   }

   public Result<Record> totalBalanceReport() throws SQLException {
      try (Connection connection = createConnection()) {
         return dsl(connection)
               .select(DEALS.fields())
               .select(
                     DEALS.CLOSE_PRICE
                           .sub(DEALS.OPEN_PRICE)
                           .add(nvl(
                                 sum(DEALS.CLOSE_PRICE.sub(DEALS.OPEN_PRICE)).over(
                                       partitionBy(DEALS.ACCOUNT_ID)
                                             .orderBy(DEALS.OPEN_TIMESTAMP, DEALS.ID)
                                             .rowsBetweenUnboundedPreceding()
                                             .andPreceding(1)), 0
                                 )
                           )
                           .as("Total balance")


               )
               .from(DEALS)
               .where(DEALS.ACCOUNT_ID.eq(113))
               .orderBy(DEALS.OPEN_TIMESTAMP, DEALS.ID)
               .fetch();
      }
   }

   public Result<Record2<String, BigDecimal>> customerTradeBestReport() throws SQLException {

      try (Connection connection = createConnection()) {
         return dsl(connection)
               .select(CUSTOMERS.LAST_NAME, max(DEALS.CLOSE_PRICE
                     .sub(DEALS.OPEN_PRICE)
                     ).as("Best trade"))
               .from(DEALS, ACCOUNTS, CUSTOMERS)
               .where(DEALS.ACCOUNT_ID.eq(ACCOUNTS.ID))
               .and(ACCOUNTS.CUSTOMER_ID.eq(CUSTOMERS.ID))
               .groupBy(CUSTOMERS.LAST_NAME)
               .orderBy(CUSTOMERS.LAST_NAME)
               .fetch();
      }
   }

   private DSLContext dsl(Connection connection) {
      return DSL
            .using(connection);
   }

   private Deal map(Record row) {
      return new Deal(row.get(DEALS.ID),
            new Account(
                  row.get(ACCOUNTS.ID),
                  row.get(ACCOUNTS.NAME),
                  new Customer(
                        row.get(CUSTOMERS.ID),
                        row.get(CUSTOMERS.FIRST_NAME),
                        row.get(CUSTOMERS.LAST_NAME)
                  )
            ),
            new Instrument(
                  row.get(INSTRUMENTS.ID),
                  row.get(INSTRUMENTS.NAME)
            ),
            row.get(DEALS.OPEN_TIMESTAMP),
            row.get(DEALS.CLOSE_TIMESTAMP),
            row.get(DEALS.OPEN_PRICE),
            row.get(DEALS.CLOSE_PRICE));
   }
}
