/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import cz.muni.fi.pa165.airportmanager.PlaneDTO;
import cz.muni.fi.pa165.airportmanager.mock.PlaneDaoMock;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public class PlaneServiceImplTest {
    
    private PlaneServiceImpl instance;
    
    public PlaneServiceImplTest() {
        
    }
    
    @Before
    public void setUp() {
        PlaneDAO planeDAO = new PlaneDaoMock();
        instance = new PlaneServiceImpl();
        instance.setpDao(planeDAO);
    }

    

    @Test
    public void testCreate() {
        PlaneDTO plane = newPlane("Boeing","A800", 650, 10);
        instance.create(plane);
        
        Long planeId = plane.getId();
        assertNotNull(planeId);
        
        PlaneDTO result = instance.get(planeId);
        assertEquals(plane, result);
        assertDeepEquals(plane, result);
        
        try
        {
            instance.create(null);
            fail("created null plane");
        }catch(NullPointerException ex){}
    }

    @Test
    public void testGet() {
        System.out.println("get");
        Long id = null;
        PlaneServiceImpl instance = new PlaneServiceImpl();
        PlaneDTO expResult = null;
        PlaneDTO result = instance.get(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        PlaneDTO plane = null;
        PlaneServiceImpl instance = new PlaneServiceImpl();
        instance.update(plane);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        PlaneDTO plane = null;
        PlaneServiceImpl instance = new PlaneServiceImpl();
        instance.remove(plane);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        PlaneServiceImpl instance = new PlaneServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByProducer() {
        System.out.println("findByProducer");
        String producer = "";
        PlaneServiceImpl instance = new PlaneServiceImpl();
        List expResult = null;
        List result = instance.findByProducer(producer);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByType() {
        System.out.println("findByType");
        String type = "";
        PlaneServiceImpl instance = new PlaneServiceImpl();
        List expResult = null;
        List result = instance.findByType(type);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByMaxNumberOfSeats() {
        System.out.println("findByMaxNumberOfSeats");
        int number = 0;
        PlaneServiceImpl instance = new PlaneServiceImpl();
        List expResult = null;
        List result = instance.findByMaxNumberOfSeats(number);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindPlaneWithGreaterNumberOfSeats() {
        System.out.println("findPlaneWithGreaterNumberOfSeats");
        int number = 0;
        PlaneServiceImpl instance = new PlaneServiceImpl();
        List expResult = null;
        List result = instance.findPlaneWithGreaterNumberOfSeats(number);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
    private static PlaneDTO newPlane(String producer, String type, int numberSeats, int maxStewardessNumber)
    {
        PlaneDTO pl = new PlaneDTO();
        pl.setProducer(producer);
        pl.setType(type);
        pl.setNumberSeats(numberSeats);
        pl.setMaxStewardessNumber(maxStewardessNumber);
        
        return pl;
    }
    private void assertDeepEquals(PlaneDTO expected, PlaneDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProducer(), actual.getProducer());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getMaxStewardessNumber(), actual.getMaxStewardessNumber());
        assertEquals(expected.getNumberSeats(), actual.getNumberSeats());
    }
}
