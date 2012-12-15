/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.exceptions.DAOException;
import cz.muni.fi.pa165.airportmanager.services.PlaneService;
import cz.muni.fi.pa165.airportmanager.services.PlaneServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vasa
 */
@Path("rest/plane/")
public class PlaneREST{
    
    private PlaneService service = new PlaneServiceImpl();
    final static Logger log = LoggerFactory.getLogger(DestinationREST.class);
    
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(PlaneDTO plane,
                       @Context HttpServletResponse response) throws IOException{
        if(plane == null || plane.getProducer().isEmpty() || plane.getType().isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try{
            service.create(plane);
        }catch(Exception e){
            log.error("Create plane error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    @GET
    @Path("getid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlaneDTO get(@PathParam("id") String id,
                    @Context HttpServletResponse response) throws IOException{
        Long lid = Long.parseLong(id);
        if(lid == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        PlaneDTO plane = null;
        
        try{
            plane = service.get(lid);
        }catch(DAOException e){
            log.error("Get plane by id error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(plane == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"id_not_found");
        }
        return plane;
    }
    
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void update(PlaneDTO plane,
                       @Context HttpServletResponse response) throws IOException
    {
        if(plane == null || plane.getProducer().isEmpty() || plane.getType().isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        try{
            service.update(plane);
        }catch(DAOException e){
            log.error("Update plane error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
    }
    @DELETE
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void remove(PlaneDTO plane,
                       @Context HttpServletResponse response) throws IOException{
        if(plane == null || plane.getId() == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try{
            service.remove(plane);
        }catch(Exception e){
            log.error("Update plane error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @GET
    @Path ("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaneDTO> findAll(@Context HttpServletResponse response)throws IOException{
        List<PlaneDTO> list = null;
        try{
            list = service.findAll();
        }catch(Exception e){
            log.error("Find all planes error: " + e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(list == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return list;
    }
    
    
    @GET
    @Path("producer/{producer}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaneDTO> findByProducer(@PathParam("producer") String producer){
        List<PlaneDTO> list = null;
        try{
            list = service.findByProducer(producer);
        }catch(Exception e){
            log.error("Find by producer error: " + e);
        }
        return list;
    }
    
    @GET
    @Path("type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaneDTO> findByCity(@PathParam("type") String type){
        List<PlaneDTO> list = null;
        try{
            list = service.findByType(type);
        }catch(Exception e){
            log.error("Find by type error: " + e);
        }
        return list;
    }
    
    @GET
    @Path("seats/{seats}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaneDTO> findByMaxNumberOfSeats(@PathParam("seats") String seats){
        List<PlaneDTO> list = null;
        int nseats = Integer.parseInt(seats);
        try{
            list = service.findByMaxNumberOfSeats(nseats);
        }catch(Exception e){
            log.error("Find by max number of seats error: " + e);
        }
        return list;
    }
    
    @GET
    @Path("greaterseats/{seats}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaneDTO> findPlaneWithGreaterNumberOfSeats(@PathParam("seats") String seats){
        List<PlaneDTO> list = null;
        int nseats = Integer.parseInt(seats);
        try{
            list = service.findByMaxNumberOfSeats(nseats);
        }catch(Exception e){
            log.error("Find by plane withgreater number of seats error: " + e);
        }
        return list;
    }
}
