/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import cz.muni.fi.pa165.airportmanager.mock.DestinationDAOMock;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public class DestinationServiceImplTest {

    private DestinationDAO destinationDAO;
    private DestinationServiceImpl instance;

    public DestinationServiceImplTest() {
    }

    @Before
    public void setUp() {
        destinationDAO = new DestinationDAOMock();
        instance = new DestinationServiceImpl();
        instance.setDestinationDAO(destinationDAO);
    }

    @Test
    public void testCreate() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Neco");
        newDTO.setCity("Neoc2");
        instance.create(newDTO);
        DestinationDTO fromDB = instance.get(Long.valueOf(0));
     //TODO
     //   assertNotNull(newDTO.getId());                
        
     //   assertEquals(newDTO.getId(), fromDB.getId());
        assertEquals(newDTO.getCity(), fromDB.getCity());
        assertEquals(newDTO.getCountry(), fromDB.getCountry());
        
        
        
        try{
            instance.create(null);
            fail("You can create null destination");
        }catch(DAOException ex){
        }
        
    }

    @Test
    public void testGet() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemove() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindAll() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByCountry() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByCity() {
        fail("The test case is a prototype.");
    }
}
