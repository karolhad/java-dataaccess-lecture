package dto;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
   @Id
   private Integer id;

   @Column
   private String name;

   @ManyToOne
   private Customer customer;

   protected Account() {
   }

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

   @Override
   public String toString() {
      return "Account{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
   }
}
