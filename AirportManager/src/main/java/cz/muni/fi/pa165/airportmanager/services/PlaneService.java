/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.PlaneDTO;
import java.util.List;

/**
 *
 * @author Marek
 */
public interface PlaneService {
    /**
     * Creates new plane
     * 
     * @param plane Plane we want to store.
     * @throws IllegalArgumentException when argument is not Plane class instance. 
     * @return nothing.
     */
    void create(PlaneDTO plane);
    /**
     * Returns Plane entity with given id.
     * 
     * @throws IllegalArgumentException when id is null
     * @param id Id of plane entity.
     * @return Plane with given id or null if plane doesnt exists.
     */
    PlaneDTO get(Long id);
    
    /**
     * Update existing plane
     * @param plane Plane we want to update
     * @return nothing
     * @throws IllegalArgumentException when given plane doesnt exist.
     */
    void update(PlaneDTO plane);
    
    /**
     * Delete existing plane
     * @param plane plane we want to delete
     * @return nothing
     * @throws IlleagalArgumentException when given plane doesnt exist.
     * @throws NullPointerException when given plane isn't in database.
     */
    void remove(PlaneDTO plane);
            
    /**
     * Retrieve all planes
     * @param nothing
     * @return List with all planes.
     */
    List<PlaneDTO> findAll();
    
    /**
     * Retrive all planes with given producer
     * @param producer Producer of the planes
     * @return List of planes with given producer
     */
    List<PlaneDTO> findByProducer(String producer);
    
    /**
     * Retrieve all planes with given type
     * @param type Type of the planes.
     * @return List of planes with given type.
     * @throws IlleagalArgumentException when given producer is null.
     */
    List<PlaneDTO> findByType(String type);
    
    /**
     * Retrieve all planes with given maximum number of seats
     * @param number Number of seats
     * @return List of planes with Maximum Number of Seats EQUAL to given number.
     * @throws IlleagalArgumentException when given type is null.
     */
    List<PlaneDTO> findByMaxNumberOfSeats(int number);
    
    /**
     * Retrieve all planes with maximum number of seats greater than given number
     * @param number Number of seats
     * @return List of planes with Maximum Number of Seats EQUAL OR GREATER THAN given number.
     */
    List<PlaneDTO> findPlaneWithGreaterNumberOfSeats(int number);
    
}
