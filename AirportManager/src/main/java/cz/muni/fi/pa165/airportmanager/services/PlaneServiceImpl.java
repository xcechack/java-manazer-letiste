/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.EntityDTOMapper;
import cz.muni.fi.pa165.airportmanager.Plane;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import cz.muni.fi.pa165.airportmanager.PlaneDTO;
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
    public void create(PlaneDTO plane) {
        if(plane!=null){
            try
            {
                pDao.create(EntityDTOMapper.planeDTOToPlane(plane));
            }catch(IllegalArgumentException ex){}
        }
    }
    
    @Transactional
    public PlaneDTO get(Long id) {
        PlaneDTO result = null;
        if(id!=null){
            try
            {
                result = EntityDTOMapper.planeToPlaneDTO(pDao.get(id));
            }catch(IllegalArgumentException ex){}
        }else{
            throw new IllegalArgumentException("Given argument was null.");
       }
        return result;
    }
    
    @Transactional
    public void update(PlaneDTO plane) {
        if(plane!=null){
            try
            {
                pDao.update(EntityDTOMapper.planeDTOToPlane(plane));
            }catch(IllegalArgumentException ex){}
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
    }
    
    @Transactional
    public void remove(PlaneDTO plane) {
        if(plane!=null){
            try{
            pDao.remove(EntityDTOMapper.planeDTOToPlane(plane));
            }catch(IllegalArgumentException ex){}
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
    }
    
    @Transactional
    public List<PlaneDTO> findAll() {
        List<PlaneDTO> result = null;
        try
        {
            result = EntityDTOMapper.planeListToPlaneDTOList(pDao.findAll());
        }catch(Exception ex){}
        return result;
    }
    
    @Transactional
    public List<PlaneDTO> findByProducer(String producer) {
        List<PlaneDTO> result = null;
        if(producer!=null){
            try
            {
                result = EntityDTOMapper.planeListToPlaneDTOList(pDao.findByProducer(producer));
            }catch(IllegalArgumentException ex){}
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
        return result;
    }
    
    @Transactional
    public List<PlaneDTO> findByType(String type) {
        List<PlaneDTO> result = null;
        if(type!=null){
            try
            {
                result = EntityDTOMapper.planeListToPlaneDTOList(pDao.findByType(type));
            }catch(IllegalArgumentException ex){}
        }else{
            throw new IllegalArgumentException("Given argument was null.");
        }
        return result;
    }
    
    @Transactional
    public List<PlaneDTO> findByMaxNumberOfSeats(int number) {
        List<PlaneDTO> result = null;
        try{
            result = EntityDTOMapper.planeListToPlaneDTOList(pDao.findByMaxNumberOfSeats(number));
        }catch(IllegalArgumentException ex){}
        return result;
    }
    
    @Transactional
    public List<PlaneDTO> findPlaneWithGreaterNumberOfSeats(int number) {
        List<PlaneDTO> result = null;
        try{
            result = EntityDTOMapper.planeListToPlaneDTOList(pDao.findPlaneWithGreaterNumberOfSeats(number));
        }catch(IllegalArgumentException ex){}
        return result;
    }
    
}
