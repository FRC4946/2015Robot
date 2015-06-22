package org.usfirst.frc4946.AlphaDogs2015Robot;
    
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		
    public static SpeedController driveTrainLeftFrontDriveMotor;
    public static SpeedController driveTrainLeftRearDriveMotor;
    public static SpeedController driveTrainRightFrontDriveMotor;
    public static SpeedController driveTrainRightRearDriveMotor;
    public static SpeedController driveTrainStrafeMotor;
    public static RobotDrive driveTrainRobotDrive;
    public static DoubleSolenoid driveTrainGearShifterSolenoid;
    public static DoubleSolenoid driveTrainWheelDropperSolenoid;

    public static Gyro driveTrainGyro;
    public static Encoder driveTrainLeftEncoder;
    public static Encoder driveTrainRightEncoder;
    public static Encoder driveTrainStrafeEncoder;
    
    //public static Solenoid feederSolenoid;
    //public static SpeedController feederLeftMotor;
    //public static SpeedController feederRightMotor;
    
    public static Solenoid grabberLeftArmSolenoid;
    public static Solenoid grabberSmallRightArmSolenoid;
    public static Solenoid grabberBigRightArmSolenoid;
    
    
    public static Compressor airCompressorCompressor;
    
    
    public static SpeedController elevatorElevatorMotor;
    public static AnalogPotentiometer elevatorAnalogPotentiometer;
    public static DigitalInput elevatorBottomLimitSwitch;
    public static DigitalInput elevatorTopLimitSwitch;
    public static DigitalInput driveTrainFrontLimitSwitch;
    
    public static void init() {
    	
        // Compressor
        airCompressorCompressor = new Compressor(0);
    	
        
    	// Speed Controllers
        driveTrainLeftFrontDriveMotor =		new CANTalon(0);
        driveTrainLeftRearDriveMotor =		new CANTalon(1);
        driveTrainRightFrontDriveMotor =	new CANTalon(2);
        driveTrainRightRearDriveMotor =		new CANTalon(3);
        driveTrainStrafeMotor =				new CANTalon(4);
        elevatorElevatorMotor =				new CANTalon(5);
        //LiveWindow.addActuator("Elevator", "Elevator Motor", (LiveWindowSendable) elevatorElevatorMotor);
        //feederLeftMotor =					new VictorSP(0);
        //feederRightMotor =				new VictorSP(1);

        
        // Robot Drive
        driveTrainRobotDrive = new RobotDrive(driveTrainLeftFrontDriveMotor, driveTrainLeftRearDriveMotor, driveTrainRightFrontDriveMotor, driveTrainRightRearDriveMotor);
        driveTrainRobotDrive.setSafetyEnabled(true);
        driveTrainRobotDrive.setExpiration(0.1);
        driveTrainRobotDrive.setSensitivity(0.5);
        driveTrainRobotDrive.setMaxOutput(1.0);
        
        
        // Solenoids
        grabberLeftArmSolenoid = new Solenoid(4);
        grabberSmallRightArmSolenoid = new Solenoid(5);
        grabberBigRightArmSolenoid = new Solenoid(6);
        driveTrainWheelDropperSolenoid = new DoubleSolenoid(1, 3);
        driveTrainGearShifterSolenoid = new DoubleSolenoid(0, 2);
        //feederSolenoid = new Solenoid(7);
        LiveWindow.addActuator("Grabber", "Left Arm Solenoid", grabberLeftArmSolenoid);
        LiveWindow.addActuator("Grabber", "Small Right Arm Solenoid", grabberSmallRightArmSolenoid);
        LiveWindow.addActuator("Grabber", "Large Right Arm Solenoid", grabberBigRightArmSolenoid);
        LiveWindow.addActuator("Drive Train", "Wheel Dropper Solenoid", driveTrainWheelDropperSolenoid);
        LiveWindow.addActuator("Drive Train", "Gear Shifter Solenoid", driveTrainGearShifterSolenoid);
        
        
        // Sensors
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
        LiveWindow.addSensor("Drive Train", "Right Encoder", driveTrainRightEncoder);
        driveTrainStrafeEncoder.setDistancePerPulse(1.0);
        driveTrainStrafeEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        
        elevatorAnalogPotentiometer = new AnalogPotentiometer(0, 85.27, 4);
        LiveWindow.addSensor("Elevator", "Analog Potentiometer", elevatorAnalogPotentiometer);
        
        elevatorBottomLimitSwitch = new DigitalInput(0);
        LiveWindow.addSensor("Elevator", "Bottom Limit Switch", elevatorBottomLimitSwitch);
        
        elevatorTopLimitSwitch = new DigitalInput(1);
        LiveWindow.addSensor("Elevator", "Top Limit Switch", elevatorTopLimitSwitch);
        
        driveTrainFrontLimitSwitch = new DigitalInput(8);
        LiveWindow.addSensor("Drive Train", "Front Limit Switch", driveTrainFrontLimitSwitch);
    }
}
