package cz.muni.fi.pa165.airportmanager.exceptions;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Vasa
 */
public class DAOException extends DataAccessException{
    
    public DAOException(String msg)
    {
        super(msg);
    }
    
    public DAOException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
