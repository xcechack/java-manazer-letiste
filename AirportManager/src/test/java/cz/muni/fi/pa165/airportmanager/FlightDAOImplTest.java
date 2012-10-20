/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.enums.Sex;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marek
 */
public class FlightDAOImplTest {
    
    private FlightDAO flightDAO;
    private DestinationDAO destDAO;
    private PlaneDAO planeDAO;
    public FlightDAOImplTest() {
    }
    
    
    
    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirportTestInMemoryPU-2");
        flightDAO = new FlightDAOImpl();
        flightDAO.setEntityManager(emf);
        
       
        destDAO = new DestinationDAOImpl();
        destDAO.setEntityManagerFactory(emf);
        
        planeDAO = new PlaneDAOImpl();
        planeDAO.setEntityManagerFactory(emf);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of create method, of class FlightDAOImpl.
     */
    @Test
    public void testCreateEmpty() {
        
        Flight flight = new Flight();
        //flight.setFlightIdentifier("testFlightIdentifier");
        
        flightDAO.create(flight);
        if(flight.getId() == null){
            fail("Empty Flight create fail.");
        }
    }
    
    @Test
    public void testNullCreate(){
        Flight flight = null;
        try{
            flightDAO.create(flight);
            fail("Test null create fail.");
        }catch(IllegalArgumentException ex){
            
        }
        
    }
    
    @Test
    public void testCreate(){
        Plane airbus = new Plane();
        airbus.setProducer("Airbus");
        airbus.setType("A380");
        airbus.setNumberSeats(800);
        airbus.setMaxStewardessNumber(12);
        
        Destination bratislava = new Destination();
        bratislava.setCountry("Slovakia");
        bratislava.setCity("Bratislava");

        Destination kosice = new Destination();
        kosice.setCountry("Slovakia");
        kosice.setCity("Kosice");
        
        Flight ar123 = new Flight();
        ar123.setDestinationStart(bratislava);
        ar123.setDestinationArrival(kosice);
        ar123.setPlane(airbus);
        
        ar123.setTimeArrival(new Timestamp(1234556000));
        ar123.setTimeArrival(new Timestamp(1238890000));
        
        ar123.setFlightIdentifier("AR123");
        
        Stewardess st1 = new Stewardess();
        st1.setName("Jozin");
        st1.setSurname("Bazin");
        st1.setSex(Sex.male);
        Stewardess st2 = new Stewardess();
        st2.setName("Fero");
        st2.setSurname("Taraba");
        st2.setSex(Sex.male);
        
        List<Stewardess> stewards = new ArrayList<Stewardess>();
        stewards.add(st1);
        stewards.add(st2);
        
        ar123.setStewardess(stewards);
        
        flightDAO.create(ar123);
        
        if(ar123.getId()==null){
            fail("Create flight test fail");
        }
    }
    
    @Test
    public void testCreateWithExistingPlane(){
          Plane airbus = new Plane();
          airbus.setProducer("Airbus");
          airbus.setType("A380");
          airbus.setNumberSeats(800);
          airbus.setMaxStewardessNumber(12);
          
          planeDAO.create(airbus);
          
          if(airbus.getId()!=null){
              //start with real test
               Flight ar124 = new Flight();
               ar124.setFlightIdentifier("AR124");
               ar124.setPlane(airbus);
               
               flightDAO.create(ar124);
               if(ar124.getId()==null){
                   fail("Creating flight with existing plane fail.");
               }else{
                   if(planeDAO.findAll().size()>1){
                       fail("Probable duplicity of planes in DB.");
                   }
                   System.out.println(" -- Planes: "+planeDAO.findAll().size());
               }
          }else{
              fail("Plane create fail.");
          }
    }
    

    /**
     * Test of get method, of class FlightDAOImpl.
     */
    @Test
    public void testGet() {
        
        Flight flight = new Flight();
        flight.setFlightIdentifier("testFlight");
        
        flightDAO.create(flight);
        assertEquals(flightDAO.get(flight.getId()), flight);
        
    }

    /**
     * Test of findByIdentifier method, of class FlightDAOImpl.
     */
    @Test
    public void testFindByIdentifier() {
        Flight flight = new Flight();
        flight.setFlightIdentifier("testFlightID");
        
        flightDAO.create(flight);
        List<Flight> expList= new ArrayList<Flight>();
        expList.add(flight);
        assertEquals(flightDAO.findByIdentifier(flight.getFlightIdentifier()), expList);
    }

    /**
     * Test of update method, of class FlightDAOImpl.
     */
    @Test
    public void testUpdate() {
        Flight flight = new Flight();
        flight.setFlightIdentifier("testFlightID2");
        flightDAO.create(flight);
        
        flight.setFlightIdentifier("updatedFlightID");
        flightDAO.update(flight);
        
        assertEquals(flightDAO.get(flight.getId()).getFlightIdentifier(), flight.getFlightIdentifier());
    }

    @Test
    public void testRemoveAll(){
        Flight fl1 = new Flight();
        fl1.setFlightIdentifier("TEST1");
        Flight fl2 = new Flight();
        fl2.setFlightIdentifier("TEST2");
        flightDAO.create(fl1);
        flightDAO.create(fl2);
          
        flightDAO.removeAll();
        
        if(flightDAO.findAll().size() > 0){
            fail("Failed to remove all flights from DB");
            System.out.println(flightDAO.findAll());
        }
    }
    
