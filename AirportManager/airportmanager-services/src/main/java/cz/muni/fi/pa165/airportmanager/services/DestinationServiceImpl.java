/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.EntityDTOMapper;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Vasa
 */
@Service
public class DestinationServiceImpl implements DestinationService {

    
    @Autowired
    private DestinationDAO destinationDAO;
     
       
    public void setDestinationDAO(DestinationDAO destinationDAO) 
    {
        this.destinationDAO = destinationDAO;
    }
       
    @Transactional
    public void create(DestinationDTO destinationDTO) {
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
            Destination destination= EntityDTOMapper.destinationDTOToDestination(destinationDTO);
            destinationDAO.create(destination);
            destinationDTO.setId(destination.getId());
        }catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
    }
    
    @Transactional
    public DestinationDTO get(Long id) {
        if(id== null){
            throw new NullPointerException("Id destination is null ");
        }
        DestinationDTO result = null;
        try{
            result = EntityDTOMapper.destinationToDestinationDTO(destinationDAO.get(id));
        }catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
        return result;
    }
    
    @Transactional
    public void update(DestinationDTO destinationDTO) {
        if(destinationDTO!=null){
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
                destinationDAO.update(EntityDTOMapper.destinationDTOToDestination(destinationDTO));
            }
            catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given argument was null.");
        }
    }
    
    @Transactional
    public void remove(DestinationDTO destinationDTO) {
        if(destinationDTO!=null){
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
                destinationDAO.remove(EntityDTOMapper.destinationDTOToDestination(destinationDTO));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
            }else{
                throw new NullPointerException("Given argument was null.");
        }
    }
    
    @Transactional
    public List<DestinationDTO> findAll() {
        List<DestinationDTO> result = null;
        try
        {
            result = EntityDTOMapper.destinationListToDestinationDTOList(destinationDAO.findAll());
        }
        catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
        return result;
    }
    
    @Transactional
    public List<DestinationDTO> findByCountry(String country) {
        if(country == null){
            throw new NullPointerException("Country is null ");
        }
        List<DestinationDTO> result = null;
        try
        {
            result = EntityDTOMapper.destinationListToDestinationDTOList(destinationDAO.findByCountry(country));
        }
        catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
        return result;
    }
    
    @Transactional
    public List<DestinationDTO> findByCity(String city) {
        if(city == null){
            throw new NullPointerException("City is null ");
        }
        List<DestinationDTO> result = null;
        try
        {
            result = EntityDTOMapper.destinationListToDestinationDTOList(destinationDAO.findByCity(city));
        }
        catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
        return result;
    }
}
