import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		
		//Creation of airports
		Airport a1 = new Airport("Orly", "ORY", "Paris", "France");
		Airport a2 = new Airport("Fiumicino", "FCO", "Rome", "Italy");
		Airport a3 = new Airport("Venizelos", "ATH", "Athens", "Greece");
		Airport a4 = new Airport("Macedonia", "SKG", "Thessaloniki", "Greece");
		Airport a5 = new Airport("MunichAirport", "MUC", "Munich", "Germany");
		Airport a6 = new Airport("Charleroi", "CRL", "Brussels", "Belgium");
		
		//Creation of flights
		Flight f1 = new Flight(a1, a2, 120, "Air France");
		Flight f2 = new Flight(a1, a5, 90, "Lufthansa");
		Flight f3 = new Flight(a1, a6, 40, "Air France");
		Flight f4 = new Flight(a4, a5, 130, "EasyJet");
		Flight f5 = new Flight(a3, a5, 150, "Olympic");
		Flight f6 = new Flight(a4, a5, 120, "Aegean");
		Flight f7 = new Flight(a2, a5, 110, "Alitalia");
		Flight f8 = new Flight(a3, a4, 30, "Aegean");
		
		//Addition to Central Registry
		CentralRegistry.addAirport(a1);
		CentralRegistry.addAirport(a2);
		CentralRegistry.addAirport(a3);
		CentralRegistry.addAirport(a4);
		CentralRegistry.addAirport(a5);
		CentralRegistry.addAirport(a6);
		
		CentralRegistry.addFlight(f1);
		CentralRegistry.addFlight(f2);
		CentralRegistry.addFlight(f3);
		CentralRegistry.addFlight(f4);
		CentralRegistry.addFlight(f5);
		CentralRegistry.addFlight(f6);
		CentralRegistry.addFlight(f7);
		CentralRegistry.addFlight(f8);
		
		//Creation of the "Find Airport" Frame
		GUIAirport gui = new GUIAirport();
	}
	
	static class GUIAirport extends JFrame{
		//Δημιουργία υποδοχέα
		private JPanel panel = new JPanel();
		private JPanel panel_airportPage;
		//Δημιουργία γραφικών συστατικών
		private JTextField CityField = new JTextField("Please enter CITY name");
		private JButton find_button = new JButton("Find");
		private JButton VisualizeNet_button = new JButton("Visualize Network");
		
		private JTextField city_to_go = new JTextField(20);
		private JList airlines_list = new JList();
        private JButton find_flights_button = new JButton("Find Flights");
        private ButtonListener listener_find_flights = new ButtonListener();
        
        private JButton Back_2_screen = new JButton("Back To Search Screen");
        private ButtonListener listener_back = new ButtonListener();
		private JTextField direct_text = new JTextField(30);
		private JTextField indirect_text = new JTextField(30);
		
		
		public GUIAirport() {
			//τοποθέτηση γραφικών στον υποδοχέα
			panel.add(CityField);
			panel.add(find_button);
			panel.add(VisualizeNet_button);
			
			//προσαρμογη panel στο παράθυρο
			this.setContentPane(panel);
			
			//Δημιουργία ακροατή
			ButtonListener listener = new ButtonListener();
			ButtonListener listener_visualize_network = new ButtonListener();
			
			//καταχωρηση ακροατη στην πηγη συμβαντων
			find_button.addActionListener(listener);
			
			this.setVisible(true);
			this.setSize(400, 400);
			this.setTitle("Find Airport");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		//Δημιουργία κλάσης ακροατή
		class ButtonListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	 
	        	String cityName = CityField.getText();
	            Airport foundAirport = CentralRegistry.getAirport(cityName);
	            
	            String destinationCityName = city_to_go.getText();
	            Airport destinationAirport = CentralRegistry.getAirport(destinationCityName);
	            if(e.getSource().equals(find_button)) {
	            	// Καλείται όταν πατηθεί το κουμπί "Find"
	            	if (foundAirport != null) {
		                AirportPage(foundAirport);
		            } else {
		                displayMessage1(cityName);
		            }
	            	
	            }
	            if(e.getSource().equals(find_flights_button)) {
	            	//οταν πατηθει το κουμπι find flights
	            	if (destinationAirport != null) {
		                // Εάν οι πόλεις είναι διαφορετικές, εμφάνισε τις πτήσεις
		                if (!foundAirport.getCityName().equals(destinationAirport.getCityName())) {
		                    // Εμφάνιση πληροφοριών πτήσεων
		                	direct_text.setText(CentralRegistry.getDirectFlightsDetails(foundAirport, destinationAirport));
		                	indirect_text.setText(CentralRegistry.getInDirectFlightsDetails(foundAirport, destinationAirport));
		                } else {
		                    // Εμφάνιση μηνύματος σφάλματος 2
		                    displayMessage2("Arrival and departure city cannot be the same!");
		                }
		            } else {
		                // Εμφάνιση μηνύματος σφάλματος 1
		                displayMessage1(destinationCityName);
		            }
	            }
	            if(e.getSource().equals(Back_2_screen)) {
	            	//οταν πατηθει το κουμπι Back to search screen
	            	setContentPane(panel);
	                panel_airportPage.revalidate();
	                panel_airportPage.repaint();
	            }
	            if(e.getSource().equals(VisualizeNet_button)) {
	            	Visualize_Network();
	            }
	     
	        }
	        
	     
	    }
		
		private void AirportPage(Airport airport) {
			panel_airportPage = new JPanel();
			JTextField name_label = new JTextField(airport.getName());
			JTextField code_label = new JTextField(airport.getAirportCodeName());
			JTextField city_label = new JTextField(airport.getCityName());
			JTextField country_label = new JTextField(airport.getCountryName());
			// Λίστα ονομάτων αεροπορικών εταιρειών
	        HashSet<String> airlineNames = new HashSet<>();
	        for (Flight flight : CentralRegistry.getFlights()) {
	            if (flight.getAirportA().equals(airport) || flight.getAirportB().equals(airport)) {
	                airlineNames.add(flight.getAirline());
	            }
	        }

	        ArrayList<String> sortedAirlineNames = new ArrayList<>(airlineNames);
	        Collections.sort(sortedAirlineNames);
	        DefaultListModel airlines_label = new DefaultListModel();
	        for(String airlines: sortedAirlineNames) {
	        	airlines_label.addElement(airlines);
	        }
	        airlines_list.setModel(airlines_label);

	        
	        
	        panel_airportPage.add(name_label);
	        panel_airportPage.add(code_label);
	        panel_airportPage.add(city_label);
	        panel_airportPage.add(country_label);
	        panel_airportPage.add(airlines_list);
	        
	        this.setContentPane(panel_airportPage);
	        this.setVisible(true);
	        this.setSize(400, 400);
	        this.setTitle("Airport Page");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        panel_airportPage.add(city_to_go);
	        panel_airportPage.add(find_flights_button);
	        find_flights_button.addActionListener(listener_find_flights);
	        
	        panel_airportPage.add(direct_text);
	        panel_airportPage.add(indirect_text);
	        
	        panel_airportPage.add(Back_2_screen);
	        Back_2_screen.addActionListener(listener_back);
	        
	        
		}
		private void displayMessage1(String message) {
	        JOptionPane.showMessageDialog(this, message + " does not have an airport");
	    }
		
		private void displayMessage2(String message) {
			JOptionPane.showMessageDialog(this, message);
		}
		
		private void Visualize_Network() {
			
		}
		
		

		
	}
	

}