package cz.muni.fi.pa165.airportmanager;
 
import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.FlightService;
import cz.muni.fi.pa165.airportmanager.services.PlaneService;
import cz.muni.fi.pa165.airportmanager.services.StewardessService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@UrlBinding("/flights/{$event}/{flight.id}")
public class FlightsActionBean implements ActionBean {
 
    final static Logger log = LoggerFactory.getLogger(FlightsActionBean.class);
 
    private ActionBeanContext context;
    private FlightDTO flight;
    //private List<StewardessDTO> stewardess;

    
    @SpringBean
    protected FlightService flightService;
    
    @SpringBean
    protected PlaneService planeService;
    
    @SpringBean
    protected DestinationService destinationService;
    
    @SpringBean
    protected StewardessService stewardessService;
    
    @SpringBean("airportAuthManager")
    protected AuthenticationManager am;
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        if(am == null){
            log.debug("AM NULL");
        }
        try {
            PlaneDTO p = new PlaneDTO();
            try{
                planeService.create(p);
                log.debug("1 Create without credentials: FAIL");
            }catch(Exception e){
                log.debug("1 Create without credentials: OK");
            }
            Authentication request = new UsernamePasswordAuthenticationToken("user", "user");
            Authentication result = am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
             try{
                planeService.create(p);
                log.debug("1 Create without credentials: OK");
            }catch(Exception e){
                log.debug("2 Create without credentials: FAIL");
            }
            
        } catch(AuthenticationException e) {
            log.debug("Authentication failed: " + e.getMessage());
        }
       
