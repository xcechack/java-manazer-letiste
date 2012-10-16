/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Marek
 */
public class FlightDAOImpl implements FlightDAO{
    
    private EntityManagerFactory entityManagerFactory;
    
    public void setEntityManager(EntityManagerFactory entityManagerF) {
        this.entityManagerFactory = entityManagerF;
    }
    
    public void create(Flight flight) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
            em.persist(flight);
        em.getTransaction().commit();
        em.close();
    }

    public Flight get(Long id) {
        
        Flight searchedFlight;
        
        if(id!=null){
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            searchedFlight = em.find(Flight.class,id);
            em.getTransaction().commit();
            em.close();
        }else{
            throw new IllegalArgumentException("Given flight ID was null.");
        }
        
        return searchedFlight;
    }

    public List<Flight> findByIdentifier(String identifier) {
        if(identifier != null){
            EntityManager em = entityManagerFactory.createEntityManager();
            List<Flight> res;
            em.getTransaction().begin();
                res = em.createQuery("SELECT * FROM flights WHERE FlightIdentifier = '"+identifier+"'",Flight.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return res;
        }else{
            throw new IllegalArgumentException("Given flight identifier was null.");
        }
    }

    public void update(Flight flight) {
        if(flight!=null){
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.merge(flight);
            em.getTransaction().commit();
            em.close();
        }else{
            throw new IllegalArgumentException("Given flight instance was null.");
        }
    }

    public void remove(Flight flight) {
        if(flight!=null){
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(flight);
            em.getTransaction().commit();
            em.close();
        }else{
            throw new IllegalArgumentException("Given flight instance was null.");
        }
    }

    public List<Flight> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Flight> res;
        em.getTransaction().begin();
            res = em.createQuery("SELECT * FROM flights",Flight.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return res;
    }

    public List<Flight> findFlightsByDepartureDestination(Destination destination) {
         if(destination != null){
            EntityManager em = entityManagerFactory.createEntityManager();
            List<Flight> res;
            em.getTransaction().begin();
                res = em.createQuery("SELECT * FROM flights WHERE DestinationStart = "+destination.getId()+"",Flight.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return res;
        }else{
            throw new IllegalArgumentException("Given destination was null.");
        }
    }

    public List<Flight> findFlightsByArrivalDestination(Destination destination) {
        if(destination != null){
            EntityManager em = entityManagerFactory.createEntityManager();
            List<Flight> res;
            em.getTransaction().begin();
                res = em.createQuery("SELECT * FROM flights WHERE DestinationArrival = "+destination.getId()+"",Flight.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return res;
        }else{
            throw new IllegalArgumentException("Given destination was null.");
        }
    }

    public List<Flight> findFlightsByPlane(Plane plane) {
        if(plane != null){
            EntityManager em = entityManagerFactory.createEntityManager();
            List<Flight> res;
            em.getTransaction().begin();
                res = em.createQuery("SELECT * FROM flights WHERE Plane = "+plane.getId()+"",Flight.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return res;
        }else{
            throw new IllegalArgumentException("Given plane instance was null.");
        }
    }
    
}
