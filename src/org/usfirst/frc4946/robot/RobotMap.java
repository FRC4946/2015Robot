// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4946.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Matthew
 */
public class RobotMap {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static SpeedController driveTrainLeftDriveMotor;
	public static SpeedController driveTrainRightDriveMotor;
	public static RobotDrive driveTrainRobotDrive;
	public static SpeedController driveTrainStrafeMotor;
	public static DoubleSolenoid driveTrainGearShifterSolenoid;
	public static DoubleSolenoid driveTrainWheelDropperSolenoid;
	public static Gyro driveTrainGyro;
	public static Encoder driveTrainLeftEncoder;
	public static Encoder driveTrainRightEncoder;
	public static Encoder driveTrainStrafeEncoder;
	public static DoubleSolenoid grabberLeftArmSolenoid;
	public static DoubleSolenoid grabberRightArmSolenoid;
	public static Compressor airCompressorCompressor;
	public static SpeedController elevatorElevatorMotor;
	public static AnalogPotentiometer elevatorAnalogPotentiometer;
	public static DigitalInput elevatorBottomLimitSwitch;
	public static DigitalInput elevatorTopLimitSwitch;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static void init() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		
		
		
		// =*=*=*= Drive Train SubSystem =*=*=*=
		
		driveTrainLeftDriveMotor = new CANTalon(0);
		LiveWindow.addActuator("Drive Train", "Left Drive Motor", (LiveWindowSendable) driveTrainLeftDriveMotor);

		driveTrainRightDriveMotor = new CANTalon(1);
		LiveWindow.addActuator("Drive Train", "Right Drive Motor", (LiveWindowSendable) driveTrainRightDriveMotor);

		driveTrainRobotDrive = new RobotDrive(driveTrainLeftDriveMotor, driveTrainRightDriveMotor);
		driveTrainRobotDrive.setSafetyEnabled(true);
		driveTrainRobotDrive.setExpiration(0.1);
		driveTrainRobotDrive.setSensitivity(0.5);
		driveTrainRobotDrive.setMaxOutput(1.0);

		driveTrainStrafeMotor = new VictorSP(0);
		LiveWindow.addActuator("Drive Train", "Strafe Motor", (LiveWindowSendable) driveTrainStrafeMotor);

		driveTrainGearShifterSolenoid = new DoubleSolenoid(0, 0, 1);
		LiveWindow.addActuator("Drive Train", "Gear Shifter Solenoid", driveTrainGearShifterSolenoid);

		driveTrainWheelDropperSolenoid = new DoubleSolenoid(0, 2, 3);
		LiveWindow.addActuator("Drive Train", "Wheel Dropper Solenoid", driveTrainWheelDropperSolenoid);

		driveTrainGyro = new Gyro(1);
		LiveWindow.addSensor("Drive Train", "Gyro", driveTrainGyro);
		driveTrainGyro.setSensitivity(0.007);
		
		driveTrainLeftEncoder = new Encoder(2, 3, false, EncodingType.k4X);
		LiveWindow.addSensor("Drive Train", "Left Encoder", driveTrainLeftEncoder);
		driveTrainLeftEncoder.setDistancePerPulse(1.0);
		driveTrainLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		
		driveTrainRightEncoder = new Encoder(4, 5, false, EncodingType.k4X);
		LiveWindow.addSensor("Drive Train", "Right Encoder", driveTrainRightEncoder);
		driveTrainRightEncoder.setDistancePerPulse(1.0);
		driveTrainRightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		
		driveTrainStrafeEncoder = new Encoder(6, 7, false, EncodingType.k4X);
		LiveWindow.addSensor("Drive Train", "Strafe Encoder", driveTrainStrafeEncoder);
		driveTrainStrafeEncoder.setDistancePerPulse(1.0);
		driveTrainStrafeEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		
		
		
		// =*=*=*= Grabber SubSystem =*=*=*=
		
		grabberLeftArmSolenoid = new DoubleSolenoid(0, 4, 5);
		LiveWindow.addActuator("Grabber", "Left Arm Solenoid", grabberLeftArmSolenoid);

		grabberRightArmSolenoid = new DoubleSolenoid(0, 6, 7);
		LiveWindow.addActuator("Grabber", "Right Arm Solenoid", grabberRightArmSolenoid);

		
		
		// =*=*=*= Air Compressor SubSystem =*=*=*=

		airCompressorCompressor = new Compressor(0);


		
		// =*=*=*= Elevator SubSystem =*=*=*=

		elevatorElevatorMotor = new CANTalon(2);
		LiveWindow.addActuator("Elevator", "Elevator Motor", (LiveWindowSendable) elevatorElevatorMotor);

		elevatorAnalogPotentiometer = new AnalogPotentiometer(0, 1.0, 0.0);
		LiveWindow.addSensor("Elevator", "Analog Potentiometer", elevatorAnalogPotentiometer);

		elevatorBottomLimitSwitch = new DigitalInput(0);
		LiveWindow.addSensor("Elevator", "Bottom Limit Switch", elevatorBottomLimitSwitch);

		elevatorTopLimitSwitch = new DigitalInput(1);
		LiveWindow.addSensor("Elevator", "Top Limit Switch", elevatorTopLimitSwitch);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}
}