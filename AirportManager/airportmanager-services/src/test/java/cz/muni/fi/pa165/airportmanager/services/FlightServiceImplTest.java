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
    
    
    private DestinationServiceImpl destDAO;
    
    private PlaneServiceImpl planeDAO;
    
    private StewardessServiceImpl stewardessDAO;
    
    
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
        //PREPARATION
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
        
        //CREATE
        service.create(flight);
        
        //ASSERT
        assertNotNull(flight.getId());
        FlightDTO flightFormDb = service.get(flight.getId());
        assertNotNull(flightFormDb);
        
        assertFlightEquals(flightFormDb, flight);
        
        //NON-STANDARD SITUATIONS
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
        FlightDTO flight = new FlightDTO();
        flight.setFlightIdentifier("testFlight");

        service.create(flight);
        assertEquals(service.get(flight.getId()), flight);
    }

    /**
     * Test of findByIdentifier method, of class FlightServiceImpl.
     */
    @Test
    public void testFindByIdentifier() {
        FlightDTO flight = new FlightDTO();
        flight.setFlightIdentifier("testFlightID");

        service.create(flight);
        List<FlightDTO> expList = new ArrayList<FlightDTO>();
        expList.add(flight);
        assertEquals(service.findByIdentifier(flight.getFlightIdentifier()), expList);
    

    }

    /**
     * Test of update method, of class FlightServiceImpl.
     */
    @Test
    public void testUpdate() {
        FlightDTO flight = new FlightDTO();
        flight.setFlightIdentifier("testFlightID2");
        service.create(flight);

        flight.setFlightIdentifier("updatedFlightID");
        service.update(flight);

        assertEquals(service.get(flight.getId()).getFlightIdentifier(), flight.getFlightIdentifier());
    }

    /**
     * Test of remove method, of class FlightServiceImpl.
     */
    @Test
    public void testRemove() {
        service.removeAll();
        FlightDTO fl1 = new FlightDTO();
        fl1.setFlightIdentifier("TEST1");
        FlightDTO fl2 = new FlightDTO();
        fl2.setFlightIdentifier("TEST2");

        service.create(fl1);
        service.create(fl2);

        service.remove(fl2);
        if (service.findAll().contains(fl2)) {
            fail("Failed removing flight 2");
        }

        service.remove(fl1);

        if (service.findAll().size() > 0) {
            fail("Error removing flight 1");
        }

    }

    /**
     * Test of removeAll method, of class FlightServiceImpl.
     */
    @Test
    public void testRemoveAll() {
        FlightDTO fl1 = new FlightDTO();
        fl1.setFlightIdentifier("TEST1");
        FlightDTO fl2 = new FlightDTO();
        fl2.setFlightIdentifier("TEST2");
        service.create(fl1);
        service.create(fl2);

        service.removeAll();

        if (service.findAll().size() > 0) {
            fail("Failed to remove all flights from DB");
            System.out.println(service.findAll());
        }
    

    }

    /**
     * Test of findAll method, of class FlightServiceImpl.
     */
    @Test
    public void testFindAll() {
        FlightDTO fl1 = new FlightDTO();
        fl1.setFlightIdentifier("TEST1");
        FlightDTO fl2 = new FlightDTO();
        fl2.setFlightIdentifier("TEST2");

        service.create(fl1);
        service.create(fl2);

        List<FlightDTO> expList = new ArrayList<FlightDTO>();
        expList.add(fl1);
        expList.add(fl2);

        assertEquals(service.findAll(), expList);

        service.remove(fl1);
        service.remove(fl2);
        System.out.println("--size: " + service.findAll().size());
        if (service.findAll().size() > 0) {
            fail("Failed getting all flights from empty DB, should return NULL");
        }
    }

    /**
     * Test of findFlightsByDepartureDestination method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByDepartureDestination() {
        FlightDTO fl1 = new FlightDTO();
        FlightDTO fl2 = new FlightDTO();
        FlightDTO fl3 = new FlightDTO();

        DestinationDTO dest = new DestinationDTO();
        dest.setCountry("slovakia");
        DestinationDTO dest2 = new DestinationDTO();
        dest2.setCountry("czech rep.");
        DestinationDTO dest3 = new DestinationDTO();
        dest3.setCountry("hungary");

        fl1.setDestinationStart(dest2);
        fl1.setDestinationArrival(dest);
        fl1.setFlightIdentifier("test1");

        fl2.setDestinationStart(dest);
        fl2.setDestinationArrival(dest3);
        fl2.setFlightIdentifier("test2");

        fl3.setDestinationStart(dest2);
        fl3.setDestinationArrival(dest3);
        fl3.setFlightIdentifier("test3");

        List<FlightDTO> expList = new ArrayList<FlightDTO>();
        expList.add(fl1);

        expList.add(fl3);

        //destDAO.create(dest);
        //destDAO.create(dest2);
        //destDAO.create(dest3);


        service.create(fl1);
        service.create(fl2);
        service.create(fl3);

        assertEquals(service.findFlightsByDepartureDestination(dest2), expList);

    }

    /**
     * Test of findFlightsByArrivalDestination method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByArrivalDestination() {
        FlightDTO fl1 = new FlightDTO();
        FlightDTO fl2 = new FlightDTO();
        FlightDTO fl3 = new FlightDTO();

        DestinationDTO dest = new DestinationDTO();
        dest.setCountry("slovakia");
        DestinationDTO dest2 = new DestinationDTO();
        dest2.setCountry("czech rep.");
        DestinationDTO dest3 = new DestinationDTO();
        dest3.setCountry("hungary");

        fl1.setDestinationStart(dest2);
        fl1.setDestinationArrival(dest);
        fl1.setFlightIdentifier("test1");

        fl2.setDestinationStart(dest);
        fl2.setDestinationArrival(dest3);
        fl2.setFlightIdentifier("test2");

        fl3.setDestinationStart(dest3);
        fl3.setDestinationArrival(dest);
        fl3.setFlightIdentifier("test3");

        List<FlightDTO> expList = new ArrayList<FlightDTO>();
        expList.add(fl1);

        expList.add(fl3);



        service.create(fl1);
        service.create(fl2);
        service.create(fl3);

        assertEquals(service.findFlightsByArrivalDestination(dest), expList);
    }

    /**
     * Test of findFlightsByPlane method, of class FlightServiceImpl.
     */
    @Test
    public void testFindFlightsByPlane() {
        FlightDTO fl1 = new FlightDTO();
        FlightDTO fl2 = new FlightDTO();
        FlightDTO fl3 = new FlightDTO();

        fl1.setFlightIdentifier("test1");
        fl2.setFlightIdentifier("test2");
        fl3.setFlightIdentifier("test3");

        PlaneDTO pl1 = new PlaneDTO();
        PlaneDTO pl2 = new PlaneDTO();
        PlaneDTO pl3 = new PlaneDTO();

        pl1.setType("A380");
        pl2.setType("A340");
        pl3.setType("A319");

        fl1.setPlane(pl1);
        fl2.setPlane(pl2);
        fl3.setPlane(pl1);

        List<FlightDTO> expList = new ArrayList<FlightDTO>();
        expList.add(fl1);
        expList.add(fl3);



        service.create(fl1);
        service.create(fl2);
        service.create(fl3);

        assertEquals(service.findFlightsByPlane(pl1), expList);
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
    
    private static void assertFlightEquals(FlightDTO f1, FlightDTO f2){
        assertEquals(f1.getDestinationArrival(), f2.getDestinationArrival());
        assertEquals(f1.getDestinationStart(), f2.getDestinationStart());
        assertEquals(f1.getFlightIdentifier(), f2.getFlightIdentifier());
        assertEquals(f1.getId(), f2.getId());
        assertEquals(f1.getPlane(), f2.getPlane());
        assertEquals(f1.getTimeArrival(), f2.getTimeArrival());
        assertEquals(f1.getTimeStart(), f2.getTimeStart());
        
        if(f1.getStewardess() == null){
            assertNull(f2.getStewardess());
        }else{
            assertNotNull(f2.getStewardess());
            for(int i = 0; i < f1.getStewardess().size(); i++){
                assertEquals(f1.getStewardess().get(i), f2.getStewardess().get(i));
            }
        }  
    }
}
