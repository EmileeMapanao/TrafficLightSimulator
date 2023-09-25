import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
/*  Emilee Mapanao
 *  October 7, 2022
 *  Program to simulation 3 cars at 3 traffic lights
 */
public class Main {
	static JButton stop, start, pause, cont, addCar;
	static JLabel car1Label, car2Label, car3Label;
	static volatile TrafficLightSimulator tls;
	static volatile TrafficLightSimulator tls2;
	static volatile TrafficLightSimulator tls3;
	static Car car1;
	static Car car2;
	static Car car3;
	static Thread trafficThread, trafficThread2, trafficThread3, carThread, carThread2, carThread3;
	static int addCarCount = 0;
 
	
	public static void main(String[] args) { 
		// Open GUI and start clock
		GUI gui = new GUI();
		Thread timeThread = new Thread(gui);
		timeThread.start(); 
	
		 // Start intersection 1 simulation
	gui.setButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == start) { 
					start.setEnabled(false);
					car1Label.setText("        Car 1    ");
					tls = new TrafficLightSimulator(TrafficLightColor.GREEN);
					trafficThread = new Thread(tls);
					car1 = new Car(0);
					carThread = new Thread(car1);
					carThread.start();
				 	trafficThread.start(); 
					tls.setColor(); 
			
					System.out.println("Start pressed");
					
				}
			}
		}); 
		// Add cars to simulation
	gui.setButtonActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// If "add car" is pressed once, add traffic simulation 2, else add simulation 3
			if(e.getSource() == addCar) {
				addCarCount++;
				if (addCarCount == 1) {
				car2Label.setText("        Car 2    ");
				tls2 = new TrafficLightSimulator(TrafficLightColor.YELLOW);
				trafficThread2 = new Thread(tls2);
				car2 = new Car(1000);
				carThread2 = new Thread(car2);
				carThread2.start();
				trafficThread2.start();  
				tls2.setColor2();
				System.out.println("Add car pressed");
				}
			else {
				car3Label.setText("        Car 3    ");
				tls3 = new TrafficLightSimulator(TrafficLightColor.RED);
				trafficThread3 = new Thread(tls3);
				car3 = new Car(2000);
				carThread3 = new Thread(car3);
				carThread3.start();
				trafficThread3.start();  
				tls3.setColor3(); 
				}
			}
		}
		 
	});
		// Exit program
	gui.setButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == stop) {
					System.out.println("Stop pressed");
				    System.exit(0);
				}
			}
			 
		});
		// Pause all currently running cars
	gui.setButtonActionListener(new ActionListener() { // not working 
			public void actionPerformed(ActionEvent e) { 
				if(e.getSource() == pause) {
					car1.pause();
					tls.pause();
					if (addCarCount >= 1) {
						car2.pause();
						tls2.pause();
					}
					if (addCarCount == 2) {
						car3.pause();
						tls3.pause();
					}
					System.out.println("Pause pressed");
					}
			}
			
		});
		// Resume all running cars
	gui.setButtonActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cont) {
				car1.resume();
				tls.resume();
				if (addCarCount >= 1) {
					car2.resume();
					tls2.resume();
				}
				if (addCarCount == 2) {
					car3.resume();
					tls3.resume();
				}
				System.out.println("Continue pressed");
				}
		}
		
	});
	}

}
 