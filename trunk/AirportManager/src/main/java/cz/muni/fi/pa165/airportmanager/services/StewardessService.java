/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.Stewardess;
import java.util.List;

/**
 *
 * @author Marek
 */
public interface StewardessService {
    /**
     * Create new stewardess
     * 
     * @param stewardess stewardess we want to store.
     * @throws NullPointerException when argument is null.
     * @return nothing.
     */
    void create(Stewardess stewardess);
    /**
     * Returns Stewardess entity with given id.
     * 
     * @throws NullPointerException when id is null.
     * @param id Id of destination entity.
     * @return Stewardess with given id or null if stewardess doesnt exists.
     */
    Stewardess get(Long id);
    /**
     * Update existing destination
     * @param stewardess stewardess we want to update
     * @return nothing
     * @throws NullPointerException when given stewardess doesnt exist.
     */
    void update(Stewardess stewardess);
    /**
     * Delete existing stewardess
     * @param stewardess stewardess we want to delete
     * @return nothing
     * @throws NullPointerException when given stewardess doesnt exist.
     */
    void remove(Stewardess stewardess);
    /**
     * Retrieve all stewardess
     * @param nothing
     * @return List with all stewardess.
     */
    List<Stewardess> findAll();
}
