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

/**
 *
 * @author Frkal
 */
public class StewardessDAOImplTest extends TestCase {
    private StewardessDAOImpl dao;
    
    public StewardessDAOImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirportPU");
        dao = new StewardessDAOImpl();
        dao.setEntityManagerFactory(emf);
    }


    /**
     * Test of create method, of class StewardessDAOImpl.
     */
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
        }catch(NullPointerException ex){
        }
    }

    /**
     * Test of get method, of class StewardessDAOImpl.
     */
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
        }catch(NullPointerException ex){
        }

    }

    /**
     * Test of update method, of class StewardessDAOImpl.
     */
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
        }catch(NullPointerException ex){
        }
        
    }

    /**
     * Test of remove method, of class StewardessDAOImpl.
     */
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
        
        
    }

    /**
     * Test of findAll method, of class StewardessDAOImpl.
     */
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
