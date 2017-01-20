package dao.jpa;

import dao.DealDao;
import dto.Deal;

import javax.persistence.EntityManager;
import java.util.List;

class JpaDealDao implements DealDao {

   private EntityManager entityManager;

   JpaDealDao(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @Override
   public List<Deal> find(String instrumentName, String customerLastName, String accountName) {
      throw new UnsupportedOperationException("Not implemented yet");

   }

   public Deal get(int id) {
      throw new UnsupportedOperationException("Not implemented yet");
   }
}
