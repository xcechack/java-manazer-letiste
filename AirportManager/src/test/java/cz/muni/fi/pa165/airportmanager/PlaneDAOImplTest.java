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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vasa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class PlaneDAOImplTest {

    @Autowired
    private PlaneDAO pDAO;

    //which exception is being thrown?
    @Before
    public void setUp() throws SQLException {
    }

    @Test
    @Transactional
    public void testCreate() {
        Plane plane = newPlane("Boeing", "A800", 650, 10);
        pDAO.create(plane);

        Long planeId = plane.getId();
        assertNotNull(planeId);

        Plane result = pDAO.get(planeId);
        assertEquals(plane, result);
        assertDeepEquals(plane, result);

        try {
            pDAO.create(null);
            fail("created null plane");
        } catch (DataAccessException ex) {
        }
    }

    @Test
    @Transactional
    public void testGet() {
        Plane plane1 = newPlane("Boeing", "A800", 650, 10);
        Plane plane2 = newPlane("Boeing", "A800", 650, 10);
        pDAO.create(plane1);
        pDAO.create(plane2);

        Plane getPlane = pDAO.get(plane1.getId());

        assertEquals(getPlane.getId(), plane1.getId());
        if (getPlane.getId().equals(plane2.getId())) {
            fail("id shouldn't be same");
        }
        assertNotSame(plane2, getPlane);
        assertDeepEquals(plane1, getPlane);

        try {
            pDAO.get(null);
            fail("can't get plane with null id");
        } catch (DataAccessException ex) {
        }
    }

    @Test
    @Transactional
    public void testUpdate() {
        Plane plane1 = newPlane("Boeing", "A800", 650, 10);
        Plane plane2 = newPlane("Boeing", "A800", 650, 10);
        pDAO.create(plane1);
        pDAO.create(plane2);
        plane1.setProducer("Airbus");
        plane1.setType("A500");
        plane1.setMaxStewardessNumber(6);
        plane1.setNumberSeats(200);
        pDAO.update(plane1);

        Plane getPlane = pDAO.get(plane1.getId());

        assertDeepEquals(plane1, getPlane);
        assertDeepEquals(plane2, pDAO.get(plane2.getId()));

        try {
            pDAO.update(null);
            fail("can't update null");
        } catch (DataAccessException ex) {
        }
    }

    @Test
    @Transactional
    public void testRemove() {
        Plane plane1 = newPlane("Boeing", "A800", 650, 10);
        Plane plane2 = newPlane("Boeing", "A800", 650, 10);

        pDAO.create(plane1);
        pDAO.create(plane2);

        pDAO.remove(plane1);

        assertNotNull(pDAO.get(plane2.getId()));
        assertNull(pDAO.get(plane1.getId()));

        try {
            pDAO.remove(null);
            fail("removing null");
        } catch (DataAccessException ex) {
        }
    }

    @Test
    @Transactional
    public void testFindAll() {
        Plane plane1 = newPlane("Boeing", "A800", 650, 10);
        Plane plane2 = newPlane("Boeing", "A800", 650, 10);
        Plane plane3 = newPlane("Boeing", "A800", 650, 10);
        pDAO.create(plane1);
        pDAO.create(plane2);

        List<Plane> planes = pDAO.findAll();

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
    @Transactional
    public void testFindByType() {
        Plane plane1 = newPlane("Boeing", "A800", 650, 10);
        Plane plane2 = newPlane("Boeing", "XRJ-800", 650, 10);

        pDAO.create(plane1);
        pDAO.create(plane2);

        List<Plane> planes = pDAO.findByType("A800");
        if (planes.size() != 1) {
            fail("wrong number of planes");
        }

        assertEquals(plane1.getId(), planes.get(0).getId());
        assertDeepEquals(plane1, planes.get(0));
        try {
            pDAO.findByType(null);
            fail("find by type with null");
        } catch (DataAccessException ex) {
        }
    }

    @Test
    @Transactional
    public void testFindByProducer() {
        Plane plane1 = newPlane("Boeing", "A800", 650, 10);
        Plane plane2 = newPlane("Airbus", "A800", 650, 10);
        pDAO.create(plane1);
        pDAO.create(plane2);

        List<Plane> list = pDAO.findByProducer("Boeing");

        if (list.size() != 1) {
            fail("wrong number of planes");
        }
        assertEquals(plane1.getId(), list.get(0).getId());
        assertDeepEquals(plane1, list.get(0));
        try {
            pDAO.findByProducer(null);
            fail("find by producer with null");
        } catch (DataAccessException ex) {
        }
    }

    @Test
    @Transactional
    public void findByMaxNumberOfSeats() {
        Plane plane1 = newPlane("Boeing", "A800", 649, 10);
        Plane plane2 = newPlane("Boeing", "A800", 650, 10);
        Plane plane3 = newPlane("Boeing", "A800", 651, 10);
        pDAO.create(plane1);
        pDAO.create(plane2);
        pDAO.create(plane3);

        List<Plane> planes = pDAO.findByMaxNumberOfSeats(650);

        if (planes.size() != 2) {
            fail("wrong number of planes");
        }

        if (planes.get(0).getId().equals(plane1.getId())) {
            assertEquals(planes.get(0).getId(), plane1.getId());
            assertEquals(planes.get(1).getId(), plane2.getId());
        } else {
            assertEquals(planes.get(0).getId(), plane2.getId());
            assertEquals(planes.get(1).getId(), plane1.getId());
        }
    }

    @Test
    @Transactional
    public void findPlaneWithGreaterNumberOfSeats() {
        Plane plane1 = newPlane("Boeing", "A800", 649, 10);
        Plane plane2 = newPlane("Boeing", "A800", 650, 10);
        Plane plane3 = newPlane("Boeing", "A800", 651, 10);
        pDAO.create(plane1);
        pDAO.create(plane2);
        pDAO.create(plane3);

        List<Plane> planes = pDAO.findPlaneWithGreaterNumberOfSeats(650);

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

    private static Plane newPlane(String producer, String type, int numberSeats, int maxStewardessNumber) {
        Plane plane = new Plane();
        plane.setProducer(producer);
        plane.setType(type);
        plane.setNumberSeats(numberSeats);
        plane.setMaxStewardessNumber(maxStewardessNumber);

        return plane;
    }

    private void assertDeepEquals(Plane expected, Plane actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProducer(), actual.getProducer());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getMaxStewardessNumber(), actual.getMaxStewardessNumber());
        assertEquals(expected.getNumberSeats(), actual.getNumberSeats());
    }
}
