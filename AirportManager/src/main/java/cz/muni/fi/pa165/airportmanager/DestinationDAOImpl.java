/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;


import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public class DestinationDAOImpl implements DestinationDAO {

    private EntityManagerFactory entityManagerFactory;
    
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }
    
    public void create(Destination destination) {
        if(destination== null){
            throw new NullPointerException("Destination is null when it is created");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    public Destination get(Long id) {
        if(id== null){
            throw new NullPointerException("Id destination is null ");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Destination dest = em.find(Destination.class, id);
        em.getTransaction().commit();
        em.close();
        return dest;
    }

    public void update(Destination destination) {
        if(destination== null){
            throw new NullPointerException("Destination is null when it is updated");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(destination);
        em.getTransaction().commit();
        em.close();
    }

    public void remove(Destination destination) {
        if(destination== null){
            throw new NullPointerException("Destination is null when it is removed");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(destination);
        em.getTransaction().commit();
        em.close();
    }

    public List<Destination> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Destination> dest;
        dest = em.createQuery("SELECT * FROM Destination").getResultList();
        em.getTransaction().commit();
        em.close();
        return Collections.unmodifiableList(dest);
    }

    
    
}
