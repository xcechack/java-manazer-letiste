/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Vasa
 */
public class EntityDTOMapper {
    
    public static UserDTO userToUserDTO(User input)
    {
        if(input == null){
            return null;
        }
        
        UserDTO result = new UserDTO();
        
        result.setId(input.getId());
        result.setUsername(input.getUsername());
        result.setPassword(input.getPassword());
        result.setCredentials(input.getCredentials());
        
        return result;
    }
    
    public static User userDTOToUser(UserDTO input)
    {
        if(input == null){
            return null;
        }
        
        User result = new User();
        
        result.setId(input.getId());
        result.setUsername(input.getUsername());
        result.setPassword(input.getPassword());
        result.setCredentials(input.getCredentials());
        
        return result;
    }
    
    public static List<UserDTO> userListToUserDTOList(List<User> users){
        if(users == null){
            return null;
        }
         
        
        List<UserDTO> uDtoList =  new ArrayList<UserDTO>();
        for(int i = 0; i<users.size(); i++){
            uDtoList.add(EntityDTOMapper.userToUserDTO(users.get(i)));
        }
        
        return uDtoList;
    }
    
    public static DestinationDTO destinationToDestinationDTO(Destination input)
    {
        if(input == null) {
            return null;
        }
        
        DestinationDTO result = new DestinationDTO();
        
        result.setId(input.getId());
        result.setCountry(input.getCountry());
        result.setCity(input.getCity());
        
        return result;
    }
    
    public static Destination destinationDTOToDestination(DestinationDTO input)
    {
        if(input == null) {
            return null;
        }
        
        Destination result = new Destination();
        
        result.setId(input.getId());
        result.setCountry(input.getCountry());
        result.setCity(input.getCity());
        
        return result;
    }
    
     public static List<DestinationDTO> destinationListToDestinationDTOList(List<Destination> destination){
        if(destination == null){
            return null;
        }
         
        
        List<DestinationDTO> dDtoList =  new ArrayList<DestinationDTO>();
        for(int i = 0; i<destination.size(); i++){
            dDtoList.add(EntityDTOMapper.destinationToDestinationDTO(destination.get(i)));
        }
        
        return dDtoList;
    }
    
    public static StewardessDTO stewardessToStewardessDTO(Stewardess input)
    {
        if(input == null) {
            return null;
        }
        
        StewardessDTO result = new StewardessDTO();
        
        result.setId(input.getId());
        result.setBirthNumber(input.getBirthNumber());
        result.setName(input.getName());
        result.setSurname(input.getSurname());
        result.setSex(input.getSex());
        
        return result;
    }
    
    public static Stewardess stewardessDTOToStewardess(StewardessDTO input)
    {
        if(input == null) {
            return null;
        }
        
        Stewardess result = new Stewardess();
        
        result.setId(input.getId());
        result.setBirthNumber(input.getBirthNumber());
        result.setName(input.getName());
        result.setSurname(input.getSurname());
        result.setSex(input.getSex());
        
        return result;
    }
    
    public static List<StewardessDTO> stewardessListToStewardessDTOList(List<Stewardess> stewardess){
        
        if(stewardess == null){
            return null;
        }
       
        List<StewardessDTO> sDtoList = new ArrayList<StewardessDTO>();
        for(int i = 0; i<stewardess.size(); i++){
            sDtoList.add(EntityDTOMapper.stewardessToStewardessDTO(stewardess.get(i)));
        }
      
        return sDtoList;
    }
    
    public static FlightDTO flightToFlightDto(Flight flight){
        if(flight!=null){
            FlightDTO flDto = new FlightDTO();
            flDto.setId(flight.getId());
            flDto.setFlightIdentifier(flight.getFlightIdentifier());
           
            flDto.setPlane(EntityDTOMapper.planeToPlaneDTO(flight.getPlane()));
            flDto.setDestinationStart(EntityDTOMapper.destinationToDestinationDTO(flight.getDestinationStart()));
            flDto.setDestinationArrival(EntityDTOMapper.destinationToDestinationDTO(flight.getDestinationArrival()));
            flDto.setTimeStart(flight.getTimeStart());
            flDto.setTimeArrival(flight.getTimeArrival());
            
            if(flight.getStewardess() != null){
                List<StewardessDTO> stewardessDtoList = new ArrayList<StewardessDTO>();
                for(int i = 0; i < flight.getStewardess().size(); i++){
                    stewardessDtoList.add(EntityDTOMapper.stewardessToStewardessDTO(flight.getStewardess().get(i)));
                }
                flDto.setStewardess(stewardessDtoList);
            }else{
                flDto.setStewardess(null);
            }
            
            return flDto;
        }
        return null;
    }
    
    public static Flight flightDtoToFlight(FlightDTO flDto){
        if(flDto!=null){
            Flight flight = new Flight();
            flight.setId(flDto.getId());
            flight.setFlightIdentifier(flDto.getFlightIdentifier());
            flight.setPlane(EntityDTOMapper.planeDTOToPlane(flDto.getPlane()));
            flight.setDestinationStart(EntityDTOMapper.destinationDTOToDestination(flDto.getDestinationStart()));
            flight.setDestinationArrival(EntityDTOMapper.destinationDTOToDestination(flDto.getDestinationArrival()));
            flight.setTimeStart(flDto.getTimeStart());
            flight.setTimeArrival(flDto.getTimeArrival());
            
            if(flDto.getStewardess() != null){
                List<Stewardess> stewardessList = new ArrayList<Stewardess>();
                for(int i = 0; i < flDto.getStewardess().size(); i++){
                    stewardessList.add(EntityDTOMapper.stewardessDTOToStewardess(flDto.getStewardess().get(i)));
                }
                flight.setStewardess(stewardessList);
            }else{
                flight.setStewardess(null);
            }
            
            return flight;
        }
        return null;
    }
    
    public static List<FlightDTO> flightListToFlightDtoList(List<Flight> flights) {
        if(flights == null){
            return null;
        }
        List<FlightDTO> fDtoList =  new ArrayList<FlightDTO>();
       
        for(int i = 0; i<flights.size(); i++){
           fDtoList.add(EntityDTOMapper.flightToFlightDto(flights.get(i)));
        }
        
        return fDtoList;
    }

    public static PlaneDTO planeToPlaneDTO(Plane input) {
        if(input == null) {
            return null;
        }
        
        PlaneDTO result = new PlaneDTO();
        
        result.setId(input.getId());
        result.setMaxStewardessNumber(input.getMaxStewardessNumber());
        result.setNumberSeats(input.getNumberSeats());
        result.setProducer(input.getProducer());
        result.setType(input.getType());
        
        return result;
    }

    public static Plane planeDTOToPlane(PlaneDTO input) {
        if(input == null) {
            return null;
        }
        
        Plane result = new Plane();
        
        result.setId(input.getId());
        result.setMaxStewardessNumber(input.getMaxStewardessNumber());
        result.setNumberSeats(input.getNumberSeats());
        result.setProducer(input.getProducer());
        result.setType(input.getType());
        
        return result;
    }
    public static List<PlaneDTO> planeListToPlaneDTOList(List<Plane> planes){
        if(planes == null){
            return null;
        }
        
        List<PlaneDTO> pDtoList = new ArrayList<PlaneDTO>();
        for(int i = 0; i<planes.size(); i++){
            pDtoList.add(EntityDTOMapper.planeToPlaneDTO(planes.get(i)));
        }
        return pDtoList;
    }
}
