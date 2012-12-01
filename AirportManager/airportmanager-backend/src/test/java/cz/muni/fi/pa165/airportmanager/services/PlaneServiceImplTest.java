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
        PlaneDTO plane1 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane2 = newPlane("Boeing", "A800", 650, 10);
        instance.create(plane1);
        instance.create(plane2);

        PlaneDTO getPlane = instance.get(plane1.getId());

        assertEquals(getPlane.getId(), plane1.getId());
        if (getPlane.getId().equals(plane2.getId())) {
            fail("id shouldn't be same");
        }
        assertNotSame(plane2, getPlane);
        assertDeepEquals(plane1, getPlane);

        try {
            instance.get(null);
            fail("can't get plane with null id");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testUpdate() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane2 = newPlane("Boeing", "A800", 650, 10);
        instance.create(plane1);
        instance.create(plane2);
        plane1.setProducer("Airbus");
        plane1.setType("A500");
        plane1.setMaxStewardessNumber(6);
        plane1.setNumberSeats(200);
        instance.update(plane1);

        PlaneDTO getPlane = instance.get(plane1.getId());

        assertDeepEquals(plane1, getPlane);
        assertDeepEquals(plane2, instance.get(plane2.getId()));

        try {
            instance.update(null);
            fail("can't update null");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testRemove() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane2 = newPlane("Boeing", "A800", 650, 10);

        instance.create(plane1);
        instance.create(plane2);

        instance.remove(plane1);

        assertNotNull(instance.get(plane2.getId()));
        assertNull(instance.get(plane1.getId()));

        try {
            instance.remove(null);
            fail("removing null");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testFindAll() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane2 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane3 = newPlane("Boeing", "A800", 650, 10);
        instance.create(plane1);
        instance.create(plane2);

        List<PlaneDTO> planes = instance.findAll();

        if (planes.contains(plane3)) {
            fail("can't contain plane3");
        }
        if (planes.size() != 2) {
            fail("wrong number of planes");
        }
        if (planes.get(0).getId().equals(plane1.getId())) {
            assertDeepEquals(planes.get(0), plane1);
            assertDeepEquals(planes.get(1), plane2);
        } else {
            assertDeepEquals(planes.get(0), plane2);
            assertDeepEquals(planes.get(1), plane1);
        }
    }

    @Test
    public void testFindByProducer() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane2 = newPlane("Boeing", "XRJ-800", 650, 10);

        instance.create(plane1);
        instance.create(plane2);

        List<PlaneDTO> planes = instance.findByType("A800");
        if (planes.size() != 1) {
            fail("wrong number of planes");
        }

        assertEquals(plane1.getId(), planes.get(0).getId());
        assertDeepEquals(plane1, planes.get(0));
        try {
            instance.findByType(null);
            fail("find by type with null");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testFindByType() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane2 = newPlane("Airbus", "A800", 650, 10);
        instance.create(plane1);
        instance.create(plane2);

        List<PlaneDTO> list = instance.findByProducer("Boeing");

        if (list.size() != 1) {
            fail("wrong number of planes");
        }
        assertEquals(plane1.getId(), list.get(0).getId());
        assertDeepEquals(plane1, list.get(0));
        try {
            instance.findByProducer(null);
            fail("find by producer with null");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testFindByMaxNumberOfSeats() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 649, 10);
        PlaneDTO plane2 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane3 = newPlane("Boeing", "A800", 651, 10);
        instance.create(plane1);
        instance.create(plane2);
        instance.create(plane3);

        List<PlaneDTO> planes = instance.findByMaxNumberOfSeats(650);
        
        if (planes.size() != 1) {
            fail("wrong number of planes");
        }

//        if (planes.get(0).getId().equals(plane1.getId())) {
//            assertEquals(planes.get(0).getId(), plane1.getId());
//            assertEquals(planes.get(1).getId(), plane2.getId());
//        } else {
//            assertEquals(planes.get(0).getId(), plane2.getId());
//            assertEquals(planes.get(1).getId(), plane1.getId());
//        }
    }

    @Test
    public void testFindPlaneWithGreaterNumberOfSeats() {
        PlaneDTO plane1 = newPlane("Boeing", "A800", 649, 10);
        PlaneDTO plane2 = newPlane("Boeing", "A800", 650, 10);
        PlaneDTO plane3 = newPlane("Boeing", "A800", 651, 10);
        instance.create(plane1);
        instance.create(plane2);
        instance.create(plane3);

        List<PlaneDTO> planes = instance.findPlaneWithGreaterNumberOfSeats(650);

        if (planes.size() != 2) {
            fail("wrong number of planes");
        }

        if (planes.get(0).getId().equals(plane2.getId())) {
            assertEquals(planes.get(0).getId(), plane2.getId());
            assertEquals(planes.get(1).getId(), plane3.getId());
        } else {
            assertEquals(planes.get(0).getId(), plane3.getId());
            assertEquals(planes.get(1).getId(), plane2.getId());
        }
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
