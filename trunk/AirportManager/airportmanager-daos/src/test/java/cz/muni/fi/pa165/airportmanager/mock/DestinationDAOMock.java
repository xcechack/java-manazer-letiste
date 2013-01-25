/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.mock;

import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.DestinationDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
public class DestinationDAOMock implements DestinationDAO {

    private List<Destination> mockDB = new ArrayList<Destination>();
    private long idSequence = 0;

    public void create(Destination destination) {
        if (destination == null) {
            throw new NullPointerException("Destination is null when it is created");
        }
        if (destination.getId() == null) {
            destination.setId(Long.valueOf(idSequence));
            idSequence++;
        }
        mockDB.add(destination);
    }

    public Destination get(Long id) {
        if (id == null) {
            throw new NullPointerException("Id destination is null ");
        }
        for (Destination dest : mockDB) {
            if (dest.getId().equals(id)) {
                return dest;
            }
        }
        return null;
    }

    public void update(Destination destination) {
        if (destination == null) {
            throw new NullPointerException("Destination is null when it is updated");
        }
        for (Destination dest : mockDB) {
            if (dest.getId().equals(destination.getId())) {
                dest.setCity(destination.getCity());
                dest.setCountry(destination.getCountry());
            }
        }
    }

    public void remove(Destination destination) {
        if (destination == null) {
            throw new NullPointerException("Destination is null when it is removed");
        }
        int index = -1;
        for (int i = 0; i < mockDB.size(); i++) {
            if (destination.getId().equals(mockDB.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            mockDB.remove(index);
        }
    }

    public List<Destination> findAll() {
        return Collections.unmodifiableList(mockDB);
    }

    public List<Destination> findByCountry(String country) {
        if (country == null) {
            throw new NullPointerException("Country is null ");
        }
        List<Destination> result = new ArrayList<Destination>();
        for (Destination dest : mockDB) {
            if (dest.getCountry().equals(country)) {
                result.add(dest);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Destination> findByCity(String city) {
        if (city == null) {
            throw new NullPointerException("Sity is null ");
        }
        List<Destination> result = new ArrayList<Destination>();
        for (Destination dest : mockDB) {
            if (dest.getCity().equals(city)) {
                result.add(dest);
            }
        }
        return Collections.unmodifiableList(result);
    }
}
