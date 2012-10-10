/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public interface DestinationDAO {
    
    
    /*
     * Returns Person entity with given id
     * 
     * @throws IllegelArgumentException when id is null
     */
    void create (Destination destination);    
    
    Destination get(Long id);
    
    void update (Destination destination);
    
    void remove (Destination destination);
    
    List<Destination> findAll();
    
    
    
    
}
