/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public interface DestinationDAO {
    
    /**
     * Creates new destination
     * 
     * @param destination Destination we want to store.
     * @throws NullPointerException when given destination is null. 
     * @return nothing.
     */
    void create(Destination destination);
    
    
}