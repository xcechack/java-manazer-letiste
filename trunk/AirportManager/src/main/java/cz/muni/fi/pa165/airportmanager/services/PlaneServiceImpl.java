/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.Plane;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marek
 */
@Service
public class PlaneServiceImpl implements PlaneService {
    
    @Autowired
    private PlaneDAO pDao;
    
    public void setpDao(PlaneDAO pDao){
        this.pDao = pDao;
    }
    
    @Transactional    
    public void create(Plane plane) {
        if(plane!=null){
            pDao.create(plane);
        }
    }
    
    @Transactional
    public Plane get(Long id) {
        if(id!=null){
            return pDao.get(id);
        }else{
            throw new IllegalArgumentException("Given argument was null.");
       }
    }
    
    @Transactional
    public void update(Plane plane) {
        if(plane!=null){
            pDao.update(plane);
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
    }
    
    @Transactional
    public void remove(Plane plane) {
        if(plane!=null){
            pDao.remove(plane);
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
    }
    
    @Transactional
    public List<Plane> findAll() {
        return pDao.findAll();
    }
    
    @Transactional
    public List<Plane> findByProducer(String producer) {
        if(producer!=null){
            return pDao.findByProducer(producer);
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
    }
    
    @Transactional
    public List<Plane> findByType(String type) {
        if(type!=null){
            return pDao.findByType(type);
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
    }
    
    @Transactional
    public List<Plane> findByMaxNumberOfSeats(int number) {
        return pDao.findByMaxNumberOfSeats(number);
    }
    
    @Transactional
    public List<Plane> findPlaneWithGreaterNumberOfSeats(int number) {
        return pDao.findPlaneWithGreaterNumberOfSeats(number);
    }
    
}
