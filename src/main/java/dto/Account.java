package dto;

public class Account {

   private Integer id;

   private String name;

   private Customer customer;

   public Account(Integer id, String name, Customer customer) {
      this.id = id;
      this.name = name;
      this.customer = customer;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public Customer getCustomer() {
      return customer;
   }

}
