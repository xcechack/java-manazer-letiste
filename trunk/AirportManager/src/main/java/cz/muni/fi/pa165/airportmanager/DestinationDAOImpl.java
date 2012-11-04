/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
@Repository
public class DestinationDAOImpl implements DestinationDAO {

    @PersistenceContext
    private EntityManager em;

    /*public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
     this.entityManagerFactory = entityManagerFactory;
     }*/
    public void create(Destination destination) {
        if (destination == null) {
            throw new NullPointerException("Destination is null when it is created");
        }
        em.persist(destination);
    }

    public Destination get(Long id) {
        if (id == null) {
            throw new NullPointerException("Id destination is null ");
        }

        Destination dest = em.find(Destination.class, id);

        return dest;
    }

    public void update(Destination destination) {
        if (destination == null) {
            throw new NullPointerException("Destination is null when it is updated");
        }

        em.merge(destination);
    }

    public void remove(Destination destination) {
        if (destination == null) {
            throw new NullPointerException("Destination is null when it is removed");
        }

        Destination managedDest = em.find(Destination.class, destination.getId());
        em.remove(managedDest);
    }

    public List<Destination> findAll() {

        List<Destination> dest;
        dest = em.createQuery("SELECT d FROM Destination d").getResultList();

        return Collections.unmodifiableList(dest);
    }

    public List<Destination> findByCountry(String country) {

        if (country == null) {
            throw new NullPointerException("Country is null ");
        }

        List<Destination> dest;
        dest = em.createQuery("SELECT d FROM Destination d WHERE d.country = :country")
                .setParameter("country", country).getResultList();

        return Collections.unmodifiableList(dest);


    }

    public List<Destination> findByCity(String city) {
        if (city == null) {
            throw new NullPointerException("Sity is null ");
        }

        List<Destination> dest;
        dest = em.createQuery("SELECT d FROM Destination d WHERE d.city = :city").setParameter("city", city).getResultList();

        return Collections.unmodifiableList(dest);
    }
}
