package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4946.AlphaDogs2015Robot.*;

/**
 *
 */
public class SetGrabberPosition extends Command {

	public int m_armPosition;
	
    public SetGrabberPosition(int position) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_grabber);
        m_armPosition = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    	/* Pos 0: Left in, Right 0
    	   Pos 1: Left in, Right 1
           Pos 2: Left in, Right 2
           Pos 3: Left in, Right 3
           
           Pos 4: Left out, Right 0
           Pos 5: Left out, Right 1
           Pos 6: Left out, Right 2
           Pos 7: Left out, Right 3 */
    	
    	if (m_armPosition == 0){
    		Robot.m_grabber.setLeftArm(false);
    		Robot.m_grabber.setRightArm(0);
    	}
    	else if (m_armPosition == 1){
    		Robot.m_grabber.setLeftArm(false);
    		Robot.m_grabber.setRightArm(1);
    	}
    	else if (m_armPosition == 2){
    		Robot.m_grabber.setLeftArm(false);
    		Robot.m_grabber.setRightArm(2);
    	}
    	else if (m_armPosition == 3){
    		Robot.m_grabber.setLeftArm(false);
    		Robot.m_grabber.setRightArm(3);
    	}
    	else if (m_armPosition == 4){
    		Robot.m_grabber.setLeftArm(true);
    		Robot.m_grabber.setRightArm(0);
    	}
    	else if (m_armPosition == 5){
    		Robot.m_grabber.setLeftArm(true);
    		Robot.m_grabber.setRightArm(1);
    	}
    	else if (m_armPosition == 6){
    		Robot.m_grabber.setLeftArm(true);
    		Robot.m_grabber.setRightArm(2);
    	}
    	else if (m_armPosition == 7){
    		Robot.m_grabber.setLeftArm(true);
    		Robot.m_grabber.setRightArm(3);
    	}
    	else{
    		Robot.m_grabber.setLeftArm(false);
    		Robot.m_grabber.setRightArm(0);
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
