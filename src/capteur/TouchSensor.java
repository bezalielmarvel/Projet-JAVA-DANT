package capteur;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class TouchSensor {
	
	private EV3TouchSensor touchSensor;
	private SampleProvider touchProvider;
	private float[] touchSample;
	
	public TouchSensor() {
		Port s2 = LocalEV3.get().getPort("S2");
		touchSensor = new EV3TouchSensor(s2);		
		touchProvider = touchSensor.getTouchMode();
		touchSample = new float[touchProvider.sampleSize()];		
	}
	
	public int getTS() {
		while(true) {
			touchProvider.fetchSample(touchSample, 0);
			return (int)touchSample[0];
		}
	}
	
	
	public void closeTS() {
		this.touchSensor.close();
	}

}
