package capteur;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;


public class ColorSensor {
	
	private EV3ColorSensor colorSensor;
	
	public ColorSensor() {
		Port s1 = LocalEV3.get().getPort("S1");
		colorSensor = new EV3ColorSensor(s1);
	}
	
	
	public int getColorId() {
		return colorSensor.getColorID();
	}

}
