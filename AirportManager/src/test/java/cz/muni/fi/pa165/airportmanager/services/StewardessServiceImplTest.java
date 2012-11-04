/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.Stewardess;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import cz.muni.fi.pa165.airportmanager.StewardessDTO;
import cz.muni.fi.pa165.airportmanager.enums.Sex;
import cz.muni.fi.pa165.airportmanager.mock.StewardessDAOMock;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frkal
 */
public class StewardessServiceImplTest {
    
    private StewardessDAO stewardessDAO;
    private StewardessServiceImpl instance;
    
    public StewardessServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        stewardessDAO = new StewardessDAOMock();
        instance = new StewardessServiceImpl();
        instance.setsDAO(stewardessDAO);//setDestinationDAO(destinationDAO);
    
    }
    
    /**
     * Test of create method, of class StewardessServiceImpl.
     */
    @Test
    public void testCreate() {
        StewardessDTO newSDTO = new StewardessDTO();
        newSDTO.setBirthNumber("111111/6587");
        newSDTO.setName("Jane");
        newSDTO.setSex(Sex.female);
        newSDTO.setSurname("Doe");
        instance.create(newSDTO);
        StewardessDTO stewFromDB = instance.get(Long.valueOf(0));
        
        assertEquals(newSDTO.getBirthNumber(), stewFromDB.getBirthNumber());
        assertEquals(newSDTO.getName(), stewFromDB.getName());
        assertEquals(newSDTO.getSex(), stewFromDB.getSex());
        assertEquals(newSDTO.getSurname(), stewFromDB.getSurname());
        
        try{
            instance.create(null);
            fail("Method create can be called with null value.");
        }catch(NullPointerException ex){
        }
    }

    /**
     * Test of get method, of class StewardessServiceImpl.
     */
    @Test
    public void testGet() {
        StewardessDTO stew1 = new StewardessDTO();
        stew1.setBirthNumber("111111/6587");
        stew1.setName("Jane");
        stew1.setSex(Sex.female);
        stew1.setSurname("Doe");
        
        
        StewardessDTO stew2 = new StewardessDTO();
        stew2.setBirthNumber("222222/6587");
        stew2.setName("Johnnie");
        stew2.setSex(Sex.male);
        stew2.setSurname("Walker");
        //create
        instance.create(stew1);
        instance.create(stew2);
        
        StewardessDTO stew1FromDB = instance.get(Long.valueOf(0));
        StewardessDTO stew2FromDB = instance.get(Long.valueOf(1));
        
        //test 
        assertNotNull(stew1.getId());
        assertNotNull(stew2.getId());
        
        //Stewardess stew1FromDB = dao.get(stew1.getId());
        //Stewardess stew2FromDB = dao.get(stew2.getId());
        
        
       // assertEquals(stew1.getId(), stew1FromDB.getId());
        assertEquals(stew1.getBirthNumber(), stew1FromDB.getBirthNumber());
        assertEquals(stew1.getName(), stew1FromDB.getName());
        assertEquals(stew1.getSex(), stew1FromDB.getSex());
        assertEquals(stew1.getSurname(), stew1FromDB.getSurname());
        
        //assertEquals(stew2.getId(), stew2FromDB.getId());
        assertEquals(stew2.getBirthNumber(), stew2FromDB.getBirthNumber());
        assertEquals(stew2.getName(), stew2FromDB.getName());
        assertEquals(stew2.getSex(), stew2FromDB.getSex());
        assertEquals(stew2.getSurname(), stew2FromDB.getSurname());
        
        try{
            instance.get(null);
            fail("Method can get stewardess with null id.");
        }catch(NullPointerException ex){
        }
    }

    /**
     * Test of update method, of class StewardessServiceImpl.
     */
    @Test
    public void testUpdate() {
        StewardessDTO stew1 = new StewardessDTO();
        stew1.setBirthNumber("222222/6587");
        stew1.setName("Johnnie");
        stew1.setSex(Sex.male);
        stew1.setSurname("Walker");
        //create
        instance.create(stew1);
       
        stew1.setName("Jack");
        stew1.setSurname("Daniels");
        stew1.setBirthNumber("333222/55555");
        stew1.setSex(Sex.female);
        
        
        instance.update(stew1);
        assertNotNull(stew1.getId());
        
        //Stewardess stew1FromDB = dao.get(stew1.getId());
        
        //assertEquals(stew1.getId(), stew1FromDB.getId());
        StewardessDTO stew1FromDB = instance.get(Long.valueOf(0));
        assertEquals(stew1.getBirthNumber(), stew1FromDB.getBirthNumber());
        assertEquals(stew1.getName(), stew1FromDB.getName());
        assertEquals(stew1.getSex(), stew1FromDB.getSex());
        assertEquals(stew1.getSurname(), stew1FromDB.getSurname());
        
        try{
            instance.update(null);
            fail("Method can update stewardess with null value.");
        }catch(NullPointerException ex){
        } 
        
    }

    /**
     * Test of remove method, of class StewardessServiceImpl.
     */
    @Test
    public void testRemove() {
        StewardessDTO stew = new StewardessDTO();
        stew.setBirthNumber("222222/6587");
        stew.setName("Johnnie");
        stew.setSex(Sex.male);
        stew.setSurname("Walker");
        //create
        instance.create(stew);
        //test
        assertNotNull(stew.getId());
        
        instance.remove(stew);
             
        assertNull(instance.get(stew.getId()));
        try{
            instance.remove(null);
            fail("Method can remove null stewardess.");
        }catch(NullPointerException ex){
        }
    }

    /**
     * Test of findAll method, of class StewardessServiceImpl.
     */
    @Test
    public void testFindAll() {
        StewardessDTO stew1 = new StewardessDTO();
        stew1.setBirthNumber("111111/6587");
        stew1.setName("Jane");
        stew1.setSex(Sex.female);
        stew1.setSurname("Doe");
        
        StewardessDTO stew2 = new StewardessDTO();
        stew2.setBirthNumber("222222/6587");
        stew2.setName("Johnnie");
        stew2.setSex(Sex.male);
        stew2.setSurname("Walker");
                
        StewardessDTO stew3 = new StewardessDTO();
        stew3.setBirthNumber("222222/6587");
        stew3.setName("Johnnie");
        stew3.setSex(Sex.male);
        stew3.setSurname("Walker");
        
        //create
        instance.create(stew1);
        instance.create(stew2);
        instance.create(stew3);
        
        //test 
        List<StewardessDTO> list = instance.findAll();
        
        assertTrue(list.contains(stew1));
        assertTrue(list.contains(stew2));
        assertTrue(list.contains(stew3));
        

    }
}
