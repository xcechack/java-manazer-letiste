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
        
        Plane airbus = new Plane();
        airbus.setProducer("Airbus");
        airbus.setType("A380");
        airbus.setNumberSeats(800);
        airbus.setMaxStewardessNumber(12);
       
        Destination bratislava = new Destination();
        bratislava.setCountry("Slovakia");
        bratislava.setCity("Bratislava");

        Destination kosice = new Destination();
        kosice.setCountry("Slovakia");
        kosice.setCity("Kosice");
        
        
        Stewardess st1 = new Stewardess();
        st1.setName("Jozin");
        st1.setSurname("Bazin");
        st1.setSex(Sex.male);
        Stewardess st2 = new Stewardess();
        st2.setName("Fero");
        st2.setSurname("Taraba");
        st2.setSex(Sex.male);
        
        List<Stewardess> stewards = new ArrayList<Stewardess>();
        stewards.add(st1);
        stewards.add(st2);
        
        
        PlaneService pService = (PlaneService) context.getBean(PlaneService.class);
        DestinationService dService = (DestinationService) context.getBean(DestinationService.class);
        FlightService fService = (FlightService) context.getBean(FlightService.class);
        
        //Create destinations
        //dService.create(bratislava);
        //dService.create(kosice);
        
        pService.create(airbus);
        //Create flgiht
        Flight ar123 = new Flight();
        ar123.setDestinationStart(bratislava);
        ar123.setDestinationArrival(kosice);
        ar123.setPlane(airbus);
        
        ar123.setTimeArrival(new Timestamp(1234556000));
        ar123.setTimeArrival(new Timestamp(1238890000));
        
        ar123.setFlightIdentifier("AR123");
        
        ar123.setStewardess(stewards);
        
   
        fService.create(ar123);
        
        List<Flight> fList = fService.findAll();
        Flight flight;
        for(int i = 0; i < fList.size(); i++){
            flight = fList.get(i);
            List<Stewardess> stewardess = flight.getStewardess();
            System.out.println("-----------------");
            System.out.println(flight.getFlightIdentifier());
            System.out.print("Stewardess: ");
            for(int j = 0; j < stewardess.size(); j++){
                System.out.print(stewardess.get(j).getSurname()+", ");
            }
            System.out.println();
            System.out.println("Plane: "+flight.getPlane().getProducer()+" "+flight.getPlane().getType());
            System.out.println("Departing from: "+flight.getDestinationStart().getCity()+", "+flight.getDestinationStart().getCountry());
            System.out.println("Arriving to: "+flight.getDestinationArrival().getCity()+", "+flight.getDestinationArrival().getCountry());
            System.out.println("-----------------");
        }
       
    }
}

