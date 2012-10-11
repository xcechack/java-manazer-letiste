/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;

/**
 *
 * @author Marek
 */
public interface FlightDAO {
    /**
     * Creates new flight
     * 
     * @param flight Flight we want to store.
     * @throws IllegalArgumentException when argument is not Flight class instance. 
     * @return nothing.
     */
    void create(Flight flight);
    /**
     * Returns Flight entity with given id.
     * 
     * @throws IllegalArgumentException when id is null
     * @param id Id of flight entity.
     * @return Flight with given id or null if flight doesnt exists.
     */
    Flight get(Long id);
    
    /**
     * Returns Flights with given flight identifier.
     * 
     * @throws IllegalArgumentException when identifier is null.
     * @param identifier String identifier of flight (f.i. UAL904 or THY9MR)
     * @return Flights with given identifier. These are usually operated from/to same destination. 
     * Differences between flights in returned list should be departure/arrival date,
     * stewards on board and sometimes it is operated by different plane.
     */
    List<Flight> findByIdentifier(String identifier);
    
    /**
     * Update existing flight
     * @param flight Flight we want to update
     * @return nothing
     * @throws IllegalArgumentException when given flight doesnt exist.
     */
    void update(Flight flight);
    
    /**
     * Delete existing flight
     * @param destination flight we want to delete
     * @return nothing
     * @throws IlleagalArgumentException when given flight doesnt exist.
     */
    void remove(Flight flight);
    
    /**
     * Retrieve all flights
     * @param nothing
     * @return List with all flights.
     */
    List<Flight> findAll();
    
}
