/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DestinationDAOImplTest {
    
    @Autowired
    private DestinationDAO dao;
    

    
    @Before
    public void setUp() throws Exception {}


    /**
     * Test of create method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testCreate() {
        System.out.println("create");
        
        Destination dest = new Destination();
        dest.setCity("Praha");
        dest.setCountry("Czech Republic");       
        
        
        dao.create(dest);
        
        
        assertNotNull(dest.getId());
        Long id = dest.getId();
        
        Destination destDB = dao.get(id);
        
        assertEquals(dest.getId(), destDB.getId());
        assertEquals(dest.getCity(), destDB.getCity());
        assertEquals(dest.getCountry(), destDB.getCountry());
        
        
        
        try{
            dao.create(null);
            fail("You can create null destination");
        }catch(DataAccessException ex){
        }
    }


    /**
     * Test of get method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testGet() {
        System.out.println("get");
         
        
        Destination dest = new Destination();
        dest.setCity("Praha");
        dest.setCountry("Czech Republic");       
        
        Destination dest2 = new Destination();
        dest2.setCity("Paříž");
        dest2.setCountry("Francie");
        
        dao.create(dest);
        dao.create(dest2);
        
        assertNotNull(dest.getId());
        Long id = dest.getId();
        
        assertNotNull(dest2.getId());
        Long id2 = dest2.getId();
        
        Destination destDB = dao.get(id);
        Destination destDB2 = dao.get(id2);
        
        assertEquals(dest.getId(), destDB.getId());
        assertEquals(dest.getCity(), destDB.getCity());
        assertEquals(dest.getCountry(), destDB.getCountry());
        
        assertEquals(dest2.getId(), destDB2.getId());
        assertEquals(dest2.getCity(), destDB2.getCity());
        assertEquals(dest2.getCountry(), destDB2.getCountry());
        
        
        
        try{
            dao.get(null);
            fail("You can get destination with null id");
        }catch(DataAccessException ex){
        }
        
        
    }

    /**
     * Test of update method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testUpdate() {
        System.out.println("update");
        
        Destination dest = new Destination();
        dest.setCity("Praha");
        dest.setCountry("Czech Republic");       
        
        
        
        dao.create(dest);
        
        dest.setCity("Paříž");
        dest.setCountry("Francie");
        
        dao.update(dest);
        
        assertNotNull(dest.getId());
        Long id = dest.getId();
        
        
        
        Destination destDB = dao.get(id);
        
        
        assertEquals(dest.getId(), destDB.getId());
        assertEquals(dest.getCity(), destDB.getCity());
        assertEquals(dest.getCountry(), destDB.getCountry());     
        
        
        
        try{
            dao.update(null);
            fail("You can update null destination");
        }catch(DataAccessException ex){
        }
    }

    /**
     * Test of remove method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testRemove() {
                
        Destination dest = new Destination();
        dest.setCity("Prahaa");
        dest.setCountry("Czech Republicc");       
         
        
        dao.create(dest);
        Long id = dest.getId();
         
        dao.remove(dest);       
            
        
        assertNull(dao.get(id));
        
        try{
            dao.remove(null);
            fail("You can delete null destination");
        }catch(DataAccessException ex){
        }
    }
        
        
        
        
    

    /**
     * Test of findAll method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        
        Destination dest = new Destination();
        dest.setCity("Praha");
        dest.setCountry("Czech Republic");       
        
        Destination dest2 = new Destination();
        dest2.setCity("Paříž");
        dest2.setCountry("Francie");
        
        dao.create(dest);
        dao.create(dest2);
         
        List<Destination> destList = dao.findAll();        
        
        assertTrue(destList.contains(dest));
        assertTrue(destList.contains(dest2));
        assertEquals(2, destList.size());
        
    }

    /**
     * Test of findByCountry method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testFindByCountry() {
        System.out.println("findByCountry");
        
        Destination dest = new Destination();
        dest.setCity("Praha");
        dest.setCountry("Czech Republic");       
        
        Destination dest2 = new Destination();
        dest2.setCity("Paříž");
        dest2.setCountry("Francie");
        
        
        Destination dest3 = new Destination();
        dest3.setCity("Lyon");
        dest3.setCountry("Francie");
        
        dao.create(dest);
        dao.create(dest2);
        dao.create(dest3);
         
        List<Destination> destList = dao.findByCountry("Francie");        
        
        assertFalse(destList.contains(dest));
        assertTrue(destList.contains(dest2));
        assertTrue(destList.contains(dest3));
        assertEquals(2, destList.size());
        
        try{
            dao.findByCountry(null);
            fail("You can find by country when country is null");
        }catch(DataAccessException ex){
        }
        
    }

    /**
     * Test of findByCity method, of class DestinationDAOImpl.
     */
    @Test
    @Transactional
    public void testFindByCity() {
        System.out.println("findByCity");
        Destination dest = new Destination();
        dest.setCity("Praha");
        dest.setCountry("Czech Republic");       
        
        Destination dest2 = new Destination();
        dest2.setCity("Paříž");
        dest2.setCountry("Francie");
        
        
        Destination dest3 = new Destination();
        dest3.setCity("Paříž");
        dest3.setCountry("USA");
        
        dao.create(dest);
        dao.create(dest2);
        dao.create(dest3);
         
        List<Destination> destList = dao.findByCity("Paříž");        
        
        assertFalse(destList.contains(dest));
        assertTrue(destList.contains(dest2));
        assertTrue(destList.contains(dest3));
        assertEquals(2, destList.size());
        
        try{
            dao.findByCity(null);
            fail("You can find by city when country is null");
        }catch(DataAccessException ex){
        }
    }
}
