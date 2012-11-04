/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.mock;

import cz.muni.fi.pa165.airportmanager.Plane;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public class PlaneDaoMock implements PlaneDAO{
    
    private List<Plane> mockDB = new ArrayList<Plane>();
    private long idSequence = 0;
    
    public void create(Plane plane) {
        if (plane == null) {
            throw new NullPointerException("Plane is null when it is created");
        }
        if (plane.getId() == null) {
            plane.setId(Long.valueOf(idSequence));
            idSequence++;
        }
        mockDB.add(plane);
    }

    public Plane get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Plane plane) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(Plane plane) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Plane> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Plane> findByProducer(String producer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Plane> findByType(String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Plane> findByMaxNumberOfSeats(int number) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Plane> findPlaneWithGreaterNumberOfSeats(int number) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
