package capteur;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class Ultrasonic {
	
	private EV3UltrasonicSensor us;
	private SampleProvider sp;
	private float[] sample;
	
	public Ultrasonic() {
		Port s3 = LocalEV3.get().getPort("S3");
		us = new EV3UltrasonicSensor(s3);
		sp = us.getMode("Distance");
		sample = new float[sp.sampleSize()];
	}
	
	
	public float getDistance() {
		while(true) {
			sp.fetchSample(sample, 0);
			return sample[0];
		}
	}
	
	public void close() {
		us.close();
	}
	
}
