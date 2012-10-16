/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public interface DestinationDAO {
    /**
     * Set entity manager factory for destination class
     * 
     * @param entityManagerFactory
     * @return nothing.
     */
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);

    /**
     * Creates new destination
     * 
     * @param destination Destination we want to store.
     * @throws NullPointerException when given destination is null. 
     * @return nothing.
     */
    void create(Destination destination);
    /**
     * Returns Destination entity with given id.
     * 
     * @throws NullPointerException when id is null
     * @param id Id of destination entity.
     * @return Destination with given id or null if destination doesnt exists.
     */
    Destination get(Long id);
    
    /**
     * Update existing destination
     * @param destination destination we want to update
     * @return nothing
     * @throws NullPointerException when given destination is null.
     */
    void update(Destination destination);
    
    /**
     * Delete existing destination
     * @param destination destination we want to delete
     * @return nothing
     * @throws NullPointerException when given destination is null.
     */
    void remove(Destination destination);
    
    /**
     * Retrieve all destinations
     * @param nothing
     * @return List with all destinations.
     */
    List<Destination> findAll();
    
    /**
     * Retrieves all destinations with given country name. 
     * @param country Destination's country parameter.
     * @return List of destinations with given country.
     * @throws NullPointerException when given country is null.
     */
    List<Destination> findByCountry(String country);
    
    /**
     * Retrievec all destinations with given city name.
     * @param city Destination's city name.
     * @return List of destinations with given city.
     * @throws NullPointerException when given city is null.
     */
    List<Destination> findByCity(String city);
    
    
}