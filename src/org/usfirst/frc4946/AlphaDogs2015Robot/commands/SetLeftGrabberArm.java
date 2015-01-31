package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Left arm command
 */
public class SetLeftGrabberArm extends Command {

	public boolean m_leftArmExtended;
	
    public SetLeftGrabberArm(boolean position) {
        requires(Robot.m_grabber);
        m_leftArmExtended = position;
    }
/**
 * Dictates whether right arm is initialized
 */
    protected void initialize() {
    	if (m_leftArmExtended == true){
    		Robot.m_grabber.setLeftArm(true);
    	}
    	else {
    		Robot.m_grabber.setLeftArm(false);
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
