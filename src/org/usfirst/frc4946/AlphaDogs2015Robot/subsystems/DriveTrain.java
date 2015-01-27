// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {
    SpeedController m_leftDriveMotors = RobotMap.driveTrainLeftMotors;
    SpeedController m_rightDriveMotors = RobotMap.driveTrainRightMotors;
    RobotDrive m_robotDrive = RobotMap.driveTrainRobotDrive;
    SpeedController m_strafeMotor = RobotMap.driveTrainStrafeMotor;
    DoubleSolenoid m_gearShifterSolenoid = RobotMap.driveTrainGearShifterSolenoid;
    DoubleSolenoid m_wheelDropperSolenoid = RobotMap.driveTrainWheelDropperSolenoid;
    Gyro m_gyro = RobotMap.driveTrainGyro;
    Encoder m_leftEncoder = RobotMap.driveTrainLeftEncoder;
    Encoder m_rightEncoder = RobotMap.driveTrainRightEncoder;
    Encoder m_strafeEncoder = RobotMap.driveTrainStrafeEncoder;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {	
		setDefaultCommand(new DriveWithJoystickArcade());
    }
    
    
	/**
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		m_robotDrive.tankDrive(left, right);
	}

	/**
	 * Arcade style driving for the DriveTrain. 
	 * @param joy The ps3 style joystick to use to drive arcade style.
	 */
	public void drive(Joystick joy) {
		m_robotDrive.arcadeDrive(joy);
	}
}

