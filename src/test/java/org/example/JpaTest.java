package org.example;

import org.example.model.Capital;
import org.example.model.Country;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* Note:  persistence.xml assumes PostgreSQL database jpalab_db */

class JpaTest {

    @org.junit.jupiter.api.Test
    @Order(1)
    void createTables() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("create");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        JpaCreate.createTables(entityManager);
        Query capitalQuery = entityManager.createQuery("SELECT c FROM Capital c");
        List<Capital> capitalList = capitalQuery.getResultList();
        assertEquals(2, capitalList.size());
        assertEquals("Paris", capitalList.get(0).getName());
        assertEquals("Mexico City", capitalList.get(1).getName());

        Query countryQuery = entityManager.createQuery("SELECT c FROM Country c");
        List<Country> countryList = countryQuery.getResultList();
        assertEquals(2, countryList.size());
        assertEquals("France", countryList.get(0).getName());
        assertEquals(3, countryList.get(0).getCapital().getId());
        assertEquals("Mexico", countryList.get(1).getName());
        assertEquals(4, countryList.get(1).getCapital().getId());

        entityManager.close();

    }

    @Test
    @Order(2)
    void readTest() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("update");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String expectedOutput = "Country{id=1, name='France'}\n" +
                "Capital{id=3, name='Paris'}\n" +
                "Capital{id=4, name='Mexico City'}\n" +
                "Country{id=2, name='Mexico'}\n";
        JpaRead.readTables(entityManager);

        //compare expected output with actual output
        assertEquals(expectedOutput, outputStreamCaptor.toString());

        entityManager.close();

        System.setOut(standardOut);
    }
}