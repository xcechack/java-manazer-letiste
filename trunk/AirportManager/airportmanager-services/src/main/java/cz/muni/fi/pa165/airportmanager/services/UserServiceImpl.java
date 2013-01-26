/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.EntityDTOMapper;
import cz.muni.fi.pa165.airportmanager.User;
import cz.muni.fi.pa165.airportmanager.UserDAO;
import cz.muni.fi.pa165.airportmanager.UserDTO;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marek
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO uDao;
    
    public void setuDao(UserDAO uDao){
        this.uDao = uDao;
    }
    
    @Transactional
    public void create(UserDTO userDTO) {
        if(userDTO!=null){
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
                
                User user = EntityDTOMapper.userDTOToUser(userDTO);
                uDao.create(user);
                userDTO.setId(user.getId());
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given plane was null");
        }
    }
    
    @Transactional
    public void update(UserDTO userDTO){
        if(userDTO!=null){
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
                uDao.update(EntityDTOMapper.userDTOToUser(userDTO));
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
    public void delete(UserDTO userDTO) {
        if(userDTO!=null){
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
                uDao.delete(EntityDTOMapper.userDTOToUser(userDTO));
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
    public UserDTO get(Long id) {
        UserDTO result = null;
        if(id!=null){
            try
            {
                result = EntityDTOMapper.userToUserDTO(uDao.get(id));
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
    public List<UserDTO> findAll() {
        List<UserDTO> result = null;
        
        try
        {
            result = EntityDTOMapper.userListToUserDTOList(uDao.findAll());
        }catch(DataAccessException ex)
        {
            throw new DAOException(ex.toString());
        }
        
        return result;
    }
    
}
