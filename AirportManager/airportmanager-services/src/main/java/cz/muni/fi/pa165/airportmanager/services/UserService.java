/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.UserDTO;
import java.util.List;

/**
 *
 * @author Marek
 */
public interface UserService {
    /**
     * Create new stewardess
     * 
     * @param userDTO user we want to store.
     * @throws NullPointerException when argument is null.
     * @return nothing.
     */
    void create(UserDTO userDTO);
    /**
     * Update existing user
     * @param userDTO user we want to update
     * @return nothing
     * @throws NullPointerException when given stewardess doesnt exist.
     */
    void update(UserDTO userDTO);
    /**
     * Delete existing user
     * @param userDTO user we want to delete
     * @return nothing
     * @throws NullPointerException when given stewardess doesnt exist.
     */
    void delete(UserDTO userDTO);
    /**
     * Returns User entity with given id.
     * 
     * @throws NullPointerException when id is null.
     * @param id Id of user entity.
     * @return user with given id or null if user doesnt exists.
     */
    UserDTO get(Long id);
    /**
     * Retrieve all users
     * @param nothing
     * @return List with all users.
     */
    List<UserDTO> findAll();
    /**
     * Retrieve entity with given username
     * 
     * @param username username to find
     * @retur user with given username or null if user doesn't exists.
     * @thows NullPointerException when username is null or empty.
     */
    UserDTO findByUsername(String username);
    
}
