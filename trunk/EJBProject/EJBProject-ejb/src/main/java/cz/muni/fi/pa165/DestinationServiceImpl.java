/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
@Stateless
@Local(value=DestinationService.class)
public class DestinationServiceImpl implements DestinationService{

    

    
    @Resource
    private SessionContext ctx;
    
    @EJB
    private DestinationDAO destinationDAO;

    @Override
    //@Asynchronous
    //@Interceptors(LoggingInterceptor.class)
    public void create(Destination destination){
        
        destinationDAO.create(destination);
 
        // Pokud bychom potrebovali vynutit rollback u CMT
        if (false) {
            ctx.setRollbackOnly();
        }
        
    }
    
}
