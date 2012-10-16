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
                res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE FLIGHTIDENTIFIER = '"+identifier+"'",Flight.class).getResultList();
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
        if(flight!=null && flight.getId()!=null){
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Flight deletedFlight = em.find(Flight.class,flight.getId());
            if(deletedFlight == null){
                em.close();
                throw new IllegalArgumentException("Given flight instance was null.");
            }else{
                em.remove(deletedFlight);
                em.getTransaction().commit();
                em.close();
            }
        }else{
            throw new IllegalArgumentException("Given flight instance was null.");
        }
    }
    
    public void removeAll(){
       List<Flight> allFlights = this.findAll();
       for(int i = 0; i < allFlights.size(); i++){
           this.remove(allFlights.get(i));
       }
    }

    public List<Flight> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Flight> res;
        em.getTransaction().begin();
            res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight",Flight.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return res;
    }

    public List<Flight> findFlightsByDepartureDestination(Destination destination) {
         if(destination != null){
            EntityManager em = entityManagerFactory.createEntityManager();
            List<Flight> res;
            em.getTransaction().begin();
                res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE DESTINATIONSTART_ID = "+destination.getId()+"",Flight.class).getResultList();
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
                res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE DESTINATIONARRIVAL_ID = "+destination.getId()+"",Flight.class).getResultList();
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
                res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE PLANE_ID = "+plane.getId()+"",Flight.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return res;
        }else{
            throw new IllegalArgumentException("Given plane instance was null.");
        }
    }
    
}
