// Car object that tracks X-axis position

class Car extends TrafficLightSimulator implements Runnable  {
	private volatile int xPosition;
	private int yPosition;
	private volatile int MPH;  // miles
	private volatile int distance;
	private volatile boolean running = true;
	private volatile boolean paused = false;
	private final Object pauseLock = new Object();
	private final static long createdMillis = System.currentTimeMillis();

	
	public Car(int xPos) {
		xPosition = xPos;
	} 
	// Getter for x axis position
	public synchronized String getX() { 
		return String.valueOf(xPosition);
	}
	// Setters for x axis positions
	synchronized void setX1() {
		GUI.xPos1.setText("    " + String.valueOf(xPosition));
		GUI.yPos1.setText("         " + String.valueOf(yPosition));
	}
	synchronized void setX2() {
		GUI.xPos2.setText("    " + String.valueOf(xPosition));
		GUI.yPos2.setText("         " + String.valueOf(yPosition));
	}
	synchronized void setX3() {
		GUI.xPos3.setText("    " + String.valueOf(xPosition));
		GUI.yPos3.setText("         " + String.valueOf(yPosition));
	}
	// Stop method for red light
	public synchronized void stop() {
		MPH = 0;
		distance = 0;
	}
	// Slow method for yellow light
	public synchronized void slow() { 
		MPH = 5;
		distance = 0;
		distance = (MPH * getSeconds())/100;   // Find out actual formula
		xPosition += distance;	
	}
	// Go method for green lights
	public synchronized void go() {
		MPH = 20;
		distance = 0;
		distance = (MPH * getSeconds())/100;  // Find out actual formula
		xPosition += distance;
	}
	// Count seconds cars are driving
	   public static int getSeconds() {
	        long nowMillis = System.currentTimeMillis();
	        return (int)((nowMillis - createdMillis) / 1000);
	}
	@Override
	public void run() {  // might not work
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
                    if (!running) { 
                        break; 
                    }
                }
            }
		switch (getColor()){
		case RED:
			stop();
			break;
		case YELLOW:
			slow();
			break;
		case GREEN:
			go();
			break;
		}
	}
}
	  public synchronized void pause() {
	        paused = true;
	    }
}

