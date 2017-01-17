package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class EntityManagerProvider {

   private static EntityManager entityManager;

   static {
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
      entityManager = entityManagerFactory.createEntityManager();
   }

   static EntityManager getEntityManager() {
      return entityManager;
   }
}
