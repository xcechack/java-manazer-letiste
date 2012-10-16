/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Vasa
 */
public interface PlaneDAO {
     /**
     * Creates new plane
     * 
     * @param plane Plane we want to store.
     * @throws IllegalArgumentException when argument is not Plane class instance. 
     * @return nothing.
     */
    void create(Plane plane);
    /**
     * Returns Plane entity with given id.
     * 
     * @throws IllegalArgumentException when id is null
     * @param id Id of plane entity.
     * @return Plane with given id or null if plane doesnt exists.
     */
    Plane get(Long id);
    
    /**
     * Update existing plane
     * @param plane Plane we want to update
     * @return nothing
     * @throws IllegalArgumentException when given plane doesnt exist.
     */
    void update(Plane plane);
    
    /**
     * Delete existing plane
     * @param plane plane we want to delete
     * @return nothing
     * @throws IlleagalArgumentException when given plane doesnt exist.
     * @throws NullPointerException when given plane isn't in database.
     */
    void remove(Plane plane);
            
    /**
     * Retrieve all planes
     * @param nothing
     * @return List with all planes.
     */
    List<Plane> findAll();
    
    /**
     * Retrive all planes with given producer
     * @param producer Producer of the planes
     * @return List of planes with given producer
     */
    List<Plane> findByProducer(String producer);
    
    /**
     * Retrieve all planes with given type
     * @param type Type of the planes.
     * @return List of planes with given type.
     * @throws IlleagalArgumentException when given producer is null.
     */
    List<Plane> findByType(String type);
    
    /**
     * Retrieve all planes with given maximum number of seats
     * @param number Number of seats
     * @return List of planes with Maximum Number of Seats EQUAL to given number.
     * @throws IlleagalArgumentException when given type is null.
     */
    List<Plane> findByMaxNumberOfSeats(int number);
    
    /**
     * Retrieve all planes with maximum number of seats greater than given number
     * @param number Number of seats
     * @return List of planes with Maximum Number of Seats EQUAL OR GREATER THAN given number.
     */
    List<Plane> findPlaneWithGreaterNumberOfSeats(int number);
    
    /**
     * Sets EntityManagerFactory
     * @param EntityManagerFactory
     * @return nothing
     */
    void setEntityManagerFactory(EntityManagerFactory emf);
}
