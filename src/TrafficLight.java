import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
	// A simulation of a traffic light that uses 
	// an enumeration to describe the light's color.  
	  
	// An enumeration of the colors of a traffic light. 
	enum TrafficLightColor {   
	  RED, GREEN, YELLOW 
	} 

	// A computerized traffic light. 
	class TrafficLightSimulator implements Runnable {  
	  private volatile TrafficLightColor tlc; // holds the current traffic light color 
	  private volatile boolean running = true;
	  private volatile boolean paused = false;
	  private final Object pauseLock = new Object();
	  private boolean changed = false; // true when the light has changed
	 
	  TrafficLightSimulator(TrafficLightColor init) {  
	    tlc = init;
	  } 
	 
	  TrafficLightSimulator() {    
	    tlc = TrafficLightColor.RED; 
	  } 
	
	  // Start up the light. 
	  @Override
	  public void run() { 
		  while (running) {
	            synchronized (pauseLock) {
	                if (!running) {
	                    break;
	                }
	                if (paused) { 
	                    try {
	                        pauseLock.wait(); // will cause this Thread to block until 
	                        // another thread calls pauseLock.notifyAll()
	                    } catch (InterruptedException ex) {
	                        break;
	                    }
	                    if (!running) { // running might have changed since we paused
	                        break; 
	                    }
	                }
	            }
	      try {  
	        switch(tlc) { 
	          case GREEN:
	        	 Thread.sleep(10000); // green for 10 seconds
	            break;  
	          case YELLOW:
	        	  Thread.sleep(3000);  // yellow for 3 seconds
	            break; 
	          case RED:
	              Thread.sleep(12000); // red for 12 seconds
	              break; 
	        } 
	      } catch(InterruptedException exc) { 
	       System.out.println(exc); 
	      } 
	      changeColor();  
	      System.out.println(Thread.currentThread().getName());
	    }  
	  } 
	  // Change color. 
	  synchronized void changeColor() { 
	    switch(tlc) { 
	      case RED: 
	        tlc = TrafficLightColor.GREEN; 
	        break; 
	      case YELLOW:  
	        tlc = TrafficLightColor.RED; 
	        break; 
	      case GREEN: 
	       tlc = TrafficLightColor.YELLOW; 
	    } 
	    changed = true;
	    notify(); // signal that the light has changed  
	  } 
	  
	  // Run intersection 1
	 synchronized void setColor() {
		  ActionListener task = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	        	  if (!paused) {
	        	  GUI.light1Color.setText(tlc.toString());
	        	  switch(tlc) {
	        	  	case GREEN:
	        	  		GUI.light1Color.setForeground(Color.GREEN);
	        	  		Main.car1.go();
	        	  		Main.car1.setX1();
	        	  		break;
	        	  	case YELLOW:
	        	  		GUI.light1Color.setForeground(Color.YELLOW);
	        	  		Main.car1.slow();
	        	  		Main.car1.setX1();
	        	  		break;
	        	  	case RED:
	        	  		GUI.light1Color.setForeground(Color.RED);
	        	  		Main.car1.stop();
	        	  		Main.car1.setX1();
	        	  		break;
	        	  }
	        	  }
	          } 
	      };
	      
	      new Timer(1000, task).start();	
		 
	}  
	 // Run intersection 2
	 synchronized void setColor2() {
		  ActionListener task = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	        	  GUI.light2Color.setText(tlc.toString());
	        	  if (!paused) {
	         	  switch(tlc) {
	        	  	case GREEN:
	        	  		GUI.light2Color.setForeground(Color.GREEN);
	        	  		Main.car2.go();
	        	  		Main.car2.setX2();
	        	  		break;
	        	  	case YELLOW:
	        	  		GUI.light2Color.setForeground(Color.YELLOW);
	        	  		Main.car2.slow();
	        	  		Main.car2.setX2();
	        	  		break;
	        	  	case RED:
	        	  		GUI.light2Color.setForeground(Color.RED);
	        	  		Main.car2.stop();
	        	  		Main.car2.setX2();
	        	  		break;
	        	  }
	        	  }
	          } 
	      };
	      
	      new Timer(1000, task).start();	
	}  
	 // Run intersection 3
	 synchronized void setColor3() {
		  ActionListener task = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	        	  GUI.light3Color.setText(tlc.toString());
	        	  if (!paused) {
	         	  switch(tlc) {
	        	  	case GREEN:
	        	  		GUI.light3Color.setForeground(Color.GREEN);
	        	  		Main.car3.go();
	        	  		Main.car3.setX3();
	        	  		break;
	        	  	case YELLOW:
	        	  		GUI.light3Color.setForeground(Color.YELLOW);
	        	  		Main.car3.slow();
	        	  		Main.car3.setX3();
	        	  		break;
	        	  	case RED:
	        	  		GUI.light3Color.setForeground(Color.RED);
	        	  		Main.car3.stop();
	        	  		Main.car3.setX3();
	        	  		break;
	        	  }
	        	  }
	          } 
	      };
	      
	      new Timer(1000, task).start();	
	}  

	  // Return current color. 
  synchronized TrafficLightColor getColor() { 
	    return tlc;    
	 }  
	
	  public synchronized void pause() {
	        paused = true;
	 }
	  public synchronized void resume() {
	        synchronized (pauseLock) {
	            paused = false;
	            pauseLock.notifyAll(); // Unblocks thread
	        }
	  }
}
	 
	