    /**
     * Test of remove method, of class FlightDAOImpl.
     */
    @Test
    public void testRemove() {
        flightDAO.removeAll();
        Flight fl1 = new Flight();
        fl1.setFlightIdentifier("TEST1");
        Flight fl2 = new Flight();
        fl2.setFlightIdentifier("TEST2");
        
        flightDAO.create(fl1);
        flightDAO.create(fl2);
        
        flightDAO.remove(fl2);
        if(flightDAO.findAll().contains(fl2)){
            fail("Failed removing flight 2");
        }
        
        flightDAO.remove(fl1);
        
        if(flightDAO.findAll().size() > 0){
            fail("Error removing flight 1");
        }
        
    }
    
    @Test
    public void testRemoveNonExisting() {
        Flight fl1 = new Flight();
        fl1.setFlightIdentifier("TEST1");
        Flight fl2 = new Flight();
        fl2.setFlightIdentifier("TEST2");
        
        flightDAO.create(fl1);
        
        try{
            flightDAO.remove(fl2);
            fail("Removing flight that doesnt exsits in DB");
        }catch(IllegalArgumentException ex){}
    }

    /**
     * Test of findAll method, of class FlightDAOImpl.
     */
    @Test
    public void testFindAll() {
        Flight fl1 = new Flight();
        fl1.setFlightIdentifier("TEST1");
        Flight fl2 = new Flight();
        fl2.setFlightIdentifier("TEST2");
        
        flightDAO.create(fl1);
        flightDAO.create(fl2);
       
        List<Flight> expList = new ArrayList<Flight>();
        expList.add(fl1);
        expList.add(fl2);
        
        assertEquals(flightDAO.findAll(),expList);
        
        flightDAO.remove(fl1);
        flightDAO.remove(fl2);
        System.out.println("--size: "+flightDAO.findAll().size());
        if(flightDAO.findAll().size()>0){
            fail("Failed getting all flights from empty DB, should return NULL");
        }
    }

    /**
     * Test of findFlightsByDepartureDestination method, of class FlightDAOImpl.
     */
    @Test
    public void testFindFlightsByDepartureDestination() {
        Flight fl1 = new Flight();
        Flight fl2 = new Flight();
        Flight fl3 = new Flight();
        
        Destination dest = new Destination();
        dest.setCountry("slovakia");
        Destination dest2 = new Destination();
        dest2.setCountry("czech rep.");
        Destination dest3 = new Destination();
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
        
        List<Flight> expList = new ArrayList<Flight>();
        expList.add(fl1);
        
        expList.add(fl3);
        
        destDAO.create(dest);
        destDAO.create(dest2);
        destDAO.create(dest3);
        
        flightDAO.create(fl1);
        flightDAO.create(fl2);
        flightDAO.create(fl3);
        
        assertEquals(flightDAO.findFlightsByDepartureDestination(dest2),expList);
        
    }
    
    

    /**
     * Test of findFlightsByArrivalDestination method, of class FlightDAOImpl.
     */
    @Test
    public void testFindFlightsByArrivalDestination() {
        Flight fl1 = new Flight();
        Flight fl2 = new Flight();
        Flight fl3 = new Flight();
        
        Destination dest = new Destination();
        dest.setCountry("slovakia");
        Destination dest2 = new Destination();
        dest2.setCountry("czech rep.");
        Destination dest3 = new Destination();
        dest3.setCountry("hungary");
        
        destDAO.create(dest);
        destDAO.create(dest2);
        destDAO.create(dest3);
        
        fl1.setDestinationStart(dest2);
        fl1.setDestinationArrival(dest);
        fl1.setFlightIdentifier("test1");
        
        fl2.setDestinationStart(dest);
        fl2.setDestinationArrival(dest3);
        fl2.setFlightIdentifier("test2");
        
        fl3.setDestinationStart(dest3);
        fl3.setDestinationArrival(dest);
        fl3.setFlightIdentifier("test3");
        
        List<Flight> expList = new ArrayList<Flight>();
        expList.add(fl1);
        
        expList.add(fl3);
        
       
        
        flightDAO.create(fl1);
        flightDAO.create(fl2);
        flightDAO.create(fl3);
        
        assertEquals(flightDAO.findFlightsByArrivalDestination(dest),expList);
    }

    /**
     * Test of findFlightsByPlane method, of class FlightDAOImpl.
     */
    @Test
    public void testFindFlightsByPlane() {
        Flight fl1 = new Flight();
        Flight fl2 = new Flight();
        Flight fl3 = new Flight();
        
        fl1.setFlightIdentifier("test1");
        fl2.setFlightIdentifier("test2");
        fl3.setFlightIdentifier("test3");
        
        Plane pl1 = new Plane();
        Plane pl2 = new Plane();
        Plane pl3 = new Plane();
        
        pl1.setType("A380");
        pl2.setType("A340");
        pl3.setType("A319");
        
        planeDAO.create(pl1);
        planeDAO.create(pl2);
        planeDAO.create(pl3);
        
        fl1.setPlane(pl1);
        fl2.setPlane(pl2);
        fl3.setPlane(pl1);
        
        List<Flight> expList = new ArrayList<Flight>();
        expList.add(fl1);
        expList.add(fl3);
        
       
        
        flightDAO.create(fl1);
        flightDAO.create(fl2);
        flightDAO.create(fl3);
        
        assertEquals(flightDAO.findFlightsByPlane(pl1),expList);
    }
}