        return new ForwardResolution("/all-flights.jsp");
    }
    /*
    public void setStewardess(List<StewardessDTO> stewardess){
        this.stewardess = stewardess;
    }
    
    public List<StewardessDTO> getStewardess() {
        return stewardess;
    }*/
    
    public Resolution save() {
        
        FlightDTO nFlight = null;
       
        nFlight = new FlightDTO();
        
        
        String errors = "";
        String flightIdentifier;
        String depDateString;
        String arrDateString;
        String depCity;
        String depCountry;
        String arrCity;
        String arrCountry;
        String planeId;
        Collection<Stewardess> stewards;
        
        /** GETTING DATA FROM FORM */
        flightIdentifier = context.getRequest().getParameter("new_flight_identifier");

        depDateString = context.getRequest().getParameter("new_dep_year")+"-"+context.getRequest().getParameter("new_dep_month")+"-"+context.getRequest().getParameter("new_dep_day");
        depDateString += " "+context.getRequest().getParameter("new_dep_hour")+":"+context.getRequest().getParameter("new_dep_minute");

        arrDateString = context.getRequest().getParameter("new_arr_year")+"-"+context.getRequest().getParameter("new_arr_month")+"-"+context.getRequest().getParameter("new_arr_day");
        arrDateString += " "+context.getRequest().getParameter("new_arr_hour")+":"+context.getRequest().getParameter("new_arr_minute");


        depCity = context.getRequest().getParameter("new_dep_city");
        depCountry = context.getRequest().getParameter("new_dep_country");

        arrCity = context.getRequest().getParameter("new_arr_city");
        arrCountry = context.getRequest().getParameter("new_arr_country");

        planeId = context.getRequest().getParameter("new_plane");
        /**-----------------------****/
        
        
        
        nFlight.setFlightIdentifier(flightIdentifier);
        
        Timestamp depTimestamp = Timestamp.valueOf(depDateString +":00");
       
        Timestamp arrTimestamp = Timestamp.valueOf(arrDateString +":00");
        
        nFlight.setTimeStart((Timestamp)depTimestamp.clone());
        
        
       
        nFlight.setTimeArrival((Timestamp) arrTimestamp.clone());
        
        
        if(depCity != null && depCountry != null && depCity.length() > 0 && depCountry.length() > 0){
            List<DestinationDTO> existing = destinationService.findByCity(depCity);
            DestinationDTO dest = null;
            for(int i = 0; i < existing.size(); i++ ){
                if(existing.get(i).getCountry().equals(depCountry)){
                    dest = existing.get(i);
                }
            }
            if(dest == null){
                dest = new DestinationDTO();
                dest.setCity(depCity);
                dest.setCountry(depCountry);
            }
            nFlight.setDestinationStart(dest);
        }
        
        
        if(arrCity != null && arrCountry != null && arrCity.length() > 0 && arrCountry.length() > 0){
            List<DestinationDTO> existing = destinationService.findByCity(arrCity);
            DestinationDTO dest = null;
            for(int i = 0; i < existing.size(); i++ ){
                if(existing.get(i).getCountry().equals(arrCountry)){
                    dest = existing.get(i);
                }
            }
            if(dest == null){
                dest = new DestinationDTO();
                dest.setCity(arrCity);
                dest.setCountry(arrCountry);
            }
            nFlight.setDestinationArrival(dest);
        }
        
        
        PlaneDTO plane = null;
        try{
            Long planeIdL = Long.parseLong(planeId); 
            plane = planeService.get(planeIdL);
            
        }catch(NumberFormatException ex){
            log.warn("Error parsing plane id: "+ex.toString());
        }
        
        nFlight.setPlane(plane);
        
        List<StewardessDTO> nStewards = new ArrayList<StewardessDTO>();
        
        /*
         * Viem ze je to "prasacke" riesenie, ale nativny stripes-ovy checkbox mi nechcel ani za nic fungovat.
         */
        for(int i = 0; i < stewardessService.findAll().size(); i++){
            //log.debug(context.getRequest().getParameter("stewardess"+stewardessService.findAll().get(i).getId()).toString());
            if(context.getRequest().getParameter("stewardess"+stewardessService.findAll().get(i).getId()) != null){
               nStewards.add(stewardessService.findAll().get(i));
            }
        }
        
        nFlight.setStewardess(nStewards);
        
        /*
        if(this.stewardess != null){
            nFlight.setStewardess(this.stewardess);
        }*/
        
        try{
            flightService.create(nFlight);
        }catch(Exception e){
            log.error("Problem with create: "+e);
        }
        
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    }
    
    public Resolution edit(){
        
        String sid = context.getRequest().getParameter("flight_id");
        
        FlightDTO nFlight = null;
        
        Long id = null;
        
        try{
             id = Long.parseLong(sid);
             nFlight = flightService.get(id);
        }catch(Exception ex){
            log.error("Edit flight - parsing flight ID exception");
            nFlight = new FlightDTO();
        }
            
        String errors = "";
        String flightIdentifier;
        String depDateString;
        String arrDateString;
        String depCity;
        String depCountry;
        String arrCity;
        String arrCountry;
        String planeId;
        
         /** GETTING DATA FROM FORM */
        flightIdentifier = context.getRequest().getParameter("edit_flight_identifier");

        depDateString = context.getRequest().getParameter("edit_dep_year")+"-"+context.getRequest().getParameter("edit_dep_month")+"-"+context.getRequest().getParameter("edit_dep_day");
        depDateString += " "+context.getRequest().getParameter("edit_dep_hour")+":"+context.getRequest().getParameter("edit_dep_minute");

        arrDateString = context.getRequest().getParameter("edit_arr_year")+"-"+context.getRequest().getParameter("edit_arr_month")+"-"+context.getRequest().getParameter("edit_arr_day");
        arrDateString += " "+context.getRequest().getParameter("edit_arr_hour")+":"+context.getRequest().getParameter("edit_arr_minute");


        depCity = context.getRequest().getParameter("edit_dep_city");
        depCountry = context.getRequest().getParameter("edit_dep_country");

        arrCity = context.getRequest().getParameter("edit_arr_city");
        arrCountry = context.getRequest().getParameter("edit_arr_country");

        planeId = context.getRequest().getParameter("edit_plane");
        /**-----------------------****/
            
        
        nFlight.setFlightIdentifier(flightIdentifier);
        
        Timestamp depTimestamp = Timestamp.valueOf(depDateString +":00");
       
        Timestamp arrTimestamp = Timestamp.valueOf(arrDateString +":00");
        
        nFlight.setTimeStart((Timestamp)depTimestamp.clone());
        
        
       
        nFlight.setTimeArrival((Timestamp) arrTimestamp.clone());
        
        
        if(depCity != null && depCountry != null && depCity.length() > 0 && depCountry.length() > 0){
            List<DestinationDTO> existing = destinationService.findByCity(depCity);
            DestinationDTO dest = null;
            for(int i = 0; i < existing.size(); i++ ){
                if(existing.get(i).getCountry().equals(depCountry)){
                    dest = existing.get(i);
                }
            }
            if(dest == null){
                dest = new DestinationDTO();
                dest.setCity(depCity);
                dest.setCountry(depCountry);
            }
            nFlight.setDestinationStart(dest);
        }
        
        
        if(arrCity != null && arrCountry != null && arrCity.length() > 0 && arrCountry.length() > 0){
            List<DestinationDTO> existing = destinationService.findByCity(arrCity);
            DestinationDTO dest = null;
            for(int i = 0; i < existing.size(); i++ ){
                if(existing.get(i).getCountry().equals(arrCountry)){
                    dest = existing.get(i);
                }
            }
            if(dest == null){
                dest = new DestinationDTO();
                dest.setCity(arrCity);
                dest.setCountry(arrCountry);
            }
            nFlight.setDestinationArrival(dest);
        }
        
        
        PlaneDTO plane = null;
        try{
            Long planeIdL = Long.parseLong(planeId); 
            plane = planeService.get(planeIdL);
            
        }catch(NumberFormatException ex){
            log.warn("Error parsing plane id: "+ex.toString());
        }
        
        nFlight.setPlane(plane);
        
        try{
            flightService.update(nFlight);
        }catch(Exception e){
            log.error("Problem with update: "+e);
        }
        
        return new RedirectResolution(this.getClass(), "all").addParameter("errors", "");
    
    }
    
    public Resolution show(){
        this.flight = flightService.get(flight.getId());
        return new ForwardResolution("/one-flight.jsp");
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }
    
    public Resolution ajaxDelete(){
        
        log.debug("delete({})", flight.getId());
        try{
            flightService.remove(flight);
        }catch(Exception ex){
           
            return new StreamingResolution("text/html","<h4>Warning!</h4>Delete of flight with ID "+flight.getId()+" was not successfull.");
        }
       
        context.getResponse().setHeader("Delete-Success", "OK");
        return new StreamingResolution("text/html","OK");
      
    }
    
    public List<FlightDTO> getFlights() {
        return flightService.findAll();
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