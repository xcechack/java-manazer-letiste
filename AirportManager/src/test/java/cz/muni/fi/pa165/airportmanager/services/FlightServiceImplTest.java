/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.FlightDAO;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.Plane;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import cz.muni.fi.pa165.airportmanager.PlaneDTO;
import cz.muni.fi.pa165.airportmanager.Stewardess;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import cz.muni.fi.pa165.airportmanager.StewardessDTO;
import cz.muni.fi.pa165.airportmanager.enums.Sex;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import cz.muni.fi.pa165.airportmanager.mock.DestinationDAOMock;
import cz.muni.fi.pa165.airportmanager.mock.FlightDAOMock;
import cz.muni.fi.pa165.airportmanager.mock.PlaneDaoMock;
import cz.muni.fi.pa165.airportmanager.mock.StewardessDAOMock;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
    
    private FlightServiceImpl service;
    
    @Before
    public void setUp() {
        StewardessDAO stewardessDAO = new StewardessDAOMock();
        FlightDAO flightDAO = new FlightDAOMock();
        DestinationDAO destinationDAO = new DestinationDAOMock();
        PlaneDAO planeDAO = new PlaneDaoMock();
        
        service = new FlightServiceImpl();
        service.setdDao(destinationDAO);
        service.setfDao(flightDAO);
        service.setpDao(planeDAO);
        service.setsDao(stewardessDAO);
    }

    /**
     * Test of create method, of class FlightServiceImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");

        List<StewardessDTO> stew = new ArrayList<StewardessDTO>();
        stew.add(generateStewardess(1));
        stew.add(generateStewardess(2));
        
        FlightDTO flight = new FlightDTO();
        flight.setDestinationArrival(generateDestination(2));
        flight.setDestinationStart(generateDestination(1));
        flight.setFlightIdentifier("arl688");
        flight.setPlane(generatePlane(1));
        flight.setStewardess(Collections.unmodifiableList(stew));
        flight.setTimeArrival(new Timestamp(123456789));
        flight.setTimeStart(new Timestamp(123444444));
        
        service.create(flight);
        
        assertNotNull(flight.getId());
        FlightDTO flightFormDb = service.get(flight.getId());
        assertNotNull(flightFormDb);
        
        assertEquals(flight.getDestinationArrival(), flightFormDb.getDestinationArrival());
        assertEquals(flight.getDestinationStart(), flightFormDb.getDestinationStart());
        assertEquals(flight.getFlightIdentifier(), flightFormDb.getFlightIdentifier());
        assertEquals(flight.getId(), flightFormDb.getId());
        assertEquals(flight.getPlane(), flightFormDb.getPlane());
        assertEquals(flight.getTimeArrival(), flightFormDb.getTimeArrival());
        assertEquals(flight.getTimeStart(), flightFormDb.getTimeStart());
        
        assertNotNull(flightFormDb.getStewardess());
        for(int i = 0; i < flight.getStewardess().size(); i++){
            assertEquals(flight.getStewardess().get(i), flightFormDb.getStewardess().get(i));
        }
        
        try{
            service.create(null);
            fail();
        }catch(NullPointerException ex){
        }
    }

    /**
     * Test of get method, of class FlightServiceImpl.
     */
    @Test
    public void testGet() {
        System.out.println("get");

    }

    /**
     * Test of findByIdentifier method, of class FlightServiceImpl.
     */
    @Test
    public void testFindByIdentifier() {
        System.out.println("findByIdentifier");

    }

    /**
     * Test of update method, of class FlightServiceImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");

    }

    /**
     * Test of remove method, of class FlightServiceImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

    }

    /**
     * Test of removeAll method, of class FlightServiceImpl.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");

    }

    /**
     * Test of findAll method, of class FlightServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");

    }

    /**
     * Test of findFlightsByDepartureDestination method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByDepartureDestination() {
        System.out.println("findFlightsByDepartureDestination");

    }

    /**
     * Test of findFlightsByArrivalDestination method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByArrivalDestination() {
        System.out.println("findFlightsByArrivalDestination");

    }

    /**
     * Test of findFlightsByPlane method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByPlane() {
        System.out.println("findFlightsByPlane");

    }
    
    private static StewardessDTO generateStewardess(int sequence){
        StewardessDTO stewardess = new StewardessDTO();
        stewardess.setBirthNumber("111111/" + sequence);
        stewardess.setName("Jane" + sequence);
        stewardess.setSex(Sex.values()[sequence % 2]);
        stewardess.setSurname("Doe" + sequence);
        return stewardess;
    }
    
    private static PlaneDTO generatePlane(int sequence){
        PlaneDTO plane = new PlaneDTO();
        plane.setMaxStewardessNumber((sequence + 1) * 10);
        plane.setNumberSeats(sequence);
        plane.setProducer("Boeing_" + sequence);
        plane.setType(String.valueOf(sequence));
        return plane;
    }
    
    private static DestinationDTO generateDestination(int sequence){
        DestinationDTO dest = new DestinationDTO();
        dest.setCity("City_" + sequence);
        dest.setCountry("Country_" + sequence);
        return dest;
    }
}
