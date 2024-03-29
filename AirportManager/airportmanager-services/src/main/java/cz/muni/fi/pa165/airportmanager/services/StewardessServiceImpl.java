/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.EntityDTOMapper;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import cz.muni.fi.pa165.airportmanager.Stewardess;
import cz.muni.fi.pa165.airportmanager.StewardessDTO;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void create(StewardessDTO stewardessDTO) {
        if(stewardessDTO!=null){
            try
            {
                if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
                    System.out.println("SEC CX HOLDER: "+SecurityContextHolder.getContext().getAuthentication());
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    
                    if(!authorities.contains(new SimpleGrantedAuthority("USER"))){
                       //System.out.println("SEC CX isA");
                       throw new DAOException("Insufficient granted authorities.");
                    }
                }
                Stewardess stewardess = EntityDTOMapper.stewardessDTOToStewardess(stewardessDTO);
                sDAO.create(stewardess);
                stewardessDTO.setId(stewardess.getId());
            }
            catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Stewardess must not be null.");
        }
    }

    @Transactional
    public StewardessDTO get(Long id) {
        StewardessDTO result = null;
        if(id!=null){
            try
            {
                result = EntityDTOMapper.stewardessToStewardessDTO(sDAO.get(id));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
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
                if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
                    System.out.println("SEC CX HOLDER: "+SecurityContextHolder.getContext().getAuthentication());
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    
                    if(!authorities.contains(new SimpleGrantedAuthority("USER"))){
                       //System.out.println("SEC CX isA");
                       throw new DAOException("Insufficient granted authorities.");
                    }
                }
                sDAO.update(EntityDTOMapper.stewardessDTOToStewardess(stewardess));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Stewardess must not be null.");
        }
    }

    @Transactional
    public void remove(StewardessDTO stewardess) {
        if(stewardess!=null){
            try
            {
                if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
                    System.out.println("SEC CX HOLDER: "+SecurityContextHolder.getContext().getAuthentication());
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    
                    if(!authorities.contains(new SimpleGrantedAuthority("USER"))){
                       //System.out.println("SEC CX isA");
                       throw new DAOException("Insufficient granted authorities.");
                    }
                }
                sDAO.remove(EntityDTOMapper.stewardessDTOToStewardess(stewardess));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Stewardess must not be null.");
        }
    }

    @Transactional
    public List<StewardessDTO> findAll() {
        List<StewardessDTO> result = null;
        
        try
        {
            result = EntityDTOMapper.stewardessListToStewardessDTOList(sDAO.findAll());
        }catch(DataAccessException ex)
        {
            throw new DAOException(ex.toString());
        }
        
        return result;
    }
    
}
