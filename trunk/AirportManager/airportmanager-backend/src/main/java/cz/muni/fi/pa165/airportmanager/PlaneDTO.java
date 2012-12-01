/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

/**
 *
 * @author Vasa
 */
public class PlaneDTO {
    private Long id;
    private String producer;
    private String type;
    private int numberSeats;
    private int maxStewardessNumber;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    public int getMaxStewardessNumber() {
        return maxStewardessNumber;
    }

    public void setMaxStewardessNumber(int maxStewardessNumber) {
        this.maxStewardessNumber = maxStewardessNumber;
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
        if (!(object instanceof PlaneDTO)) {
            return false;
        }
        PlaneDTO other = (PlaneDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.airportmanager.PlaneDTO[ id=" + id + " ]";
    }
}
