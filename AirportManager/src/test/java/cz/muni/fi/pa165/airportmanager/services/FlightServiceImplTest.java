/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.FlightDAO;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import cz.muni.fi.pa165.airportmanager.PlaneDTO;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maks
 */
public class FlightServiceImplTest {
    
    public FlightServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setfDao method, of class FlightServiceImpl.
     */
    @Test
    public void testSetfDao() {
        System.out.println("setfDao");
        FlightDAO fDao = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.setfDao(fDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setdDao method, of class FlightServiceImpl.
     */
    @Test
    public void testSetdDao() {
        System.out.println("setdDao");
        DestinationDAO dDao = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.setdDao(dDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setpDao method, of class FlightServiceImpl.
     */
    @Test
    public void testSetpDao() {
        System.out.println("setpDao");
        PlaneDAO pDao = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.setpDao(pDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setsDao method, of class FlightServiceImpl.
     */
    @Test
    public void testSetsDao() {
        System.out.println("setsDao");
        StewardessDAO sDao = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.setsDao(sDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class FlightServiceImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        FlightDTO fDto = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.create(fDto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class FlightServiceImpl.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Long id = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        FlightDTO expResult = null;
        FlightDTO result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdentifier method, of class FlightServiceImpl.
     */
    @Test
    public void testFindByIdentifier() {
        System.out.println("findByIdentifier");
        String identifier = "";
        FlightServiceImpl instance = new FlightServiceImpl();
        List expResult = null;
        List result = instance.findByIdentifier(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class FlightServiceImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        FlightDTO fDto = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.update(fDto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class FlightServiceImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        FlightDTO fDto = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.remove(fDto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAll method, of class FlightServiceImpl.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        FlightServiceImpl instance = new FlightServiceImpl();
        instance.removeAll();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class FlightServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        FlightServiceImpl instance = new FlightServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findFlightsByDepartureDestination method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByDepartureDestination() {
        System.out.println("findFlightsByDepartureDestination");
        DestinationDTO destination = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        List expResult = null;
        List result = instance.findFlightsByDepartureDestination(destination);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findFlightsByArrivalDestination method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByArrivalDestination() {
        System.out.println("findFlightsByArrivalDestination");
        DestinationDTO destination = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        List expResult = null;
        List result = instance.findFlightsByArrivalDestination(destination);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findFlightsByPlane method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByPlane() {
        System.out.println("findFlightsByPlane");
        PlaneDTO plane = null;
        FlightServiceImpl instance = new FlightServiceImpl();
        List expResult = null;
        List result = instance.findFlightsByPlane(plane);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
