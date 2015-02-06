package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        requires(Robot.m_driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	boolean isStrafeMode = Robot.m_driveTrain.getDriveMode();
    	
    	double Yval = Robot.m_oi.getDriveJoystick().getRawAxis(1);
    	double Xval = Robot.m_oi.getDriveJoystick().getRawAxis(0);
    	double Zval = Robot.m_oi.getDriveJoystick().getRawAxis(2);
    	
    	if(isStrafeMode){
    		Robot.m_driveTrain.drive(-Yval, Xval);
    	} else{
        	Robot.m_driveTrain.strafeDrive(-Yval, -Zval, -Xval);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_driveTrain.strafeDrive(0.0, 0.0, 0.0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_driveTrain.strafeDrive(0.0, 0.0, 0.0);
    }
}
