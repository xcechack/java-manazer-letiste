/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Vasa
 */
public class PlaneDAOImplTest {
    
    private PlaneDAO pDAO;
    
    //which exception is being thrown?
    @Before
    public void setUp()throws SQLException
    {
        pDAO = new PlaneDAOImpl();
        pDAO.setEntityManagerFactory(Persistence.createEntityManagerFactory("AirportTestPU"));
    }
    
    @Test
    public void testCreate()
    {
        Plane plane = newPlane("Boeing","A800", 650, 10);
        pDAO.create(plane);
        
        Long planeId = plane.getId();
        assertNotNull(planeId);
        
        Plane result = pDAO.get(planeId);
        assertEquals(plane, result);
        assertNotSame(plane, result);
        assertDeepEquals(plane, result);
    }
    
    @Test
    public void testGet()
    {
        Plane plane = newPlane("Boeing","A800", 650, 10);
        pDAO.create(plane);
        
        Long planeId = plane.getId();
        
        Plane getPlane = pDAO.get(planeId);
        
        assertEquals(getPlane.getId(), plane.getId());
        assertNotSame(getPlane, plane);
        assertDeepEquals(plane, getPlane);
    }
    
    @Test
    public void testUpdate()
    {
        Plane plane = newPlane("Boeing","A800", 650, 10);
        pDAO.create(plane);
        plane.setProducer("Airbus");
        pDAO.update(plane);
        
        Plane getPlane = pDAO.get(plane.getId());
        
        assertEquals(plane.getId(), getPlane.getId());
        assertEquals(plane.getProducer(),"Airbus");
    }
    
    @Test
    public void testRemove()
    {
        for(Plane p : listOfNewPlanes())
        {
            pDAO.create(p);
        }
        Plane plane = newPlane("Bombardier","CS300", 140, 3);
        
        pDAO.create(plane);
        
        pDAO.remove(plane);
        
        assertFalse(pDAO.findAll().contains(plane));
        assertNull(pDAO.get(plane.getId()));
        assertEquals(pDAO.findAll().size(), listOfNewPlanes().size());
    }
   
    @Test
    public void findByProducer() 
    {
        Plane plane = newPlane("Boeing","A800", 650, 10);
        pDAO.create(plane);
        
        List<Plane> list = pDAO.findByProducer("Boeing");
        
        assertEquals(plane, list.get(0));
        assertNotSame(plane, list.get(0));
        assertDeepEquals(plane, list.get(0));
    }
    
    @Test
    public void findPlaneWithGreaterNumberOfSeats()
    {
        Plane plane = newPlane("Boeing","A800", 650, 10);
        pDAO.create(plane);
        
        List<Plane> list = pDAO.findPlaneWithGreaterNumberOfSeats(640);
        
        assertNotNull(list.get(0));
        assertEquals(plane, list.get(0));
        assertNotSame(plane, list.get(0));
        assertDeepEquals(plane, list.get(0));
    }
    
    private static Plane newPlane(String producer, String type, int numberSeats, int maxStewardessNumber)
    {
        Plane plane = new Plane();
        plane.setProducer(producer);
        plane.setType(type);
        plane.setNumberSeats(numberSeats);
        plane.setMaxStewardessNumber(maxStewardessNumber);
        
        return plane;
    }
    
    private static List<Plane> listOfNewPlanes()
    {
        List<Plane> result = new ArrayList<Plane>();
        result.add(newPlane("Boeing","747", 250, 5));
        result.add(newPlane("Airbus","A380", 150, 4));
        result.add(newPlane("Bombardier","CS300", 140, 3));
        result.add(newPlane("Cessna","172 Skyhawk", 3, 0));
        result.add(newPlane("LearJet","Learjet 45", 25, 1));
        result.add(newPlane("Hawker","Hurricane", 1, 0));
        return result;
    }
    
    private void assertDeepEquals(Plane expected, Plane actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProducer(), actual.getProducer());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getMaxStewardessNumber(), actual.getMaxStewardessNumber());
        assertEquals(expected.getNumberSeats(), actual.getNumberSeats());
    }
}
