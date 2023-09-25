import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.Timer;


public class GUI extends Main implements Runnable {
	static JLabel time;
	static volatile JLabel light1Color, light2Color, light3Color;
    static String currentTime;
    static volatile JLabel xPos1, xPos2, xPos3, yPos1, yPos2, yPos3;


	// Display GUI of traffic simulation 
	public GUI () { 
	JFrame frame = new JFrame("Traffic Simulator");
	JPanel timePanel = new JPanel(); // Holds time
	JPanel simPanel = new JPanel(); // Holds simulator
	JPanel buttonPanel = new JPanel(); // Holds buttons
	JPanel labelPanel = new JPanel(); // labels
	GridLayout frameLayout = new GridLayout(4, 1);
	GridLayout simLayout = new GridLayout(3, 7);
	GridLayout buttonLayout = new GridLayout(2, 2);
	FlowLayout labelLayout = new FlowLayout();
	
	JLabel blank1 = new JLabel("           ");
	JLabel blank2 = new JLabel("           ");
	JLabel blank3 = new JLabel("           ");
	JLabel intersection = new JLabel("Intersection");
	JLabel car = new JLabel("Car");
	JLabel x = new JLabel("X Position");
	JLabel y = new JLabel("Y Position");
	time = new JLabel();
	light1Color = new JLabel();
	light2Color = new JLabel();
	light3Color = new JLabel(); 
	car1Label = new JLabel();
	car2Label = new JLabel();
	car3Label = new JLabel();
	xPos1 = new JLabel();
	xPos2 = new JLabel();
	xPos3 = new JLabel();
	yPos1 = new JLabel();
	yPos2 = new JLabel();
	yPos3 = new JLabel();
	stop = new JButton("Stop");
	start = new JButton("Start");
	pause = new JButton("Pause"); 
	cont = new JButton("Continue");
	addCar = new JButton("Add car");
	
	timePanel.add(time);
	
	labelPanel.add(intersection);
	labelPanel.add(blank1);
	simPanel.add(light1Color);
	
	labelPanel.add(car);
	simPanel.add(car1Label);
	labelPanel.add(blank2);
	
	labelPanel.add(x);
	simPanel.add(xPos1);
	simPanel.add(yPos1);
	labelPanel.add(blank3);
	labelPanel.add(y);
	labelPanel.setLayout(labelLayout);
	
	simPanel.add(light2Color);
	simPanel.add(car2Label);
	simPanel.add(xPos2);
	simPanel.add(yPos2);
	
	simPanel.add(light3Color);
	simPanel.add(car3Label);
	simPanel.add(xPos3);
	simPanel.add(yPos3);
	
	simPanel.setLayout(simLayout);
	
	buttonPanel.add(start);
	buttonPanel.add(stop);
	buttonPanel.add(addCar);
	buttonPanel.add(pause);
	buttonPanel.add(cont); 
	
	buttonPanel.setLayout(buttonLayout);
	
	frame.add(timePanel);
	frame.add(labelPanel); 
	frame.add(simPanel);
	frame.add(buttonPanel);
	 
	frame.setSize(400, 400);
	frame.setLayout(frameLayout);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	frame.repaint();  
	}
	
	public void setButtonActionListener(ActionListener e) {
		stop.addActionListener(e);
		start.addActionListener(e);
		pause.addActionListener(e);
		cont.addActionListener(e);
		addCar.addActionListener(e);
	}
	// Display time, update every second
	@Override
	public void run() {
	      ActionListener task = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	              currentTime = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
	              time.setText("Current time: " + currentTime);
	          } 
	      };
	      System.out.println(Thread.currentThread().getName());
	      new Timer(1000, task).start();	
	} 
}
