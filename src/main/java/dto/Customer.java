package dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "customers")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "first_name")
   private String firstName;
   @Column(name = "last_name")
   private String lastName;

   @OneToMany()
   @JoinColumn(name = "customer_id")
   private List<Account> accounts;

   protected Customer() {
   }

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
            ", accounts=" + accounts +
            '}';
   }
}
