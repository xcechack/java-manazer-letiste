/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marek
 */
@Entity
@Table(name="flights")
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private Long id;
   
    private String flightIdentifier;
    private Timestamp timeStart;
    private Timestamp timeArrival;
    private Plane plane;
    private Destination destinationStart;
    private Destination destinationArrival;
    
    private List<Stewardess> stewardess;

    @ManyToMany
    @JoinTable(
      name="FLIGHT_STEWARDESS",
      joinColumns={@JoinColumn(name="FLIGHT_ID", referencedColumnName="ID")},
      inverseJoinColumns={@JoinColumn(name="STEWARDESS_ID", referencedColumnName="ID")})
    public List<Stewardess> getStewardess() {
        return stewardess;
    }

    public void setStewardess(List<Stewardess> stewardess) {
        this.stewardess = stewardess;
    }
    
    @Column(name="FlightIdentifier")
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
    
    @ManyToOne
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    
    @ManyToOne
    public Destination getDestinationStart() {
        return destinationStart;
    }

    public void setDestinationStart(Destination destinationStart) {
        this.destinationStart = destinationStart;
    }
    
    @ManyToOne
    public Destination getDestinationArrival() {
        return destinationArrival;
    }

    public void setDestinationArrival(Destination destinationArrival) {
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
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.airportmanager.Flight[ id=" + id + " ]";
    }
    
}
