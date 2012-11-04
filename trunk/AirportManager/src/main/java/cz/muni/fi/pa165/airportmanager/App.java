package cz.muni.fi.pa165.airportmanager;


import cz.muni.fi.pa165.airportmanager.enums.Sex;
import cz.muni.fi.pa165.airportmanager.services.DestinationService;
import cz.muni.fi.pa165.airportmanager.services.FlightService;
import cz.muni.fi.pa165.airportmanager.services.PlaneService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        PlaneDTO airbus = new PlaneDTO();
        airbus.setProducer("Airbus");
        airbus.setType("A380");
        airbus.setNumberSeats(800);
        airbus.setMaxStewardessNumber(12);
       
        DestinationDTO bratislava = new DestinationDTO();
        bratislava.setCountry("Slovakia");
        bratislava.setCity("Bratislava");

        DestinationDTO kosice = new DestinationDTO();
        kosice.setCountry("Slovakia");
        kosice.setCity("Kosice");
        
        
        StewardessDTO st1 = new StewardessDTO();
        st1.setName("Jozin");
        st1.setSurname("Bazin");
        st1.setSex(Sex.male);
        StewardessDTO st2 = new StewardessDTO();
        st2.setName("Fero");
        st2.setSurname("Taraba");
        st2.setSex(Sex.male);
        
        List<StewardessDTO> stewards = new ArrayList<StewardessDTO>();
        stewards.add(st1);
        stewards.add(st2);
        
        
        PlaneService pService = (PlaneService) context.getBean(PlaneService.class);
        DestinationService dService = (DestinationService) context.getBean(DestinationService.class);
        FlightService fService = (FlightService) context.getBean(FlightService.class);
        
        //Create destinations
        //dService.create(bratislava);
        //dService.create(kosice);
        
        //pService.create(airbus);
        //Create flgiht
        FlightDTO ar123 = new FlightDTO();
        ar123.setDestinationStart(bratislava);
        ar123.setDestinationArrival(kosice);
        ar123.setPlane(airbus);
        
        ar123.setTimeArrival(new Timestamp(1234556000));
        ar123.setTimeArrival(new Timestamp(1238890000));
        
        ar123.setFlightIdentifier("AR123");
        
        ar123.setStewardess(stewards);
        
   
        fService.create(ar123);
        
        List<FlightDTO> fList = fService.findAll();
        Flight flight;
      
        
        
        for(int i = 0; i < fList.size(); i++){
           // flight = fList.get(i);
            
            FlightDTO fDto = fList.get(i); //conv.flightToFlightDto(flight);
            
            System.out.println("===================================");
            
            
            
            
            System.out.println("============   DTO:  ==============");
            System.out.println(fDto);
            //System.out.println("============   Entity:  ===========");
           // System.out.println(flight);
            
            fDto.getDestinationStart().setCity("Prague");
            fDto.getDestinationStart().setCountry("Czech republic");
            
            dService.update(fDto.getDestinationStart());
            
            System.out.println("============   Changed:  ==========");
            FlightDTO fl2 = fService.get(fDto.getId());
            System.out.println(fl2);
            
            System.out.println("===================================");
            
          
        }
        
        
        
       
    }
}

