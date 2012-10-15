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

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public class DestinationDAOImplTest {
    
    private DestinationDAOImpl dao;
    

    
    @Before
    public void setUp() throws Exception {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirportPU");
        dao = new DestinationDAOImpl();
        dao.setEntityManagerFactory(emf);
    }


    /**
     * Test of create method, of class DestinationDAOImpl.
     */
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
        }catch(NullPointerException ex){
        }
    }


    /**
     * Test of get method, of class DestinationDAOImpl.
     */
    @Test
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
        }catch(NullPointerException ex){
        }
        
        
    }

    /**
     * Test of update method, of class DestinationDAOImpl.
     */
    @Test
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
        }catch(NullPointerException ex){
        }
    }

    /**
     * Test of remove method, of class DestinationDAOImpl.
     */
    @Test
    public void testRemove() {
                
        Destination dest = new Destination();
        dest.setCity("Prahaa");
        dest.setCountry("Czech Republicc");       
           
        
        dao.create(dest);
        
         
        dao.remove(dest);       
            
        
        assertNull(dao.get(dest.getId()));
        
        try{
            dao.remove(null);
            fail("You can delete null destination");
        }catch(NullPointerException ex){
        }
    }
        
        
        
        
    

    /**
     * Test of findAll method, of class DestinationDAOImpl.
     */
    @Test
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
    public void testFindByCountry() {
        System.out.println("findByCountry");
        
    }

    /**
     * Test of findByCity method, of class DestinationDAOImpl.
     */
    @Test
    public void testFindByCity() {
        System.out.println("findByCity");
        
    }
}
