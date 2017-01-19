package dao.jooq;

import dao.CustomerDao;
import dto.Customer;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static dao.jdbc.ConnectionProvider.createConnection;
import static org.jooq.model.Tables.CUSTOMERS;

public class JooqCustomerDao implements CustomerDao {

   @Override
   public List<Customer> findAll() throws SQLException {
      try (Connection connection = createConnection()) {

         return dsl(connection)
               .
                     select()
               .
                     from(CUSTOMERS)
               .
                     fetch()
               .
                     stream()
               .map(this::map)
               .
                     collect(Collectors.toList());
      }
   }

   @Override
   public Customer get(Integer id) throws SQLException {
      try (Connection connection = createConnection()) {
         return map(dsl(connection)
               .select()
               .from(CUSTOMERS)
               .where(CUSTOMERS.ID.eq(id))
               .fetchOne());
      }
   }

   @Override
   public List<Customer> findByFirstName(String firstName) throws SQLException {
      try (Connection connection = createConnection()) {
         return dsl(connection)
               .select()
               .from(CUSTOMERS)
               .where(CUSTOMERS.FIRST_NAME.eq(firstName))
               .fetch()
               .stream()
               .map(this::map)
               .collect(Collectors.toList());
      }
   }

   @Override
   public Integer create(String firstName, String lastName) throws SQLException {

      try (Connection connection = createConnection()) {
         return dsl(connection)
               .insertInto(CUSTOMERS)
               .columns(CUSTOMERS.FIRST_NAME, CUSTOMERS.LAST_NAME)
               .values(firstName, lastName)
               .returning(CUSTOMERS.ID)
               .fetchOne()
               .get(CUSTOMERS.ID);
      }
   }

   private DSLContext dsl(Connection connection) {
      return DSL.using(connection, SQLDialect.POSTGRES);
   }

   private Customer map(Record row) {
      return new Customer(row.get(CUSTOMERS.FIRST_NAME), row.get(CUSTOMERS.LAST_NAME));
   }

}
