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

    private DestinationServiceImpl instance;

    public DestinationServiceImplTest() {
    }

    @Before
    public void setUp() {
        DestinationDAO destinationDAO = new DestinationDAOMock();
        instance = new DestinationServiceImpl();
        instance.setDestinationDAO(destinationDAO);
    }

    @Test
    public void testCreate() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
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
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        
        DestinationDTO newDTO2 = new DestinationDTO();
        newDTO2.setCountry("Francie");
        newDTO2.setCity("Paříž");
        
        instance.create(newDTO);
        instance.create(newDTO2);
        
        DestinationDTO fromDB = instance.get(Long.valueOf(0));
        DestinationDTO fromDB2 = instance.get(Long.valueOf(1));
        
        
        assertEquals(newDTO.getCity(), fromDB.getCity());
        assertEquals(newDTO.getCountry(), fromDB.getCountry());
        
        
        assertEquals(newDTO2.getCity(), fromDB2.getCity());
        assertEquals(newDTO2.getCountry(), fromDB2.getCountry());
        
        
        
        try{
            instance.get(null);
            fail("You can get destination with null id");
        }catch(DAOException ex){
        }
    }

    @Test
    public void testUpdate() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        
        instance.create(newDTO);
        
        
        newDTO.setCountry("Francie");
        newDTO.setCity("Paříž");
        
        
        instance.update(newDTO);
        
        DestinationDTO fromDB = instance.get(Long.valueOf(0));
        DestinationDTO fromDB2 = instance.get(Long.valueOf(1));
        
        assertEquals(newDTO.getCity(), fromDB.getCity());
        assertEquals(newDTO.getCountry(), fromDB.getCountry());
        
        try{
            instance.update(null);
            fail("You can update null destination");
        }catch(DAOException ex){
        }
    }

    @Test
    public void testRemove() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        
        instance.create(newDTO);
        instance.remove(newDTO);
        
        assertNull(instance.get(newDTO.getId()));
        
        try{
            instance.remove(null);
            fail("You can delete null destination");
        }catch(DAOException ex){
        }
        
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
