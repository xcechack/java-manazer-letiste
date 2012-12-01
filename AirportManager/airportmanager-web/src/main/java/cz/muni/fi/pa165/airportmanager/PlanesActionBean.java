package cz.muni.fi.pa165.airportmanager;
 
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
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
import javax.servlet.http.HttpServletResponse;

@UrlBinding("/planes/{$event}/{plane.id}")
public class PlanesActionBean implements ActionBean {
 
    final static Logger log = LoggerFactory.getLogger(FlightsActionBean.class);
 
    private ActionBeanContext context;
    private PlaneDTO plane;
    
    @SpringBean
    protected FlightService flightService;
    
    @SpringBean
    protected PlaneService planeService;
    
    @SpringBean
    protected DestinationService destinationService;
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/all-planes.jsp");
    }
    
    public Resolution save() {
        
        String errors = "";
        
        String producer = context.getRequest().getParameter("new_plane_producer");
        String type = context.getRequest().getParameter("new_plane_type");
        String numberSeats = context.getRequest().getParameter("new_plane_seats");
        String stewards = context.getRequest().getParameter("new_plane_stewards");
        int seats = 0;
        int maxStewards = 0;
        
        try{
            seats = Integer.parseInt(numberSeats);
        }catch(NumberFormatException ex){
            errors += "<br/>Max number of seats parse problem :"+ex;
            return new RedirectResolution(this.getClass(), "all").addParameter("errors", errors);
        }
        try{
            maxStewards = Integer.parseInt(stewards);
        }catch(NumberFormatException ex){
            errors += "<br/>Max number of stewards parse problem :"+ex;
            return new RedirectResolution(this.getClass(), "all").addParameter("errors", errors);
        }
        
        PlaneDTO nPlane = new PlaneDTO();
        nPlane.setProducer(producer);
        nPlane.setType(type);
        nPlane.setMaxStewardessNumber(maxStewards);
        nPlane.setNumberSeats(seats);
        
        try{
            planeService.create(nPlane);
        }catch(Exception e){
            log.error("Problem with update: "+e);
        }
        
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    }
    
    public Resolution ajaxDelete(){
        
        log.debug("delete({})", plane.getId());
        try{
            planeService.remove(plane);
        }catch(Exception ex){
           
            return new StreamingResolution("text/html","<h4>Warning!</h4>Delete of plane with ID "+plane.getId()+" was not successfull.<strong> There might be flight using this plane.</strong>");
        }
       
        context.getResponse().setHeader("Delete-Success", "OK");
        return new StreamingResolution("text/html","OK");
      
    }
    
    public Resolution edit(){
        String errors = "";
        
        String producer = context.getRequest().getParameter("edit_plane_producer");
        String type = context.getRequest().getParameter("edit_plane_type");
        String numberSeats = context.getRequest().getParameter("edit_plane_seats");
        String stewards = context.getRequest().getParameter("edit_plane_stewards");
        String planeId = context.getRequest().getParameter("plane_id");
         
        int seats = 0;
        int maxStewards = 0;
        
        try{
            seats = Integer.parseInt(numberSeats);
        }catch(NumberFormatException ex){
            errors += "<br/>Max number of seats parse problem :"+ex;
            return new RedirectResolution(this.getClass(), "all").addParameter("errors", errors);
        }
        
        try{
            maxStewards = Integer.parseInt(stewards);
        }catch(NumberFormatException ex){
            errors += "<br/>Max number of stewards parse problem :"+ex;
            return new RedirectResolution(this.getClass(), "all").addParameter("errors", errors);
        }
        
        PlaneDTO uPlane;
        
        try{
            Long planeIdL = Long.parseLong(planeId); 
            uPlane = planeService.get(planeIdL);
            
        }catch(NumberFormatException ex){
            log.warn("Error parsing plane id: "+ex.toString());
            return new RedirectResolution(this.getClass(), "all");
        }
        
        
        uPlane.setProducer(producer);
        uPlane.setType(type);
        uPlane.setMaxStewardessNumber(maxStewards);
        uPlane.setNumberSeats(seats);
        
        try{
            planeService.update(uPlane);
        }catch(Exception e){
            log.error("Problem with update: "+e);
        }
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    }
    
    public List<PlaneDTO> getPlanes() {
        return planeService.findAll();
    }
    
    public PlaneDTO getPlane(){
        return plane;
    }
    
    public void setPlane(PlaneDTO plane){
        this.plane = plane;
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