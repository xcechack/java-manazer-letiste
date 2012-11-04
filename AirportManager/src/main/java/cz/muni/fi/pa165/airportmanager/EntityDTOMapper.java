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
    
    public static DestinationDTO destinationToDestinationDTO(Destination input)
    {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        DestinationDTO result = new DestinationDTO();
        
        result.setId(input.getId());
        result.setCountry(input.getCountry());
        result.setCity(input.getCity());
        
        return result;
    }
    
    public static Destination destinationDTOToDestination(DestinationDTO input)
    {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        Destination result = new Destination();
        
        result.setId(input.getId());
        result.setCountry(input.getCountry());
        result.setCity(input.getCity());
        
        return result;
    }
    
    public static StewardessDTO stewardessToStewardessDTO(Stewardess input)
    {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
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
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        Stewardess result = new Stewardess();
        
        result.setId(input.getId());
        result.setBirthNumber(input.getBirthNumber());
        result.setName(input.getName());
        result.setSurname(input.getSurname());
        result.setSex(input.getSex());
        
        return result;
    }
    
    public static FlightDTO flightToFlightDTO(Flight input)
    {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        FlightDTO result = new FlightDTO();
        
        result.setTimeArrival(input.getTimeArrival());
        result.setTimeStart(input.getTimeStart());
        result.setDestinationArrival(destinationToDestinationDTO(input.getDestinationArrival()));
        result.setDestinationStart(destinationToDestinationDTO(input.getDestinationStart()));
        result.setFlightIdentifier(input.getFlightIdentifier());
        result.setId(input.getId());
        result.setPlane(planeToPlaneDTO(input.getPlane()));
        
        List<StewardessDTO> listOfStewardessDTO = new ArrayList<StewardessDTO>();
        for(Stewardess stewardess : input.getStewardess())
        {
            listOfStewardessDTO.add(stewardessToStewardessDTO(stewardess));
        }
        result.setStewardess(listOfStewardessDTO);
        
        return result;
    }
    
    public static Flight flightDTOToFlight(FlightDTO input)
    {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        Flight result = new Flight();
        
        result.setTimeArrival(input.getTimeArrival());
        result.setTimeStart(input.getTimeStart());
        result.setDestinationArrival(destinationDTOToDestination(input.getDestinationArrival()));
        result.setDestinationStart(destinationDTOToDestination(input.getDestinationStart()));
        result.setFlightIdentifier(input.getFlightIdentifier());
        result.setId(input.getId());
        result.setPlane(planeDTOToPlane(input.getPlane()));
        
        List<Stewardess> listOfStewardess = new ArrayList<Stewardess>();
        for(StewardessDTO stewardess : input.getStewardess())
        {
            listOfStewardess.add(stewardessDTOToStewardess(stewardess));
        }
        result.setStewardess(listOfStewardess);
        
        return result;
    }

    private static PlaneDTO planeToPlaneDTO(Plane input) {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        PlaneDTO result = new PlaneDTO();
        
        result.setId(input.getId());
        result.setMaxStewardessNumber(input.getMaxStewardessNumber());
        result.setNumberSeats(input.getNumberSeats());
        result.setProducer(input.getProducer());
        result.setType(input.getType());
        
        return result;
    }

    private static Plane planeDTOToPlane(PlaneDTO input) {
        if(input == null)throw new IllegalArgumentException("destination is null");
        
        Plane result = new Plane();
        
        result.setId(input.getId());
        result.setMaxStewardessNumber(input.getMaxStewardessNumber());
        result.setNumberSeats(input.getNumberSeats());
        result.setProducer(input.getProducer());
        result.setType(input.getType());
        
        return result;
    }
}
