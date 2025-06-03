public class Flight {

	private Airport airport1,airport2;

	private int duration;

	private String airline;



	public Flight (Airport aName1, Airport aName2, int aDuration ,String aAirline) {

		duration=aDuration;

		airline=aAirline;

		airport1=aName1;

		airport2=aName2;



	

	}

	public Airport getAirportB() {

	

		return airport2;

	}

	public Airport getAirportA() {

		return airport1;

	}

	public int getDuration() {

		return duration;

	}

	public  String getAirline(){

		return airline;

	}
	
	@Override
    public String toString() {
        return "Flight operated by " + airline + ", duration " + duration + " minutes";
    }
}