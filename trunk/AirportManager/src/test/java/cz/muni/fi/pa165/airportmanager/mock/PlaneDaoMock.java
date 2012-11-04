/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.mock;

import cz.muni.fi.pa165.airportmanager.Plane;
import cz.muni.fi.pa165.airportmanager.PlaneDAO;
import java.util.ArrayList;
import java.util.Collections;
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
        if (id == null) {
            throw new NullPointerException("Id plane is null ");
        }
        for (Plane plane : mockDB) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    public void update(Plane plane) {
        if (plane == null) {
            throw new NullPointerException("Plane is null when it is updated");
        }
        for (Plane pl : mockDB) {
            if (pl.getId().equals(plane.getId())) {
                pl.setMaxStewardessNumber(plane.getMaxStewardessNumber());
                pl.setNumberSeats(plane.getNumberSeats());
                pl.setProducer(plane.getProducer());
                pl.setType(plane.getType());
            }
        }
    }

    public void remove(Plane plane) {
        if (plane == null) {
            throw new NullPointerException("Plane is null when it is removed");
        }
        int index = -1;
        for (int i = 0; i < mockDB.size(); i++) {
            if (plane.getId().equals(mockDB.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            mockDB.remove(index);
        }
    }

    public List<Plane> findAll() {
        return Collections.unmodifiableList(mockDB);
    }

    public List<Plane> findByProducer(String producer) {
        if (producer == null) {
            throw new NullPointerException("Country is null ");
        }
        List<Plane> result = new ArrayList<Plane>();
        for (Plane pl : mockDB) {
            if (pl.getProducer().equals(producer)) {
                result.add(pl);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Plane> findByType(String type) {
        if (type == null) {
            throw new NullPointerException("Country is null ");
        }
        List<Plane> result = new ArrayList<Plane>();
        for (Plane pl : mockDB) {
            if (pl.getType().equals(type)) {
                result.add(pl);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Plane> findByMaxNumberOfSeats(int number) {
        if (number == 0) {
            throw new NullPointerException("Country is null ");
        }
        List<Plane> result = new ArrayList<Plane>();
        for (Plane pl : mockDB) {
            if (pl.getNumberSeats() == number )) {
                result.add(pl);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Plane> findPlaneWithGreaterNumberOfSeats(int number) {
        if (number == 0) {
            throw new NullPointerException("Country is null ");
        }
        List<Plane> result = new ArrayList<Plane>();
        for (Plane pl : mockDB) {
            if (pl.getNumberSeats()>= number) {
                result.add(pl);
            }
        }
        return Collections.unmodifiableList(result);
    }
    
}
