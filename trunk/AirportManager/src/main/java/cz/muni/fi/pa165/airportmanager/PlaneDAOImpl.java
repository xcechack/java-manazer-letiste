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
    
    public PlaneDAOImpl()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("AirportPU");
    }

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

    public void removeAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM planes");
        entityManager.getTransaction().commit();
        //entityManager.close();
    }

    public List<Plane> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        entityManager.getTransaction().begin();
            query = entityManager.createQuery("SELECT * FROM planes", Plane.class);
        entityManager.getTransaction().commit();
        //entityManager.close();
        return query.getResultList();
    }

    public List<Plane> findByProducer(String producer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        entityManager.getTransaction().begin();
            query = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.producer = :produc", Plane.class).setParameter("produc", producer);
        entityManager.getTransaction().commit();
        //entityManager.close();
        return query.getResultList();
    }

    public List<Plane> findByType(String type) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        entityManager.getTransaction().begin();
            query = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.type = :type", Plane.class).setParameter("type", type);
        entityManager.getTransaction().commit();
        //entityManager.close();
        return query.getResultList();
    }

    public List<Plane> findByMaxNumberOfSeats(int number) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        entityManager.getTransaction().begin();
            query = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.numberseats = :number", Plane.class).setParameter("number", number);
        entityManager.getTransaction().commit();
        //entityManager.close();
        return query.getResultList();
    }

    public List<Plane> findPlaneWithGreaterNumberOfSeats(int number) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        entityManager.getTransaction().begin();
            query = entityManager.createQuery("SELECT p FROM " + Plane.class.getName() + " p WHERE p.numberSeats >= :number", Plane.class).setParameter("number", number);
        entityManager.getTransaction().commit();
        //entityManager.close();
        return query.getResultList();
    }
    
}
