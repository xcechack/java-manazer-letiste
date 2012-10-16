/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Vasa
 */
public class PlaneDAOImpl implements PlaneDAO{
    
    private EntityManagerFactory entityManagerFactory;

    public void create(Plane plane) {
        if(plane == null)throw new IllegalArgumentException("plane is null");
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
            entityManager.persist(plane);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Plane get(Long id) {
        if(id == null)throw new IllegalArgumentException("id is null");
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
            Plane foundPlane = entityManager.find(Plane.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return foundPlane;
    }

    public void update(Plane plane) {
        if(plane == null)throw new IllegalArgumentException("id is null");
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
            entityManager.merge(plane);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void remove(Plane plane) {
        if(plane == null)throw new IllegalArgumentException("id is null");
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
            entityManager.remove(plane);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Plane> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Plane> result;
        entityManager.getTransaction().begin();
            result = entityManager.createQuery("SELECT * FROM planes").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public List<Plane> findByProducer(String producer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Plane> result;
        entityManager.getTransaction().begin();
            result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.producer = :produc").setParameter("produc", producer).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public List<Plane> findByType(String type) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Plane> result;
        entityManager.getTransaction().begin();
            result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.type = :type").setParameter("type", type).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public List<Plane> findByMaxNumberOfSeats(int number) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Plane> result;
        entityManager.getTransaction().begin();
            result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.numberseats = :number").setParameter("number", number).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public List<Plane> findPlaneWithGreaterNumberOfSeats(int number) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Plane> result;
        entityManager.getTransaction().begin();
            result = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.numberSeats >= :number").setParameter("number", number).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        entityManagerFactory = emf;
    }
    
}
