/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Vasa
 */
public class FlightDTO {
    private Long id;
   
    private String flightIdentifier;
    private Timestamp timeStart;
    private Timestamp timeArrival;
    private PlaneDTO plane;
    private DestinationDTO destinationStart;
    private DestinationDTO destinationArrival;
    
    private List<StewardessDTO> stewardess;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<StewardessDTO> getStewardess() {
        return stewardess;
    }

    public void setStewardess(List<StewardessDTO> stewardess) {
        this.stewardess = stewardess;
    }
    
    public String getFlightIdentifier() {
        return flightIdentifier;
    }

    public void setFlightIdentifier(String flightIdentifier) {
        this.flightIdentifier = flightIdentifier;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(Timestamp timeArrival) {
        this.timeArrival = timeArrival;
    }
    
    public PlaneDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneDTO plane) {
        this.plane = plane;
    }
    
    public DestinationDTO getDestinationStart() {
        return destinationStart;
    }

    public void setDestinationStart(DestinationDTO destinationStart) {
        this.destinationStart = destinationStart;
    }
    
    public DestinationDTO getDestinationArrival() {
        return destinationArrival;
    }

    public void setDestinationArrival(DestinationDTO destinationArrival) {
        this.destinationArrival = destinationArrival;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlightDTO)) {
            return false;
        }
        FlightDTO other = (FlightDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.airportmanager.FlightDTO[ id=" + id + " ]";
    }
}
