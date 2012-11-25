package cz.muni.fi.pa165.airportmanager;
 
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.FlightService;
import cz.muni.fi.pa165.airportmanager.services.FlightServiceImpl;
import cz.muni.fi.pa165.airportmanager.services.PlaneService;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.util.List;
import java.util.logging.Level;

@UrlBinding("/destinations/{$event}/{plane.id}")
public class DestinationsActionBean implements ActionBean {
 
    final static Logger log = LoggerFactory.getLogger(FlightsActionBean.class);
 
    private ActionBeanContext context;
    private DestinationDTO destination;

    public DestinationDTO getDestination() {
        return destination;
    }

    public void setDestination(DestinationDTO destination) {
        this.destination = destination;
    }
    
    @SpringBean
    protected FlightService flightService;
    
    @SpringBean
    protected PlaneService planeService;
    
    @SpringBean
    protected DestinationService destinationService;
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/all-destinations.jsp");
    }
    
    public Resolution save() {
        
        String errors = "";
        
        String city = context.getRequest().getParameter("new_dest_city");
        String country = context.getRequest().getParameter("new_dest_country");
      
        DestinationDTO destination = new DestinationDTO();
        
        destination.setCity(city);
        destination.setCountry(country);
       
        try{
            destinationService.create(destination);
        }catch(Exception e){
            log.error("Problem with create: "+e);
        }
        
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    }
    
    public Resolution edit(){
        
        String errors = "";
        
        String city = context.getRequest().getParameter("edit_dest_city");
        String country = context.getRequest().getParameter("edit_dest_country");
        String destId = context.getRequest().getParameter("destination_id");
        
        DestinationDTO uDestination; 
        
        try{
            Long destIdL = Long.parseLong(destId); 
            uDestination = destinationService.get(destIdL);
            
        }catch(NumberFormatException ex){
            log.warn("Error parsing plane id: "+ex.toString());
            return new RedirectResolution(this.getClass(), "all");
        }
        
        
        uDestination.setCity(city);
        uDestination.setCountry(country);
        
        try{
            destinationService.update(uDestination);
        }catch(Exception e){
            log.error("Problem with update: "+e);
        }
        
        return new RedirectResolution(this.getClass(), "all").addParameter("edited", "");
    }
    
    public List<DestinationDTO> getDestinations() {
        return destinationService.findAll();
    }
    
    public Resolution ajaxDelete(){
        
        log.debug("delete({})", destination.getId());
        try{
            destinationService.remove(destination);
        }catch(Exception ex){
           
            return new StreamingResolution("text/html","<h4>Warning!</h4>Delete of destination with ID "+destination.getId()+" was not successfull.<strong> There might be flight using this destination.</strong>");
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