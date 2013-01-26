/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.services;

import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import cz.muni.fi.pa165.airportmanager.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.EntityDTOMapper;
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.FlightDAO;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import cz.muni.fi.pa165.airportmanager.PlaneDTO;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
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
    public void create(FlightDTO fDto) {
        if(fDto!=null){
            if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
                    System.out.println("SEC CX HOLDER: "+SecurityContextHolder.getContext().getAuthentication());
                    List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                    
                    if(!authorities.contains(new SimpleGrantedAuthority("USER"))){
                       //System.out.println("SEC CX isA");
                       throw new DAOException("Insufficient granted authorities.");
                    }
                }
                Flight flight = EntityDTOMapper.flightDtoToFlight(fDto);
                if(flight.getStewardess() != null){
                    for(int i=0; i < flight.getStewardess().size(); i++){
                        if(flight.getStewardess().get(i) != null && flight.getStewardess().get(i).getId()==null){
                            try
                            {
                                sDao.create(flight.getStewardess().get(i));
                                fDto.getStewardess().get(i).setId(flight.getStewardess().get(i).getId());
                            }catch(DataAccessException ex)
                            {
                                //DAO operation failed
                                throw new DAOException(ex.toString());
                            }
                        }
                    }
                }
                
                if(flight.getPlane() != null && flight.getPlane().getId()==null){
                    try
                    {                        
                        pDao.create(flight.getPlane());
                        fDto.getPlane().setId(flight.getPlane().getId());
                    }catch(DataAccessException ex)
                    {
                        //DAO operation failed
                        throw new DAOException(ex.toString());
                    }
                }
                if(flight.getDestinationStart() != null && flight.getDestinationStart().getId()==null){
                    try
                    {
                        dDao.create(flight.getDestinationStart());
                        fDto.getDestinationStart().setId(flight.getDestinationStart().getId());
                    }catch(DataAccessException ex)
                    {
                        //DAO operation failed
                        throw new DAOException(ex.toString());
                    }
                }
                if(flight.getDestinationArrival() != null && flight.getDestinationArrival().getId()==null){
                    try
                    {
                        dDao.create(flight.getDestinationArrival());
                        fDto.getDestinationArrival().setId(flight.getDestinationArrival().getId());
                    }catch(DataAccessException ex)
                    {
                        //DAO operation failed
                        throw new DAOException(ex.toString());
                    }
                }
                try
                {
                    fDao.create(flight);
                    fDto.setId(flight.getId());
                }catch(DataAccessException ex)
                {
                    //DAO operation failed
                    throw new DAOException(ex.toString());
                }
            
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    @Transactional
    public FlightDTO get(Long id) {
        if(id!=null){
            try
            {
                return EntityDTOMapper.flightToFlightDto(fDao.get(id));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    
    @Transactional
    public List<FlightDTO> findByIdentifier(String identifier) {
        if(identifier!=null){
            try
            {
                return EntityDTOMapper.flightListToFlightDtoList(fDao.findByIdentifier(identifier));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    
    @Transactional
    public void update(FlightDTO fDto) {
        if(fDto!=null){
            Flight flight = EntityDTOMapper.flightDtoToFlight(fDto);
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
                fDao.update(flight);
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    @Transactional
    public void remove(FlightDTO fDto) {
        if(fDto!=null){
            Flight flight = EntityDTOMapper.flightDtoToFlight(fDto);
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
                fDao.remove(flight);
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    @Transactional
    public void removeAll() {
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
            fDao.removeAll();
        }catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
    }
    
    @Transactional
    public List<FlightDTO> findAll() {
        try
        {
            return EntityDTOMapper.flightListToFlightDtoList(fDao.findAll());
        }catch(DataAccessException ex)
        {
            //DAO operation failed
            throw new DAOException(ex.toString());
        }
    }
    
    @Transactional
    public List<FlightDTO> findFlightsByDepartureDestination(DestinationDTO destination) {
        if(destination!=null){
            try
            {
                return EntityDTOMapper.flightListToFlightDtoList(fDao.findFlightsByDepartureDestination(EntityDTOMapper.destinationDTOToDestination(destination)));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    @Transactional
    public List<FlightDTO> findFlightsByArrivalDestination(DestinationDTO destination) {
        if(destination!=null){
            try
            {
                return EntityDTOMapper.flightListToFlightDtoList(fDao.findFlightsByArrivalDestination(EntityDTOMapper.destinationDTOToDestination(destination)));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    @Transactional
    public List<FlightDTO> findFlightsByPlane(PlaneDTO plane) {
        if(plane!=null){
            try
            {
                return EntityDTOMapper.flightListToFlightDtoList(fDao.findFlightsByPlane(EntityDTOMapper.planeDTOToPlane(plane)));
            }catch(DataAccessException ex)
            {
                //DAO operation failed
                throw new DAOException(ex.toString());
            }
        }else{
            throw new NullPointerException("Given flight was null.");
        }
    }
    
}
