// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4946.robot.subsystems;

import org.usfirst.frc4946.robot.RobotMap;
import org.usfirst.frc4946.robot.commands.*;
import org.usfirst.frc4946.robot.commands.drivetrain.DriveWithJoystickArcade;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	SpeedController m_leftDriveMotor = RobotMap.driveTrainLeftDriveMotor;
	SpeedController m_rightDriveMotor = RobotMap.driveTrainRightDriveMotor;
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
     * Drive the robot using tank style
     * @param left Power to apply to left motors
     * @param right Power to apply to right motors
     */
    public void tankDrive(double left, double right) {
        m_robotDrive.tankDrive(left, right);
    }
    
    /**
     * Drive the robot using arcade style
     * @param x Forward and backwards speed
     * @param y Rotation speed 
     */
    public void arcadeDrive(double x, double y) {
        m_robotDrive.arcadeDrive(x, y);
    }
    
    /**
     * Disable motor safety, stops 'Robot drive not updated enough...' error
     * @param value 'True' to enable safety and 'False' to disable
     */
    public void toggleSafety(boolean value) {
        m_robotDrive.setSafetyEnabled(value);
    }
}