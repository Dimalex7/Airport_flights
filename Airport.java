import java.util.ArrayList;

public class Airport {

	private String airportName;
	private String airportCodeName;
	private String cityName;
	private String countryName;
	private ArrayList <Flight> aFlight =new ArrayList<>();

	 

	public Airport (String airportName, String airportCodeName, String cityName, String countryName) {
	this.airportName = airportName;
	this.airportCodeName= airportCodeName;
	this.cityName = cityName;
	this.countryName = countryName;
	}

	

	

	public boolean isDirectlyConnectedTo(Airport anAirport) {

		ArrayList<Flight>flights=CentralRegistry.getFlights();

		for (Flight flight:flights) {
			if (anAirport==flight.getAirportA()&&this ==flight.getAirportB()) {
				return true;
			}
			else if (anAirport==flight.getAirportB()&&this==flight.getAirportA()) {
				return true;
			}
		
		}
		return false;

		

	}



	

	public String getName() {

		return airportName;

	}

	public String getCityName() {

		return cityName;

	}
	
	public String getAirportCodeName() {
		return airportCodeName;
	}
	
	public String getCountryName() {
		return countryName;
	}



	public void printCompanies() {

		ArrayList<Flight>flights=CentralRegistry.getFlights();

		for (Flight flight:flights) {

			if (this==flight.getAirportA()||this ==flight.getAirportB()) {

				String Company = flight.getAirline();

				System.out.println(Company);

			}

		}

		

	}





	public boolean isInDirectlyConnectedTo(Airport anAirport) {

		ArrayList<Flight>flights=CentralRegistry.getFlights();

		

		for (Flight flight:flights) {

			if (this.isDirectlyConnectedTo(anAirport)){

				return false;

			}

		    if (this==flight.getAirportA()) {

		    	if (anAirport.isDirectlyConnectedTo(flight.getAirportB())) {

		    		return true;

		    	}

		    }

		    if (this ==flight.getAirportB()) {

		    	if (anAirport.isDirectlyConnectedTo(flight.getAirportA())) {

		    		return true;

		    	}

		    }

		}

			

			return false;

		

	}





	public ArrayList<Airport> getCommonConnections(Airport anAirport) {

		ArrayList<Flight>flights=CentralRegistry.getFlights();
		ArrayList<Airport>listt = new ArrayList<>();

		for (Flight flight:flights) {

			

			if (this.isInDirectlyConnectedTo(anAirport)) {

				if (flight.getAirportA()==this) {

					if(flight.getAirportB().isDirectlyConnectedTo(anAirport)) {

						listt.add(flight.getAirportB());

					}

					

					}

				else if (flight.getAirportB()==this) {

					if (flight.getAirportA().isDirectlyConnectedTo(anAirport)){

						listt.add(flight.getAirportA());

					}

					

				}

			}

				

			}

		return listt;

	}





	



}