/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.FlightDAO;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.Plane;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marek
 */
@Service
public class FlightServiceImpl implements FlightService {
   
    @Autowired
    private FlightDAO fDao;
    @Autowired
    private DestinationDAO dDao;
    @Autowired
    private PlaneDAO pDao;
    @Autowired
    private StewardessDAO sDao;
    
    public void setfDao(FlightDAO fDao) {
        this.fDao = fDao;
    }
    
    public void setdDao(DestinationDAO dDao) {
        this.dDao = dDao;
    }
    
    public void setpDao(PlaneDAO pDao) {
        this.pDao = pDao;
    }
    
    public void setsDao(StewardessDAO sDao) {
        this.sDao = sDao;
    }
    
    @Transactional
    public void create(Flight flight) {
        if(flight!=null){
           
                if(flight.getStewardess() != null){
                    for(int i=0; i < flight.getStewardess().size(); i++){
                        if(flight.getStewardess().get(i) != null && flight.getStewardess().get(i).getId()==null){
                            sDao.create(flight.getStewardess().get(i));
                        }
                    }
                }
                
                if(flight.getPlane() != null && flight.getPlane().getId()==null){
                     pDao.create(flight.getPlane());
                }
                if(flight.getDestinationStart() != null && flight.getDestinationStart().getId()==null){
                     dDao.create(flight.getDestinationStart());
                }
                if(flight.getDestinationArrival() != null && flight.getDestinationArrival().getId()==null){
                     dDao.create(flight.getDestinationArrival());
                }
               
                fDao.create(flight);
            
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public Flight get(Long id) {
        if(id!=null){
            return fDao.get(id);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public List<Flight> findByIdentifier(String identifier) {
        if(identifier!=null){
            return fDao.findByIdentifier(identifier);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public void update(Flight flight) {
        if(flight!=null){
            fDao.update(flight);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public void remove(Flight flight) {
        if(flight!=null){
            fDao.remove(flight);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public void removeAll() {
        fDao.removeAll();
    }
    @Transactional
    public List<Flight> findAll() {
        return fDao.findAll();
    }
    @Transactional
    public List<Flight> findFlightsByDepartureDestination(Destination destination) {
        if(destination!=null){
            return fDao.findFlightsByDepartureDestination(destination);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public List<Flight> findFlightsByArrivalDestination(Destination destination) {
        if(destination!=null){
            return fDao.findFlightsByArrivalDestination(destination);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    @Transactional
    public List<Flight> findFlightsByPlane(Plane plane) {
        if(plane!=null){
            return fDao.findFlightsByPlane(plane);
        }else{
            throw new IllegalArgumentException("Given flight was null.");
        }
    }
    
}
