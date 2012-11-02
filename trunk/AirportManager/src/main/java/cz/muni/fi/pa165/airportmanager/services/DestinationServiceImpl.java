/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.Destination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Marek
 */
@Service
public class DestinationServiceImpl implements DestinationService {

    
    @Autowired
    private DestinationDAO destinationDAO;
  
    
    public void setDestinationDAO(DestinationDAO destinationDAO) {
        this.destinationDAO = destinationDAO;
    }
    
    @Transactional
    public void create(Destination destination) {
        destinationDAO.create(destination);
    }
    @Transactional
    public Destination get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Transactional
    public void update(Destination destination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Transactional
    public void remove(Destination destination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Transactional
    public List<Destination> findAll() {
        return destinationDAO.findAll();
    }
    @Transactional
    public List<Destination> findByCountry(String country) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Transactional
    public List<Destination> findByCity(String city) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
