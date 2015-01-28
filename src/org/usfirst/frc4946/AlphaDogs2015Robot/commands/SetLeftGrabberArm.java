package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLeftGrabberArm extends Command {

	public boolean m_leftArmPosition;
	
    public SetLeftGrabberArm(boolean position) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_grabber);
        m_leftArmPosition = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (m_leftArmPosition == true){
    		Robot.m_grabber.setLeftArm(true);
    	}
    	else{
    		Robot.m_grabber.setLeftArm(false);
    	}
    }
    	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
