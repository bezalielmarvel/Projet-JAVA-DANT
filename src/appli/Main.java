package appli;

import lejos.hardware.Button;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("INITIALISING...");
		Robot r = new Robot();
		System.out.println("PRESS ANY BUTTON TO START");
		Button.LEDPattern(4); 
		Button.waitForAnyPress();
		r.start();
	}

}
