package org.usfirst.frc4946.AlphaDogs2015Robot;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import org.usfirst.frc4946.AlphaDogs2015Robot.util.*;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//public static SpeedController driveTrainLeftMotors;
	//public static SpeedController driveTrainRightMotors;
	
    public static SpeedController driveTrainLeftFrontDriveMotor;
    //public static SpeedController driveTrainLeftMiddleDriveMotor;
    public static SpeedController driveTrainLeftRearDriveMotor;

    public static SpeedController driveTrainRightFrontDriveMotor;
    //public static SpeedController driveTrainRightMiddleDriveMotor;
    public static SpeedController driveTrainRightRearDriveMotor;
    
    public static RobotDrive driveTrainRobotDrive;
    public static SpeedController driveTrainStrafeMotor;
    public static DoubleSolenoid driveTrainGearShifterSolenoid;
    public static DoubleSolenoid driveTrainWheelDropperSolenoid;
    public static Gyro driveTrainGyro;
    public static Encoder driveTrainLeftEncoder;
    public static Encoder driveTrainRightEncoder;
    public static Encoder driveTrainStrafeEncoder;
    
    public static Solenoid feederSolenoid;
    public static SpeedController feederLeftMotor;
    public static SpeedController feederRightMotor;
    
    public static Solenoid grabberLeftArmSolenoid;
    public static Solenoid grabberSmallRightArmSolenoid;
    public static Solenoid grabberBigRightArmSolenoid;
    public static Compressor airCompressorCompressor;
    public static SpeedController elevatorElevatorMotor;
    public static AnalogPotentiometer elevatorAnalogPotentiometer;
    public static DigitalInput elevatorBottomLimitSwitch;
    public static DigitalInput elevatorTopLimitSwitch;
    
    public static void init() {
        driveTrainLeftFrontDriveMotor = new CANTalon(0);
        //driveTrainLeftMiddleDriveMotor = new CANTalon(1);
        driveTrainLeftRearDriveMotor = new CANTalon(1);
        //driveTrainLeftMotors = new MultiSpeedController(driveTrainLeftFrontDriveMotor,
        //												driveTrainLeftMiddleDriveMotor,
        //												driveTrainLeftRearDriveMotor);
        
        driveTrainRightFrontDriveMotor = new CANTalon(2);
        //driveTrainRightMiddleDriveMotor = new CANTalon(4);
        driveTrainRightRearDriveMotor = new CANTalon(3);
        //driveTrainRightMotors = new MultiSpeedController(driveTrainRightFrontDriveMotor,
		//												driveTrainRightMiddleDriveMotor,
		//												driveTrainRightRearDriveMotor);
        
        driveTrainRobotDrive = new RobotDrive(driveTrainLeftFrontDriveMotor, driveTrainLeftRearDriveMotor, driveTrainRightFrontDriveMotor, driveTrainRightRearDriveMotor);
        
        
        driveTrainRobotDrive.setSafetyEnabled(true);
        driveTrainRobotDrive.setExpiration(0.1);
        driveTrainRobotDrive.setSensitivity(0.5);
        driveTrainRobotDrive.setMaxOutput(1.0);
        

        driveTrainStrafeMotor = new CANTalon(4);
        
        driveTrainGearShifterSolenoid = new DoubleSolenoid(4, 5);
        driveTrainWheelDropperSolenoid = new DoubleSolenoid(2, 3);
        LiveWindow.addActuator("Drive Train", "Gear Shifter Solenoid", driveTrainGearShifterSolenoid);
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
        
        
        
        
        
        
        
        //grabberLeftArmSolenoid = new DoubleSolenoid(0, 4, 5);      
        //LiveWindow.addActuator("Grabber", "Left Arm Solenoid", grabberLeftArmSolenoid);
        //grabberRightArmSolenoid = new DoubleSolenoid(0, 6, 7);      
        //LiveWindow.addActuator("Grabber", "Right Arm Solenoid", grabberRightArmSolenoid);
        
        
        
        
        feederSolenoid = new Solenoid(7);
        feederLeftMotor = new VictorSP(0);
        feederRightMotor = new VictorSP(1);
        
        
        
        
        
        airCompressorCompressor = new Compressor(0);

        
        
        
        
        elevatorElevatorMotor = new CANTalon(8);
        
        elevatorAnalogPotentiometer = new AnalogPotentiometer(0, 1.0, 0.0);
        LiveWindow.addSensor("Elevator", "Analog Potentiometer", elevatorAnalogPotentiometer);
        
        elevatorBottomLimitSwitch = new DigitalInput(0);
        LiveWindow.addSensor("Elevator", "Bottom Limit Switch", elevatorBottomLimitSwitch);
        
        elevatorTopLimitSwitch = new DigitalInput(1);
        LiveWindow.addSensor("Elevator", "Top Limit Switch", elevatorTopLimitSwitch);
        
    }
}
