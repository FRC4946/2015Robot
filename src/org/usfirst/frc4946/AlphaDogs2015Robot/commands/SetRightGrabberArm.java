package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Right arm command
 */
public class SetRightGrabberArm extends Command {

	public int m_rightArmPosition;
	
    public SetRightGrabberArm(int position) {
        requires(Robot.m_grabber);
        m_rightArmPosition = position;
    }

    /**Dictates what position the right arm will be in
     * Position 0 is neutral
     * Position 1 is 2 inches
     * Position 2 is 4 inches
     * Position 3 is 6 inches 
     */
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

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
