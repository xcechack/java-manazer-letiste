/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import java.util.List;

/**
 *
 * @author Frkal
 */
public interface StewardessDAO {

    void create(Stewardess stewardess);

    Stewardess get(Long id);

    void update(Stewardess stewardess);

    void remove(Stewardess stewardess);

    List<Stewardess> findAll();
}
