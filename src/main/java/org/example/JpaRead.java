package org.example;

import org.example.model.Capital;
import org.example.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaRead {

    public static void readTables(EntityManager entityManager) {
        // get country data using primary key id=1  (France)
        Country country1 = entityManager.find(Country.class, 1);
        System.out.println(country1);
        // get the country's capital
        Capital capital1 = country1.getCapital();
        System.out.println(capital1);

        // get the capital using primary key id=4 (Mexico City)
        Capital capital2 = entityManager.find(Capital.class, 4);
        System.out.println(capital2);
        // get the capital's country
        Country country2 = capital2.getCountry();
        System.out.println(country2);
    }

    public static void main(String[] args) {
        // create EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        readTables(entityManager);

        // close entity manager and factory
        entityManager.close();
        entityManagerFactory.close();
    }
}