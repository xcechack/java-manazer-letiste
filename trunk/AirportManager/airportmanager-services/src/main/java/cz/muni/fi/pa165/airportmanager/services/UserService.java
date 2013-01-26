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
    
    public void create(UserDTO user);
    
    public void delete(UserDTO user);
    
    public UserDTO get(Long id);
    
    public List<UserDTO> findAll();
    
}
