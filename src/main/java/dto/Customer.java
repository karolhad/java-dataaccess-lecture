package dto;

import java.util.List;

public class Customer {

   private Integer id;

   private String firstName;

   private String lastName;

   private List<Account> accounts;

   public Customer(Integer id, String firstName, String lastName) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public Customer(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public Integer getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public List<Account> getAccounts() {
      return accounts;
   }

   @Override
   public String toString() {
      return "Customer{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
   }
}
