package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.enums.Sex;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirportPU");
        //EntityManager em1 = emf.createEntityManager(); //ctx1
        
        /*
        Person testPerson = new Person();
        testPerson.setFirstName("Jozinko");
        testPerson.setLastName("Z Bazin");*/
        
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
        
        Flight ar123 = new Flight();
        ar123.setDestinationStart(bratislava);
        ar123.setDestinationArrival(kosice);
        ar123.setPlane(airbus);
        
        ar123.setTimeArrival(new Timestamp(1234556000));
        ar123.setTimeArrival(new Timestamp(1238890000));
        
        ar123.setFlightIdentifier("AR123");
        
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
        
        ar123.setStewardess(stewards);
        
        
        FlightDAO fDAO = new FlightDAOImpl();
        fDAO.setEntityManager(emf);
        DestinationDAO dDAO = new DestinationDAOImpl();
        dDAO.setEntityManagerFactory(emf);
        StewardessDAOImpl sDAO = new StewardessDAOImpl();
        sDAO.setEntityManagerFactory(emf);
        PlaneDAO pDAO = new PlaneDAOImpl();
        pDAO.setEntityManagerFactory(emf);
        pDAO.create(airbus);
        dDAO.create(bratislava);
        dDAO.create(kosice);
        sDAO.create(st1);
        sDAO.create(st2);
        fDAO.create(ar123);
        
        List<Flight> fList = fDAO.findAll();
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

