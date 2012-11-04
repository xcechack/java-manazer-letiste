/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.EntityDTOMapper;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import cz.muni.fi.pa165.airportmanager.Stewardess;
import cz.muni.fi.pa165.airportmanager.StewardessDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marek
 */
@Service
public class StewardessServiceImpl implements StewardessService{

    @Autowired
    private StewardessDAO sDAO;

    public void setsDAO(StewardessDAO sDAO) {
        this.sDAO = sDAO;
    }
    
    @Transactional
    public void create(StewardessDTO stewardess) {
        if(stewardess!=null){
            try
            {
                sDAO.create(EntityDTOMapper.stewardessDTOToStewardess(stewardess));
            }
            catch(NullPointerException ex){}
        }else{
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
    }

    @Transactional
    public StewardessDTO get(Long id) {
        StewardessDTO result = null;
        if(id!=null){
            try
            {
                result = EntityDTOMapper.stewardessToStewardessDTO(sDAO.get(id));
            }catch(NullPointerException ex){}
        }else{
            throw new NullPointerException("Id must not be null.");
        }
        return result;
    }

    @Transactional
    public void update(StewardessDTO stewardess) {
        if(stewardess!=null){
            try
            {
                sDAO.update(EntityDTOMapper.stewardessDTOToStewardess(stewardess));
            }catch(NullPointerException ex){}
        }else{
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
    }

    @Transactional
    public void remove(StewardessDTO stewardess) {
        if(stewardess!=null){
            try
            {
                sDAO.remove(EntityDTOMapper.stewardessDTOToStewardess(stewardess));
            }catch(NullPointerException ex){}
        }else{
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
    }

    @Transactional
    public List<StewardessDTO> findAll() {
        List<StewardessDTO> result = null;
        
        try
        {
            result = EntityDTOMapper.stewardessListToStewardessDTOList(sDAO.findAll());
        }catch(NullPointerException ex){}
        
        return result;
    }
    
}
