/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;



import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */

@Stateless
public class DestinationDAOImpl implements DestinationDAO {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Destination destination) {
        em.persist(destination);
    } 
}
