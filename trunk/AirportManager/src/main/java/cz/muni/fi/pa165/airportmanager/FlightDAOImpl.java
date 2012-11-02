/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marek
 */
@Repository
public class FlightDAOImpl implements FlightDAO{
   
    @PersistenceContext
    private EntityManager em;
    
    
    
    public void create(Flight flight) {
         if(flight!=null){
            em.persist(flight);
         }else{
            throw new IllegalArgumentException("Given flight was null.");
         }
    }

    public Flight get(Long id) {
        
        Flight searchedFlight;
        
        if(id!=null){
           
            searchedFlight = em.find(Flight.class,id);
           
        }else{
            throw new IllegalArgumentException("Given flight ID was null.");
        }
        
        return searchedFlight;
    }

    public List<Flight> findByIdentifier(String identifier) {
        if(identifier != null){
           
            List<Flight> res;
            
            res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE FLIGHTIDENTIFIER = '"+identifier+"'",Flight.class).getResultList();
           
            return res;
        }else{
            throw new IllegalArgumentException("Given flight identifier was null.");
        }
    }

    public void update(Flight flight) {
        if(flight!=null){
           
            em.merge(flight);
           
        }else{
            throw new IllegalArgumentException("Given flight instance was null.");
        }
    }

    public void remove(Flight flight) {
        if(flight!=null && flight.getId()!=null){
            
            Flight deletedFlight = em.find(Flight.class,flight.getId());
            
            if(deletedFlight == null){
                throw new IllegalArgumentException("Given flight instance was null.");
            }else{
                em.remove(deletedFlight);
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
        
        List<Flight> res;
       
        res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight",Flight.class).getResultList();
       
        return res;
    }

    public List<Flight> findFlightsByDepartureDestination(Destination destination) {
         if(destination != null){
            
            List<Flight> res;
            
            res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE DESTINATIONSTART_ID = "+destination.getId()+"",Flight.class).getResultList();
            
            return res;
        }else{
            throw new IllegalArgumentException("Given destination was null.");
        }
    }

    public List<Flight> findFlightsByArrivalDestination(Destination destination) {
        if(destination != null){
           
            List<Flight> res;
            
            res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE DESTINATIONARRIVAL_ID = "+destination.getId()+"",Flight.class).getResultList();
            
            return res;
        }else{
            throw new IllegalArgumentException("Given destination was null.");
        }
    }

    public List<Flight> findFlightsByPlane(Plane plane) {
        if(plane != null){
            
            List<Flight> res;
            
            res = em.createQuery("SELECT Flight FROM "+Flight.class.getName()+" Flight WHERE PLANE_ID = "+plane.getId()+"",Flight.class).getResultList();
            
            return res;
        }else{
            throw new IllegalArgumentException("Given plane instance was null.");
        }
    }
    
    /*public List<Flight> findFlightsOperatedByStewardess(Stewardess stewardess){
          
      }*/
    
}
