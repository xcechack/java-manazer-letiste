/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.FlightService;
import cz.muni.fi.pa165.airportmanager.services.PlaneService;
import cz.muni.fi.pa165.airportmanager.services.UserService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marek
 */
@UrlBinding("/users/{$event}/{user.id}")
public class UsersActionBean implements ActionBean {
 
    final static Logger log = LoggerFactory.getLogger(FlightsActionBean.class);
 
    private ActionBeanContext context;
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO destination) {
        this.user = destination;
    }
    
    @SpringBean
    protected UserService userService;
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/users.jsp");
    }
    
    public Resolution save() {
        
        String errors = "";
        
        String name = context.getRequest().getParameter("new_user_name");
        String password = context.getRequest().getParameter("new_user_password");
      
        UserDTO nuser = new UserDTO();
        
        nuser.setUsername(name);
        nuser.setPassword(password);
        List<String> credentials = new ArrayList<String>();
        credentials.add("USER");
        nuser.setCredentials(credentials);
       
        try{
            userService.create(nuser);
        }catch(Exception e){
            log.error("Problem with create: "+e);
        }
        
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    }
    
    public List<UserDTO> getUsers() {
        return userService.findAll();
    }
    
    public Resolution ajaxDelete(){
        
        log.debug("delete({})", user.getId());
        try{
            userService.delete(user);
        }catch(Exception ex){
           
            return new StreamingResolution("text/html","<h4>Warning!</h4>Delete of destination with ID "+user.getId()+" was not successfull.<strong> There might be flight using this destination.</strong>");
        }
       
        context.getResponse().setHeader("Delete-Success", "OK");
        return new StreamingResolution("text/html","OK");
    }
    
 
    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
 
    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
