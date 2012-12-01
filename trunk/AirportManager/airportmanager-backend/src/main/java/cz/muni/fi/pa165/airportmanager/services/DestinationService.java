/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import java.util.List;

/**
 *
 * @author Marek
 */
public interface DestinationService {
    /**
     * Creates new destination
     * 
     * @param destinationDTO Destination we want to store.
     * @throws NullPointerException when given destination is null. 
     * @return nothing.
     */
    void create(DestinationDTO destinationDTO);
    /**
     * Returns Destination entity with given id.
     * 
     * @throws NullPointerException when id is null
     * @param id Id of destination entity.
     * @return Destination with given id or null if destination doesnt exists.
     */
    DestinationDTO get(Long id);
    
    /**
     * Update existing destination
     * @param destinationDTO destination we want to update
     * @return nothing
     * @throws NullPointerException when given destination is null.
     */
    void update(DestinationDTO destinationDTO);
    
    /**
     * Delete existing destination
     * @param destinationDTO destination we want to delete
     * @return nothing
     * @throws NullPointerException when given destination is null.
     */
    void remove(DestinationDTO destinationDTO);
    
    /**
     * Retrieve all destinations
     * @param nothing
     * @return List with all destinations.
     */
    List<DestinationDTO> findAll();
    
    /**
     * Retrieves all destinations with given country name. 
     * @param country Destination's country parameter.
     * @return List of destinations with given country.
     * @throws NullPointerException when given country is null.
     */
    List<DestinationDTO> findByCountry(String country);
    
    /**
     * Retrievec all destinations with given city name.
     * @param city Destination's city name.
     * @return List of destinations with given city.
     * @throws NullPointerException when given city is null.
     */
    List<DestinationDTO> findByCity(String city);
}
