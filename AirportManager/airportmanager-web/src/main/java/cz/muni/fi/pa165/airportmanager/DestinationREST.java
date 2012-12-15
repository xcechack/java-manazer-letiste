package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.DestinationServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vasa
 */

@Path("rest/destination/")
public class DestinationREST {
    
    private DestinationService service = new DestinationServiceImpl();
    final static Logger log = LoggerFactory.getLogger(DestinationREST.class);
    
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(DestinationDTO destination,
                       @Context HttpServletResponse response) throws IOException{
        if(destination == null || destination.getCity().isEmpty() || destination.getCountry().isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try{
            service.create(destination);
        }catch(DAOException e){
            log.error("Create destination error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    
    @GET
    @Path("getid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DestinationDTO get(int id,
                    @Context HttpServletResponse response) throws IOException{
        Long lid = new Long(id);
        if(lid == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        DestinationDTO destination = null;
        
        try{
            destination = service.get(lid);
        }catch(DAOException e){
            log.error("Get destination by id error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(destination == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"id_not_found");
        }
        return destination;
    }
    
    /**
     * Update existing destination
     * @param destinationDTO destination we want to update
     * @return nothing
     * @throws NullPointerException when given destination is null.
     */
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void update(DestinationDTO destination,
                       @Context HttpServletResponse response) throws IOException
    {
        if(destination == null || destination.getCity().isEmpty() || destination.getCountry().isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        try{
            service.update(destination);
        }catch(DAOException e){
            log.error("Update destination error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    /**
     * Delete existing destination
     * @param destinationDTO destination we want to delete
     * @return nothing
     * @throws NullPointerException when given destination is null.
     */
    /*@DELETE
    void remove(DestinationDTO destinationDTO);*/
    
    /**
     * Retrieve all destinations
     * @param nothing
     * @return List with all destinations.
     */
    @GET
    @Path ("all")
    @Produces(MediaType.APPLICATION_JSON)
    public void findAll(@Context HttpServletResponse response)throws IOException{
        List<DestinationDTO> list = null;
        try{
            list = service.findAll();
        }catch(Exception e){
            log.error("Find all destinations error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(list == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),list);
    }
    
    /**
     * Retrieves all destinations with given country name. 
     * @param country Destination's country parameter.
     * @return List of destinations with given country.
     * @throws NullPointerException when given country is null.
     */
    @GET
    @Path("country/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public void findByCountry(String country){
        //List<DestinationDTO>
    }
    
    /**
     * Retrievec all destinations with given city name.
     * @param city Destination's city name.
     * @return List of destinations with given city.
     * @throws NullPointerException when given city is null.
     */
    /*@GET
    @Path("city/{city}")*/
    //List<DestinationDTO> findByCity(String city);
}
