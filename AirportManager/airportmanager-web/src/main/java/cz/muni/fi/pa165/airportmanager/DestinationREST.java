package cz.muni.fi.pa165.airportmanager;

import com.sun.jersey.spi.inject.Inject;
import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.DestinationServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Inject
    protected DestinationService destinationService;
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
            destinationService.create(destination);
        }catch(DAOException e){
            log.error("Create destination error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    
    @OPTIONS
    @Path("create_destination")
    public void createDestination(@Context HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        response.setHeader("Access-Control-Max-Age", "17000");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    
    
    @GET
    @Path("getid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DestinationDTO get(@PathParam("id") String id,
                    @Context HttpServletResponse response) throws IOException{
        Long lid = Long.parseLong(id);
        if(lid == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        DestinationDTO destination = null;
        
        try{
            destination = destinationService.get(lid);
        }catch(DAOException e){
            log.error("Get destination by id error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(destination == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"id_not_found");
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return destination;
    }
    
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
            destinationService.update(destination);
        }catch(Exception e){
            log.error("Update destination error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    
    @OPTIONS
    @Path("update_destination")
    public void updateDestination(@Context HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        response.setHeader("Access-Control-Max-Age", "17000");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    
    @DELETE
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void remove(DestinationDTO destination,
                       @Context HttpServletResponse response) throws IOException{
        if(destination == null || destination.getId() == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try{
            destinationService.remove(destination);
        }catch(Exception e){
            log.error("Update destination error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    
    @OPTIONS
    @Path("delete_destination")
    public void deleteDestination(@Context HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        response.setHeader("Access-Control-Max-Age", "17000");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    
    @GET
    @Path ("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DestinationDTO> findAll(@Context HttpServletResponse response)throws IOException{
        List<DestinationDTO> list = null;
        try{
            list = destinationService.findAll();
        }catch(Exception e){
            log.error("Find all destinations error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(list == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return list;
    }
    
    
    @GET
    @Path("country/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DestinationDTO> findByCountry(@PathParam("country") String country,
                                           @Context HttpServletResponse response){
        List<DestinationDTO> list = null;
        try{
            list = destinationService.findByCountry(country);
        }catch(Exception e){
            log.error("Find by country error: " + e);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return list;
    }
    
    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DestinationDTO> findByCity(@PathParam("city") String city,
                                           @Context HttpServletResponse response){
        List<DestinationDTO> list = null;
        try{
            list = destinationService.findByCity(city);
        }catch(Exception e){
            log.error("Find by city error: " + e);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return list;
    }
}