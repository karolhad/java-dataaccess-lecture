package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

   private static EntityManager entityManager;

   static {
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
      entityManager = entityManagerFactory.createEntityManager();
   }

   public static EntityManager getEntityManager() {
      return entityManager;
   }
}
