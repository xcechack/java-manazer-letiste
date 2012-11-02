/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Frkal
 */
@Repository
public class StewardessDAOImpl implements StewardessDAO {

    
    @PersistenceContext
    private EntityManager em;


    public void create(Stewardess stewardess) {
        if (stewardess == null) {
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
        
        em.persist(stewardess);
        
    }

    public Stewardess get(Long id) {
        if (id == null) {
            throw new NullPointerException("Id must not be null.");
        }
        
        Stewardess stew = em.find(Stewardess.class, id);
        
        return stew;
    }

    public void update(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Stewardess must not be null.");
        }
        
        em.merge(stewardess);
       
    }

    public void remove(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Stewardess must not be null.");
        }
       
        Stewardess managedEntity = em.find(Stewardess.class, stewardess.getId());
        em.remove(managedEntity);
        
    }

    public List<Stewardess> findAll() {
        
        List<Stewardess> list;
        list = em.createQuery("SELECT s FROM Stewardess s").getResultList();
     
        return Collections.unmodifiableList(list);
    }
}
