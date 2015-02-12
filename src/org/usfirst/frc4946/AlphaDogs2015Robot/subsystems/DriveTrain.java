package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ActuateStrafeSolenoid;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.CounterBase.EncodingType;
//import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * @author Matthew, Ashish
 */
public class DriveTrain extends Subsystem {
    RobotDrive m_robotDrive = RobotMap.driveTrainRobotDrive;
    SpeedController m_strafeMotor = RobotMap.driveTrainStrafeMotor;
    DoubleSolenoid m_gearShifterSolenoid = RobotMap.driveTrainGearShifterSolenoid;
    DoubleSolenoid m_wheelDropperSolenoid = RobotMap.driveTrainWheelDropperSolenoid;
	Gyro m_gyro = RobotMap.driveTrainGyro;
	Encoder m_leftEncoder = RobotMap.driveTrainLeftEncoder;
	Encoder m_rightEncoder = RobotMap.driveTrainRightEncoder;
	Encoder m_strafeEncoder = RobotMap.driveTrainStrafeEncoder;

	private boolean isStrafeMode;
    DigitalInput m_frontLimitSwitch = RobotMap.driveTrainFrontLimitSwitch;
    
    /* Encoder (presumably) pulses 1000 times per revolution
	 * Wheel's circumference is (6.0 * pi) inches
	 * 1 encoder pulse is (0.006 * pi) inches
	 * */
	double kDistancePerPulse = 0.006 * Math.PI;
	
    //save the initial position of the gyroscope
    double gyroInitialPosition;

    
    
    public void initDefaultCommand() {	
		setDefaultCommand(new DriveWithJoystick());
		
		m_leftEncoder.setDistancePerPulse(kDistancePerPulse);
		m_rightEncoder.setDistancePerPulse(kDistancePerPulse);
		m_strafeEncoder.setDistancePerPulse(kDistancePerPulse);
		
		//save the initial position of the gyroscope
		//double gyroscopeInitialPosition;

    }
    
    /**
     * Set the default command to DriveWithJoystickStrafe
     */
    public void setDriveStrafe() {
    	isStrafeMode = true;
    }
    
    /**
     * Set the default command to DriveWithJoystickArcade
     */
    public void setDriveArcade() {
    	isStrafeMode = false;
    }
    

	/**
	 * Arcade style driving for the DriveTrain. 
	 * @param moveValue Speed in range [-1,1]
	 * @param rotateValue Rotation in range [-1,1]
	 */
	public void drive(double moveValue, double rotateValue) {
		
		// Get the throttle value from the drive joystick
        double driveSpeed = Robot.m_oi.getDriveJoystick().getThrottle();
        driveSpeed *= -1;                        //Flip range from (1, -1) to (-1, 1)
        driveSpeed = (driveSpeed + 1) / 2;    // Shift to (0,1)
        
        //Scale motor speed based off of the drive joystick throttle
        moveValue = moveValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
        rotateValue = (rotateValue * (0.7 + 0.3 * driveSpeed)); // 0.7 to 1.0
        
     
        
        m_strafeMotor.set(0.0);
		m_robotDrive.arcadeDrive(moveValue, rotateValue);	
	}

	
	/**
	 * Strafe driving for the DriveTrain. 
	 * @param moveValue Speed in range [-1,1]
	 * @param rotateValue Rotation in range [-1,1]
	 * @param strafeValue Strafe speed in range [-1,1]
	 */
	public void strafeDrive(double moveValue, double rotateValue, double strafeValue) {
		
    	
		// Limit the value of the strafe wheel from [-1.0, 1.0]
		if (strafeValue > 1.0) {
			strafeValue = 1.0;
        }
        if (strafeValue < -1.0) {
        	strafeValue = -1.0;
        }        
        
        // Get the throttle value from the drive joystick
        double driveSpeed = Robot.m_oi.getDriveJoystick().getThrottle();
        driveSpeed *= -1;                        //Flip range from (1, -1) to (-1, 1)
        driveSpeed = (driveSpeed + 1) / 2;    // Shift to (0,1)

        //Scale motor speed based off of the drive joystick throttle
        moveValue = moveValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
        rotateValue = rotateValue * (0.3 + 0.2 * driveSpeed); // 0.3 to 0.5
        strafeValue = strafeValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
        
        // Manually square inputs to add precision near middle of Joystick for strafing
    	if (strafeValue >= 0) {
    		strafeValue = strafeValue * strafeValue;
    	} else {
    		strafeValue = -strafeValue * strafeValue;  // Keeps negative value if strafe movement was negative
    	}
    	
    	if (moveValue >= 0.75) {
    		new ActuateStrafeSolenoid(true);
    	}
		
    	// Set the motors to the desired speed
		m_robotDrive.arcadeDrive(moveValue, rotateValue);
		m_strafeMotor.set(strafeValue);
	}
	
	
	
	/**
	 * Strafe driving for the DriveTrain. 
	 * @param moveValue Speed in range [-1,1]
	 * @param rotateValue Rotation in range [-1,1]
	 * @param strafeValue Strafe speed in range [-1,1]
	 */
	public void autoStrafeDrive(double moveValue, double rotateValue, double strafeValue) {
		
    	// Set the motors to the desired speed
		m_robotDrive.arcadeDrive(moveValue, rotateValue);
		m_strafeMotor.set(strafeValue);
	}
	
	/**
	 * Arcade style driving for the DriveTrain. 
	 * @param joy The joystick to use to drive arcade style.
	 */
	public void drive(Joystick joy) {
		m_robotDrive.arcadeDrive(joy);
	}
	
	
	/**
     * Shift the gearbox into either high or low gear
	 * 
	 * @param isHigh Whether to shift into high gear or not. 0 disables the solenoid, 1 shifts to low gear, 2 shifts to high gear.
	 */
	public void setGear(int isHigh) {

		if (isHigh == 1) {
			m_gearShifterSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (isHigh == 2) {
			m_gearShifterSolenoid.set(DoubleSolenoid.Value.kReverse);
		} else {
			m_gearShifterSolenoid.set(DoubleSolenoid.Value.kOff);
		}
		
	}
	
	
	/**
     * Lower or raise the middle wheel.
	 * 
	 * @param isRaised Whether to raise the wheel or not. 0 disables the solenoid, 1 lowers the wheel, 2 raises it.
	 */
	public void setDropWheel(int isRaised) {

		if (isRaised == 1) {
			m_wheelDropperSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (isRaised == 2) {
			m_wheelDropperSolenoid.set(DoubleSolenoid.Value.kReverse);
		} else {
			m_wheelDropperSolenoid.set(DoubleSolenoid.Value.kOff);
		}
	}

	/**
     * Get the Left Encoder
     * 
     * @return The lesft encoder
     */
	public Encoder getLeftEncoder() {
		return m_leftEncoder;
	}
	
	/**
     * Get the Right Encoder
     * 
     * @return The right encoder
     */
	public Encoder getRightEncoder() {
		return m_rightEncoder;
	}

	/**
     * Get the Strafe Encoder
     * 
     * @return The strafe encoder
     */
	public Encoder getStrafeEncoder() {
		return m_strafeEncoder;
	}
	
	/**
     * Get the Gyro
     * 
     * @return The gyro
     */
	public Gyro getGyro() {
		return m_gyro;
		
	}
	
	/**
     * Get the state of the front limit switch
     * 
     * @return Whether or not the switch is pressed
     */
	public boolean getLimitSwitchState() {
		return m_frontLimitSwitch.get();
		
	}
	
	public boolean getDriveMode(){
		return isStrafeMode;
	}
}
