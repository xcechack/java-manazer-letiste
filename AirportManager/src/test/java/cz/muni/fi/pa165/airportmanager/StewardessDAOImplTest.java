/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.enums.Sex;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Frkal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class StewardessDAOImplTest{
    
    @Autowired
    private StewardessDAO dao;
 
    
    @Before
    public void setUp() throws Exception {}


    /**
     * Test of create method, of class StewardessDAOImpl.
     */
    @Test
    @Transactional
    public void testCreate() {
        System.out.println("create");
        //PREPARATION
        Stewardess stew = new Stewardess();
        stew.setBirthNumber("111111/6587");
        stew.setName("Jane");
        stew.setSex(Sex.female);
        stew.setSurname("Doe");
        
        //create
        dao.create(stew);
        
        //test
        assertNotNull(stew.getId());
        Long id = stew.getId();
        
        Stewardess stewFromDB = dao.get(id);
        
        assertEquals(stew.getId(), stewFromDB.getId());
        assertEquals(stew.getBirthNumber(), stewFromDB.getBirthNumber());
        assertEquals(stew.getName(), stewFromDB.getName());
        assertEquals(stew.getSex(), stewFromDB.getSex());
        assertEquals(stew.getSurname(), stewFromDB.getSurname());
        
        //non-standard situations
        try{
            dao.create(null);
            fail("Method create can be called with null value.");
        }catch(DataAccessException ex){
        }
    }

    /**
     * Test of get method, of class StewardessDAOImpl.
     */
    @Test
    @Transactional
    public void testGet() {
        System.out.println("get");
        //PREPARATION
        Stewardess stew1 = new Stewardess();
        stew1.setBirthNumber("111111/6587");
        stew1.setName("Jane");
        stew1.setSex(Sex.female);
        stew1.setSurname("Doe");
        
        Stewardess stew2 = new Stewardess();
        stew2.setBirthNumber("222222/6587");
        stew2.setName("Johnnie");
        stew2.setSex(Sex.male);
        stew2.setSurname("Walker");
        //create
        dao.create(stew1);
        dao.create(stew2);
        
        
        //test 
        assertNotNull(stew1.getId());
        assertNotNull(stew2.getId());
        
        Stewardess stew1FromDB = dao.get(stew1.getId());
        Stewardess stew2FromDB = dao.get(stew2.getId());
        
        
        assertEquals(stew1.getId(), stew1FromDB.getId());
        assertEquals(stew1.getBirthNumber(), stew1FromDB.getBirthNumber());
        assertEquals(stew1.getName(), stew1FromDB.getName());
        assertEquals(stew1.getSex(), stew1FromDB.getSex());
        assertEquals(stew1.getSurname(), stew1FromDB.getSurname());
        
        assertEquals(stew2.getId(), stew2FromDB.getId());
        assertEquals(stew2.getBirthNumber(), stew2FromDB.getBirthNumber());
        assertEquals(stew2.getName(), stew2FromDB.getName());
        assertEquals(stew2.getSex(), stew2FromDB.getSex());
        assertEquals(stew2.getSurname(), stew2FromDB.getSurname());
        
        try{
            dao.get(null);
            fail("Method can get stewardess with null id.");
        }catch(DataAccessException ex){
        }

    }

    /**
     * Test of update method, of class StewardessDAOImpl.
     */
    @Test
    @Transactional
    public void testUpdate() {
        System.out.println("update");
        //PREPARATION
        Stewardess stew1 = new Stewardess();
        stew1.setBirthNumber("222222/6587");
        stew1.setName("Johnnie");
        stew1.setSex(Sex.male);
        stew1.setSurname("Walker");
        //create
        dao.create(stew1);
       
        stew1.setName("Jack");
        stew1.setSurname("Daniels");
        stew1.setBirthNumber("333222/55555");
        stew1.setSex(Sex.female);
        
        
        dao.update(stew1);
        assertNotNull(stew1.getId());
        
        Stewardess stew1FromDB = dao.get(stew1.getId());
        
        assertEquals(stew1.getId(), stew1FromDB.getId());
        assertEquals(stew1.getBirthNumber(), stew1FromDB.getBirthNumber());
        assertEquals(stew1.getName(), stew1FromDB.getName());
        assertEquals(stew1.getSex(), stew1FromDB.getSex());
        assertEquals(stew1.getSurname(), stew1FromDB.getSurname());
        
        try{
            dao.update(null);
            fail("Method can update stewardess with null value.");
        }catch(DataAccessException ex){
        }
        
    }

    /**
     * Test of remove method, of class StewardessDAOImpl.
     */
    @Test
    @Transactional
    public void testRemove() {
        System.out.println("remove");
        //PREPARATION
        Stewardess stew = new Stewardess();
        stew.setBirthNumber("222222/6587");
        stew.setName("Johnnie");
        stew.setSex(Sex.male);
        stew.setSurname("Walker");
        //create
        dao.create(stew);
        //test
        assertNotNull(stew.getId());
        
        dao.remove(stew);
             
        assertNull(dao.get(stew.getId()));
        try{
            dao.remove(null);
            fail("Method can remove null stewardess.");
        }catch(DataAccessException ex){
        }
        
    }

    /**
     * Test of findAll method, of class StewardessDAOImpl.
     */
    @Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        //PREPARATION
        Stewardess stew1 = new Stewardess();
        stew1.setBirthNumber("111111/6587");
        stew1.setName("Jane");
        stew1.setSex(Sex.female);
        stew1.setSurname("Doe");
        
        Stewardess stew2 = new Stewardess();
        stew2.setBirthNumber("222222/6587");
        stew2.setName("Johnnie");
        stew2.setSex(Sex.male);
        stew2.setSurname("Walker");
                
        Stewardess stew3 = new Stewardess();
        stew3.setBirthNumber("222222/6587");
        stew3.setName("Johnnie");
        stew3.setSex(Sex.male);
        stew3.setSurname("Walker");
        
        //create
        dao.create(stew1);
        dao.create(stew2);
        dao.create(stew3);
        
        //test 
        List<Stewardess> list = dao.findAll();
        
        assertTrue(list.contains(stew1));
        assertTrue(list.contains(stew2));
        assertTrue(list.contains(stew3));
        

    }
}
