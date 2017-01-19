package dto;

public class Instrument {

   private Integer id;

   private String name;

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
