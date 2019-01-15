package capteur;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
	
	private EV3GyroSensor gyroSensor;
	private SampleProvider gyroProvider;
	private float[] gyroSample;	
	
	public GyroSensor() {		
		Port s4 = LocalEV3.get().getPort("S4");
		gyroSensor = new EV3GyroSensor(s4);
		gyroProvider = gyroSensor.getAngleMode();
		gyroSample = new float[gyroProvider.sampleSize()];
	}
	
	public int getAngle() {
		while(true) {
			gyroProvider.fetchSample(gyroSample, 0);
			return (int)gyroSample[0];
		}
	}
	
	public void reset() {
		gyroSensor.reset();
	}

}
