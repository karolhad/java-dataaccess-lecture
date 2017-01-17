package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instruments")
public class Instrument {

   @Id
   private Integer id;

   @Column
   private String name;

   protected Instrument() {
   }

   public Instrument(Integer id, String name) {
      this.id = id;
      this.name = name;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

}
