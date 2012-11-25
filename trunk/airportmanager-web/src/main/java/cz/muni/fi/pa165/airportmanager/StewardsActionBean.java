package cz.muni.fi.pa165.airportmanager;
 
import cz.muni.fi.pa165.airportmanager.Flight;
import cz.muni.fi.pa165.airportmanager.FlightDTO;
import cz.muni.fi.pa165.airportmanager.enums.Sex;
import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.FlightService;
import cz.muni.fi.pa165.airportmanager.services.FlightServiceImpl;
import cz.muni.fi.pa165.airportmanager.services.PlaneService;
import cz.muni.fi.pa165.airportmanager.services.StewardessService;
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

@UrlBinding("/stewards/{$event}/{steward.id}")
public class StewardsActionBean implements ActionBean {
 
    final static Logger log = LoggerFactory.getLogger(FlightsActionBean.class);
 
    private ActionBeanContext context;
    private StewardessDTO steward;

    public StewardessDTO getSteward() {
        return steward;
    }

    public void setSteward(StewardessDTO steward) {
        this.steward = steward;
    }

  
    @SpringBean
    protected StewardessService stewardessService;
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/all-stewards.jsp");
    }
    
    public Resolution save() {
        
        String errors = "";
        
        String name = context.getRequest().getParameter("new_name");
        String surname = context.getRequest().getParameter("new_surname");
        String birthNumber = context.getRequest().getParameter("new_birthnumber");
        
        StewardessDTO nSteward = new StewardessDTO();
        nSteward.setBirthNumber(birthNumber);
        nSteward.setName(name);
        nSteward.setSurname(surname);
        
        if(context.getRequest().getParameter("new_sex").equals(Sex.male)){
            nSteward.setSex(Sex.male);
        }else{
            nSteward.setSex(Sex.female);
        }
        try{
            stewardessService.create(nSteward);
        }catch(Exception e){
            log.error("Problem with create: "+e);
        }
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    }
    
    public Resolution edit(){
        
        String errors = "";
        
        String name = context.getRequest().getParameter("edit_name");
        String surname = context.getRequest().getParameter("edit_surname");
        String birthNumber = context.getRequest().getParameter("edit_birthnumber");
        String stewardId = context.getRequest().getParameter("steward_id");
        
        
        StewardessDTO nSteward; 
        
        try{
            Long stewardIdL = Long.parseLong(stewardId); 
            nSteward = stewardessService.get(stewardIdL);
            
        }catch(NumberFormatException ex){
            log.warn("Error parsing plane id: "+ex.toString());
            return new RedirectResolution(this.getClass(), "all");
        }
        
        
        nSteward.setBirthNumber(birthNumber);
        nSteward.setName(name);
        nSteward.setSurname(surname);
        
        if(context.getRequest().getParameter("edit_sex").equals(""+Sex.male)){
            nSteward.setSex(Sex.male);
        }else{
            nSteward.setSex(Sex.female);
        }
        
        try{
        stewardessService.update(nSteward);
        }catch(Exception e){
            log.error("Problem with update: "+e);
        }
        return new RedirectResolution(this.getClass(), "all").addParameter("edited", "");
    }
    
    public List<StewardessDTO> getStewardess() {
        return stewardessService.findAll();
    }
    
    public Resolution ajaxDelete(){
        
        log.debug("delete({})", steward.getId());
        try{
            stewardessService.remove(steward);
        }catch(Exception ex){
           
            return new StreamingResolution("text/html","<h4>Warning!</h4>Delete of destination with ID "+steward.getId()+" was not successfull.<strong> There might be flight using this destination.</strong>");
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