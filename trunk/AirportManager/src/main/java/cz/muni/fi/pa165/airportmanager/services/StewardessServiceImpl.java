/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import cz.muni.fi.pa165.airportmanager.Stewardess;
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
    public void create(Stewardess stewardess) {
        if(stewardess!=null){
            sDAO.create(stewardess);
        }else{
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
    }

    @Transactional
    public Stewardess get(Long id) {
        if(id!=null){
            return sDAO.get(id);
        }else{
            throw new NullPointerException("Id must not be null.");
        }
    }

    @Transactional
    public void update(Stewardess stewardess) {
        if(stewardess!=null){
            sDAO.update(stewardess);
        }else{
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
    }

    @Transactional
    public void remove(Stewardess stewardess) {
        if(stewardess!=null){
            sDAO.remove(stewardess);
        }else{
            throw new IllegalArgumentException("Stewardess must not be null.");
        }
    }

    @Transactional
    public List<Stewardess> findAll() {
        return sDAO.findAll();
    }
    
}
