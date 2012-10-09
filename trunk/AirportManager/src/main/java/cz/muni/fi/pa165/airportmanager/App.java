package cz.muni.fi.pa165.airportmanager;

import javax.persistence.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em1 = emf.createEntityManager(); //ctx1
        /*
        Person testPerson = new Person();
        testPerson.setFirstName("Jozinko");
        testPerson.setLastName("Z Bazin");
        
        em1.getTransaction().begin();
           em1.persist(testPerson);
        em1.getTransaction().commit();*/
    }
}

