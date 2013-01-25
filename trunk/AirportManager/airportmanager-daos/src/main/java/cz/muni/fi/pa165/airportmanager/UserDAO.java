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
public interface UserDAO {
    
    void create(User user);
    
    User get(Long id);
    
    void update(User user);
    
    void delete(User user);
    
    List<User> findAll();
    
    User getUserByUsername(String username);
   
}
