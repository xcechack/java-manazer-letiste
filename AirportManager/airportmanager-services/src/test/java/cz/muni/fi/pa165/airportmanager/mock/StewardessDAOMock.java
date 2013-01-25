/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager.mock;

import cz.muni.fi.pa165.airportmanager.Destination;
import cz.muni.fi.pa165.airportmanager.Stewardess;
import cz.muni.fi.pa165.airportmanager.StewardessDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Frkal
 */
public class StewardessDAOMock implements StewardessDAO{
    private List<Stewardess> mockDB = new ArrayList<Stewardess>();
    private long idSequence = 0;

    public void create(Stewardess stewardess) {if (stewardess == null) {
            throw new NullPointerException("Destination is null when it is created");
        }
        if (stewardess.getId() == null) {
            stewardess.setId(Long.valueOf(idSequence));
            idSequence++;
        }
        mockDB.add(stewardess);
    }

    public Stewardess get(Long id) {
        if (id == null) {
            throw new NullPointerException("Id destination is null ");
        }
        for (Stewardess dest : mockDB) {
            if (dest.getId().equals(id)) {
                return dest;
            }
        }
        return null;
    }

    public void update(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Destination is null when it is updated");
        }
        for (Stewardess stewardess1 : mockDB) {
            if (stewardess1.getId().equals(stewardess.getId())) {
                stewardess1.setBirthNumber(stewardess.getBirthNumber());
                stewardess1.setName(stewardess.getName());
                stewardess1.setSurname(stewardess.getSurname());
                stewardess1.setSex(stewardess.getSex());
            }
        }
    }

    public void remove(Stewardess stewardess) {
        if (stewardess == null) {
            throw new NullPointerException("Stewardess is null when it is removed");
        }
        int index = -1;
        for (int i = 0; i < mockDB.size(); i++) {
            if (stewardess.getId().equals(mockDB.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            mockDB.remove(index);
        }
    }

    public List<Stewardess> findAll() {
        return Collections.unmodifiableList(mockDB);
    }
    
}
