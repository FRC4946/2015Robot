package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetRightGrabberArm extends Command {

	public int m_rightArmPosition;
	
    public SetRightGrabberArm(int position) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_grabber);
        m_rightArmPosition = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (m_rightArmPosition == 0){
    		Robot.m_grabber.setRightArm(0);
    	}
    	else if (m_rightArmPosition == 1){
    		Robot.m_grabber.setRightArm(1);
    	}
    	else if (m_rightArmPosition == 2){
    		Robot.m_grabber.setRightArm(2);
    	}
    	else if (m_rightArmPosition == 3){
    		Robot.m_grabber.setRightArm(3);
    	}
    	else{
    		Robot.m_grabber.setRightArm(0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
