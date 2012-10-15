/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Frkal
 */
public class StewardessDAOImpl implements StewardessDAO {

    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void create(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Stewardess must not be null.");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(stewardess);
        em.getTransaction().commit();
        em.close();
    }

    public Stewardess get(Long id) {
        if (id == null) {
            throw new NullPointerException("Id must not be null.");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Stewardess stew = em.find(Stewardess.class, id);
        em.getTransaction().commit();
        em.close();
        return stew;
    }

    public void update(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Stewardess must not be null.");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(stewardess);
        em.getTransaction().commit();
        em.close();
    }

    public void remove(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Stewardess must not be null.");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Stewardess managedEntity = em.find(Stewardess.class, stewardess.getId());
        em.remove(managedEntity);
        em.getTransaction().commit();
        em.close();
    }

    public List<Stewardess> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Stewardess> list;
        em.getTransaction().begin();
        list = em.createQuery("SELECT s FROM Stewardess s").getResultList();
        em.getTransaction().commit();
        em.close();
        return Collections.unmodifiableList(list);
    }
}
