package appli;

import capteur.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Robot {
	private UnregulatedMotor motorB, motorC;
	private TouchSensor ts;
	private GyroSensor gs;
	private Ultrasonic us;
	private ColorSensor cs;
	private long chrono;

	public Robot() {
		motorB = new UnregulatedMotor(MotorPort.B);
		motorC = new UnregulatedMotor(MotorPort.C);
		ts = new TouchSensor();
		gs = new GyroSensor(); 
		us = new Ultrasonic();
		cs = new ColorSensor();
		chrono = 0;
	}
	
	public void Forward() {
		motorB.forward();
		motorC.forward();
		motorB.setPower(35);
		motorC.setPower(35);
		Delay.msDelay(10);
	}
	
	public void Backward() {
		motorB.backward();
		motorC.backward();
		motorB.setPower(25);
		motorC.setPower(25);
		Delay.msDelay(500);
	}
	
	public void turnRight() {
        motorB.backward();
        motorC.forward();
        motorB.setPower(50);
        motorC.setPower(50);
        Delay.msDelay(1);
        motorB.stop();
        motorC.stop();
	}
	
	public void turnLeft() {
        motorB.forward();
        motorC.backward();
        motorB.setPower(50);
        motorC.setPower(50);
        Delay.msDelay(1);
        motorB.stop();
        motorC.stop();
	}
	
	public void turn90Left() {
		while(gs.getAngle() < 85) {
			turnLeft();
			if(Button.ESCAPE.isDown()) {
				System.exit(0);
			}
		}
		gs.reset();
	}
	
	public void turn90Right() {
		while(gs.getAngle() > -85) {
			turnRight();
			if(Button.ESCAPE.isDown()) {
				System.exit(0);
			}
		}
		gs.reset();
	}
	
	public void checkCollision() {
		if(ts.getTS() == 1) {
			Backward();
			turn90Left();
		}
	}
	
	public void checkFinish() {
		if(cs.getColorId() == 6) {
			stop();
			System.out.println("SORTIE !!!!\n");
			System.out.println("Temps :" + getChrono()/1000 + " s");
			Sound.beepSequenceUp();
			dance();
			Button.waitForAnyPress();
			System.exit(0);
		}
	}
	
	public void goStraightLine() {
		Forward();
		if(gs.getAngle() > 0) {
			turnRight();
		}
		
		if(gs.getAngle() < 0) {
			turnLeft();
		}
	}
	
	public void closeRobot() {
		this.motorB.close();
		this.motorC.close();
	}
	
	public void stop() {
		this.motorB.stop();
		this.motorC.stop();
	}
	
	public void startChrono() {
		chrono = System.currentTimeMillis();
	}
	
	public long getChrono() {
		long chrono2 = System.currentTimeMillis();	
		return chrono2 - chrono;
	}
	
	public void dance() {
		while(true) {
			Sound.beepSequenceUp();
			
			for(int i = 0; i<5; i++) {
				Forward();
			}
			
			
			for(int i = 0; i<4; i++) {
				motorB.forward();
				motorC.forward();
				motorB.setPower(25);
				motorC.setPower(25);
				Delay.msDelay(500);
				
				motorB.backward();
				motorC.backward();
				motorB.setPower(25);
				motorC.setPower(25);
				Delay.msDelay(500);
				
				Sound.beepSequence();
				
				if(Button.ESCAPE.isDown()) {
					System.exit(0);
				}
			}
			
			
			while(gs.getAngle() > -270) {
				motorB.backward();
		        motorC.forward();
		        motorB.setPower(50);
		        motorC.setPower(50);
		        Delay.msDelay(1);
				if(Button.ESCAPE.isDown()) {
					System.exit(0);
				}
			}
			gs.reset();
			
			
			for(int i = 0; i<10; i++) {
				motorB.forward();
				motorC.stop();
				motorB.setPower(25);
				motorC.setPower(25);
				Delay.msDelay(500);
				//Sound.beep();
				motorC.forward();
				motorB.stop();
				//Sound.beep();
				motorB.setPower(25);
				motorC.setPower(25);
				Delay.msDelay(500);
				Sound.beepSequenceUp();
				if(Button.ESCAPE.isDown()) {
					System.exit(0);
				}
			}
			
			while(gs.getAngle() > -270) {
				motorB.backward();
		        motorC.forward();
		        motorB.setPower(50);
		        motorC.setPower(50);
		        Delay.msDelay(1);
				if(Button.ESCAPE.isDown()) {
					System.exit(0);
				}
			}
			gs.reset();
			
			
			if(Button.ESCAPE.isDown()) {
				System.exit(0);
			}
		}
	}
	
	public void start() {
		gs.reset();
		startChrono();
		while(true) {
			while(us.getDistance() < 0.11) {				
				goStraightLine();
				checkCollision();				
				checkFinish();				
				if(Button.ESCAPE.isDown()) {
					System.exit(0);
				}
			}
			if(us.getDistance() > 0.12) {
				for(int i=0; i<35; i++) {
					goStraightLine();
				}
				stop();
				turn90Right();				
				while(us.getDistance() >= 0.10) {
					goStraightLine();
					checkCollision();					
					checkFinish();					
					if(Button.ESCAPE.isDown()) {
						System.exit(0);
					}
				}
				checkFinish();
			}
			checkFinish();
			if(Button.ESCAPE.isDown()) {
				System.exit(0);
			}
			
		}
	}

}
