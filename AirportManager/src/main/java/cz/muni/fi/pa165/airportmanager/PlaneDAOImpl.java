/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vasa
 */
@Repository
public class PlaneDAOImpl implements PlaneDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    

    public void create(Plane plane) {
        if(plane == null) {
            throw new IllegalArgumentException("plane is null");
        }
       
        entityManager.persist(plane);
    }

    public Plane get(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
       
        Plane foundPlane = entityManager.find(Plane.class, id);
        
        return foundPlane;
    }

    public void update(Plane plane) {
        if(plane == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        
        entityManager.merge(plane);
    }

    public void remove(Plane plane) {
        if(plane == null || plane.getId() == null) {
            throw new IllegalArgumentException("plane or id is null");
        }
        
        
        Plane getPlane = entityManager.find(Plane.class,plane.getId());
        if(getPlane != null)
        {
            entityManager.remove(getPlane);
        }
        else
        {
            throw new NullPointerException();
        }
       
    }

    public List<Plane> findAll() {
        
        List<Plane> result;
        result = entityManager.createQuery("SELECT Plane FROM " + Plane.class.getName() + " Plane").getResultList();
       
        return result;
    }

    public List<Plane> findByProducer(String producer) {
        if(producer == null) {
            throw new IllegalArgumentException();
        }
        
        List<Plane> result;
        result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.producer = :produc", Plane.class).setParameter("produc", producer).getResultList();
       
        return result;
    }

    public List<Plane> findByType(String type) {
        if(type == null) {
            throw new IllegalArgumentException();
        }
        
        List<Plane> result;
        result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.type = :type", Plane.class).setParameter("type", type).getResultList();
      
        return result;
    }

    public List<Plane> findByMaxNumberOfSeats(int number) {
       
        List<Plane> result;
        result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.numberSeats <= :number", Plane.class).setParameter("number", number).getResultList();
       
        return result;
    }

    public List<Plane> findPlaneWithGreaterNumberOfSeats(int number) {
       
        List<Plane> result;
        result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.numberSeats >= :number", Plane.class).setParameter("number", number).getResultList();
        
        return result;
    }

    
}
