package org.example;

import org.example.model.Capital;
import org.example.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaCreate {
    public static void createTables(EntityManager entityManager) {
        // create new country instances
        Country country1 = new Country();
        country1.setName("France");
        Country country2 = new Country();
        country2.setName("Mexico");

        // create new capital instances
        Capital capital1 = new Capital();
        capital1.setName("Paris");
        Capital capital2 = new Capital();
        capital2.setName("Mexico City");

        // create country-capital associations
        country1.setCapital(capital1);
        country2.setCapital(capital2);

        // access transaction object
        EntityTransaction transaction = entityManager.getTransaction();

        // create and use transactions
        transaction.begin();
        //persist the countries
        entityManager.persist(country1);
        entityManager.persist(country2);
        //persist the capitals
        entityManager.persist(capital1);
        entityManager.persist(capital2);
        transaction.commit();
    }

    public static void main(String[] args) {
        // create EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        createTables(entityManager);

        //close entity manager and factory
        entityManager.close();
        entityManagerFactory.close();
    }
}
