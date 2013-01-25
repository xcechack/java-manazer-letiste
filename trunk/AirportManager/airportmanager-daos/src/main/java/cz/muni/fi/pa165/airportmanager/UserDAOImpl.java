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
 * @author Marek
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void create(User user) {
        if(user == null){
            throw new IllegalArgumentException("User must not be null");
        }
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("User's name must not be null or empty");
        }
        
        em.persist(user);
    }

    public User get(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id must not be null");
        }
        
        User user = em.find(User.class, id);
        
        return user;
    }

    public void update(User user) {
        if(user == null || user.getId() == null){
            throw new IllegalArgumentException("User or his id must not be null");
        }
        em.merge(user);
    }

    public void delete(User user) {
        if(user == null || user.getId() == null){
            throw new IllegalArgumentException("User or his id must be null");
        }
        
        User userToDelete = em.find(User.class, user.getId());
        
        em.remove(userToDelete);
    }

    public List<User> findAll() {
        List<User> list;
        list = em.createQuery("SELECT s FROM REGISTERED_USERS s").getResultList();
        return list;
    }

    public User getUserByUsername(String username) {
        if(username == null){
            throw new IllegalArgumentException("Usename must not be null");
        }
        
        User user = em.find(User.class, username);
        
        return user;
    }
    
}
