/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.PlaneDTO;
import java.util.List;

/**
 *
 * @author Marek
 */
public interface FlightService {
     /**
     * Creates new flight
     * 
     * @param flight Flight we want to store.
     * @throws IllegalArgumentException when argument is not Flight class instance. 
     * @return nothing.
     */
    void create(FlightDTO fDto);
    /**
     * Returns Flight entity with given id.
     * 
     * @throws IllegalArgumentException when id is null
     * @param id Id of flight entity.
     * @return Flight with given id or null if flight doesnt exists.
     */
    FlightDTO get(Long id);
    
    /**
     * Returns Flights with given flight identifier.
     * 
     * @throws IllegalArgumentException when identifier is null.
     * @param identifier String identifier of flight (f.i. UAL904 or THY9MR)
     * @return Flights with given identifier. These are usually flights from/to same destination. 
     * Differences between flights in returned list should be departure/arrival date,
     * stewards on board and sometimes it is operated by different plane.
     */
    List<FlightDTO> findByIdentifier(String identifier);
    
    /**
     * Update existing flight
     * @param flight Flight we want to update
     * @return nothing
     * @throws IllegalArgumentException when given flight doesnt exist.
     */
    void update(FlightDTO flight);
    
    /**
     * Delete existing flight
     * @param destination flight we want to delete
     * @return nothing
     * @throws IlleagalArgumentException when given flight doesnt exist or is null.
     */
    void remove(FlightDTO flight);
    
    /**
     * Delete all flights from Database
     */
    void removeAll();
    /**
     * Retrieve all flights
     * @param nothing
     * @return List with all flights.
     */
    List<FlightDTO> findAll();
    
    /**
     * Retrieve all flights departing from given destination.
     * @param destination Departure destination
     * @return List of flights with given departure destination
     * @throws IllegalArgumentException if given destination is null
     */
    List<FlightDTO> findFlightsByDepartureDestination(DestinationDTO destination);
    
    /**
     * Retrieve flights arriving to given destination
     * @param destination arrival destination
     * @return List of flights with given arrival destination
     * @throws IllegalArgumentException if given destination is null
     */
    List<FlightDTO> findFlightsByArrivalDestination(DestinationDTO destination);
    
    /**
     * Retrieve flights operated by given plane
     * @param plane given plane
     * @return List of flights operated by given plane
     * @throws IllegalArgumentException if given flight
     */
    List<FlightDTO> findFlightsByPlane(PlaneDTO plane);
}
