
import java.util.ArrayList;



public class CentralRegistry {

	

	private static ArrayList <Airport> aAirport  =new ArrayList<>();
	private static ArrayList <Flight> bFlight =new ArrayList<>();

	

	public static void addAirport(Airport airportName) {

	        aAirport.add(airportName);

	}

	public static void addFlight(Flight flightName) {

			bFlight.add(flightName);

	}

	

	public static ArrayList<Flight>getFlights() {

		return bFlight;

		

		

	}

	public static ArrayList<Airport>getAirports() {

		return aAirport;

	}

	public static Airport getLargestHub() {

		int  maxFlight=0;

		Airport maxAirport=null;

		int c;

		

		for (Airport airport :aAirport) {

			c=0;

			for (Flight flight:bFlight) {

				if (airport==flight.getAirportA()||airport==flight.getAirportB())

					c++;

			}

			if (c>maxFlight) {

				maxFlight=c;

				maxAirport=airport;

			}

		}

		return maxAirport;

	}

	public static Flight getLongestFlight() {

		int longestF= bFlight.get(0).getDuration();

		int i=0;

		int j=-1;

		for (Flight flight:bFlight) {

			j++;

			if (flight.getDuration()>longestF) {

				i=j;	

			}

			

	}

	return bFlight.get(i);

	}
	
	public static Airport getAirport(String cityName) {
        for (Airport airport : aAirport) {
            if (airport.getCityName().equals(cityName)) {
                return airport;
            }
        }
        return null; // Επιστρέφει null αν δεν βρεί αεροδρόμιο με την καταχωρημένη πόλη
    }
	
	public static String getDirectFlightsDetails(Airport a, Airport b) {
        StringBuilder result = new StringBuilder("DIRECT FLIGHTS DETAILS\n");

        int flightNumber = 1;
        for (Flight flight : bFlight) {
            if ((flight.getAirportA().equals(a) && flight.getAirportB().equals(b)) ||
                (flight.getAirportA().equals(b) && flight.getAirportB().equals(a))) {
                result.append("[").append(flightNumber).append("] ").append(flight.toString()).append("\n");
                flightNumber++;
            }
        }

        return result.toString();
    }
	
	public static String getInDirectFlightsDetails(Airport a, Airport b) {
        StringBuilder result = new StringBuilder("INDIRECT FLIGHTS through...\n");

        int flightNumber = 1;
        for (Airport intermediateAirport : aAirport) {
            if (!intermediateAirport.equals(a) && !intermediateAirport.equals(b)) {
                // Εάν το ενδιάμεσο αεροδρόμιο δεν είναι ούτε το αεροδρόμιο 'a' ούτε το 'b'
                result.append("[").append(flightNumber).append("]").append(intermediateAirport.getCityName())
                      .append(", ").append(intermediateAirport.getAirportCodeName()).append(" airport\n");
                flightNumber++;
            }
        }

        return result.toString();
    }

}