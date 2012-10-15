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
        Destination managedDest = em.find(Destination.class, destination.getId());
        em.remove(managedDest);
        em.getTransaction().commit();
        em.close();
    }

    public List<Destination> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Destination> dest;
        dest = em.createQuery("SELECT d FROM Destination d").getResultList();
        em.getTransaction().commit();
        em.close();
        return Collections.unmodifiableList(dest);
    }

    public List<Destination> findByCountry(String country) {
        
        if(country== null){
            throw new NullPointerException("Country is null ");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Destination> dest;        
        dest = em.createQuery("SELECT d FROM Destination d WHERE d.country = :country")
                .setParameter("country", country).getResultList();
        
        em.getTransaction().commit();
        em.close();
        return Collections.unmodifiableList(dest);
        
        
    }

    public List<Destination> findByCity(String city) {
        if(city== null){
            throw new NullPointerException("Sity is null ");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Destination> dest;        
        dest = em.createQuery("SELECT d FROM Destination d WHERE d.city = :city").setParameter("city", city).getResultList();
        em.getTransaction().commit();
        em.close();
        return Collections.unmodifiableList(dest);
    }

    
    
}
