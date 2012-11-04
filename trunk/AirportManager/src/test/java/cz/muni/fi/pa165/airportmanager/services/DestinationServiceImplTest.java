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



        try {
            instance.create(null);
            fail("You can create null destination");
        } catch (DAOException ex) {
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



        try {
            instance.get(null);
            fail("You can get destination with null id");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testUpdate() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");

        instance.create(newDTO);

        newDTO.setId(Long.valueOf(0));
        newDTO.setCountry("Francie");
        newDTO.setCity("Paříž");


        instance.update(newDTO);

        DestinationDTO fromDB = instance.get(Long.valueOf(0));


        assertEquals(newDTO.getCity(), fromDB.getCity());
        assertEquals(newDTO.getCountry(), fromDB.getCountry());

        try {
            instance.update(null);
            fail("You can update null destination");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testRemove() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        newDTO.setId(Long.valueOf(0));
        instance.create(newDTO);
        instance.remove(newDTO);

        assertNull(instance.get(newDTO.getId()));

        try {
            instance.remove(null);
            fail("You can delete null destination");
        } catch (IllegalArgumentException ex) {
        }

    }

    @Test
    public void testFindAll() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        newDTO.setId(Long.valueOf(0));

        DestinationDTO newDTO2 = new DestinationDTO();
        newDTO2.setCountry("Francie");
        newDTO2.setCity("Paříž");
        newDTO2.setId(Long.valueOf(1));

        instance.create(newDTO);
        instance.create(newDTO2);

        List<DestinationDTO> destList = instance.findAll();

        assertTrue(destList.contains(newDTO));
        assertTrue(destList.contains(newDTO2));
        assertEquals(2, destList.size());
    }

    @Test
    public void testFindByCountry() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        newDTO.setId(Long.valueOf(0));
        
        DestinationDTO newDTO2 = new DestinationDTO();
        newDTO2.setCountry("Francie");
        newDTO2.setCity("Paříž");
        newDTO2.setId(Long.valueOf(1));
        
        DestinationDTO newDTO3 = new DestinationDTO();
        newDTO3.setCountry("Francie");
        newDTO3.setCity("Lyon");
        newDTO3.setId(Long.valueOf(2));
        
        instance.create(newDTO);
        instance.create(newDTO2);
        instance.create(newDTO3);
        
        List<DestinationDTO> destList = instance.findByCountry("Francie");;        
        
        assertFalse(destList.contains(newDTO));
        assertTrue(destList.contains(newDTO2));
        assertTrue(destList.contains(newDTO3));
        assertEquals(2, destList.size());
        
        try{
            instance.findByCountry(null);
            fail("You can find by country when country is null");
        }catch(NullPointerException ex){
        }
    }

    @Test
    public void testFindByCity() {
        DestinationDTO newDTO = new DestinationDTO();
        newDTO.setCountry("Czech Republic");
        newDTO.setCity("Praha");
        newDTO.setId(Long.valueOf(0));
        
        DestinationDTO newDTO2 = new DestinationDTO();
        newDTO2.setCountry("Francie");
        newDTO2.setCity("Paříž");
        newDTO2.setId(Long.valueOf(1));
        
        DestinationDTO newDTO3 = new DestinationDTO();
        newDTO3.setCountry("USA");
        newDTO3.setCity("Paříž");
        newDTO3.setId(Long.valueOf(2));
        
        instance.create(newDTO);
        instance.create(newDTO2);
        instance.create(newDTO3);
        
        List<DestinationDTO> destList = instance.findByCity("Paříž");       
        
        assertFalse(destList.contains(newDTO));
        assertTrue(destList.contains(newDTO2));
        assertTrue(destList.contains(newDTO3));
        assertEquals(2, destList.size());
        
        try{
            instance.findByCity(null);
            fail("You can find by city when country is null");
        }catch(NullPointerException ex){
        }
    }
}
