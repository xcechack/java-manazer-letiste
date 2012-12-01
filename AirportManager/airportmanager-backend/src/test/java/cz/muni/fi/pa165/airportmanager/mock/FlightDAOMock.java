/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.mock;

import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.FlightDAO;
import cz.muni.fi.pa165.airportmanager.Plane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Maks
 */
public class FlightDAOMock implements FlightDAO {

    private List<Flight> mockDB = new ArrayList<Flight>();
    private long idSequence = 0;

    public void create(Flight flight) {
        if (flight == null) {
            throw new NullPointerException("Flight is null when it is created");
        }
        if (flight.getId() == null) {
            flight.setId(Long.valueOf(idSequence));
            idSequence++;
        }
        mockDB.add(flight);
    }

    public Flight get(Long id) {
        if (id == null) {
            throw new NullPointerException("Id flight is null ");
        }
        for (Flight flight : mockDB) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }

    public List<Flight> findByIdentifier(String identifier) {
        List<Flight> flights = new ArrayList<Flight>();
        if (identifier == null) {
            throw new NullPointerException("Identifier is null ");
        }
        for(Flight flight : mockDB){
            if(identifier.equals(flight.getFlightIdentifier())){
                flights.add(flight);
            }
        }
        return Collections.unmodifiableList(flights);
    }

    public void update(Flight flight) {
        if (flight == null) {
            throw new NullPointerException("Flight is null when it is updated");
        }
        for (Flight dbFlight : mockDB) {
            if (dbFlight.getId().equals(flight.getId())) {
                dbFlight.setDestinationArrival(flight.getDestinationArrival());
                dbFlight.setDestinationStart(flight.getDestinationStart());
                dbFlight.setFlightIdentifier(flight.getFlightIdentifier());
                dbFlight.setPlane(flight.getPlane());
                dbFlight.setStewardess(flight.getStewardess());
                dbFlight.setTimeArrival(flight.getTimeArrival());
                dbFlight.setTimeStart(flight.getTimeStart());
            }
        }
    }

    public void remove(Flight flight) {
        if (flight == null) {
            throw new NullPointerException("Flight is null when it is removed");
        }
        
        int index = -1;
        for (int i = 0; i < mockDB.size(); i++) {
            if (flight.getId().equals(mockDB.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            mockDB.remove(index);
        }
    }

    public void removeAll() {
        mockDB = new ArrayList<Flight>();
    }

    public List<Flight> findAll() {
        return Collections.unmodifiableList(mockDB);
    }

    public List<Flight> findFlightsByDepartureDestination(Destination destination) {
        List<Flight> flights = new ArrayList<Flight>();
        if (destination == null) {
            throw new NullPointerException("Destination is null ");
        }
        for(Flight flight : mockDB){
            if(destination.equals(flight.getDestinationStart())){
                flights.add(flight);
            }
        }
        return Collections.unmodifiableList(flights);
    }

    public List<Flight> findFlightsByArrivalDestination(Destination destination) {
        List<Flight> flights = new ArrayList<Flight>();
        if (destination == null) {
            throw new NullPointerException("Destination is null ");
        }
        for(Flight flight : mockDB){
            if(destination.equals(flight.getDestinationArrival())){
                flights.add(flight);
            }
        }
        return Collections.unmodifiableList(flights);
    }

    public List<Flight> findFlightsByPlane(Plane plane) {
        List<Flight> flights = new ArrayList<Flight>();
        if (plane == null) {
            throw new NullPointerException("Plane is null ");
        }
        for(Flight flight : mockDB){
            if(plane.equals(flight.getPlane())){
                flights.add(flight);
            }
        }
        return Collections.unmodifiableList(flights);
    }
}
