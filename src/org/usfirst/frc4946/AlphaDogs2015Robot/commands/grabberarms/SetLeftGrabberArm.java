package org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms;

import org.usfirst.frc4946.AlphaDogs2015Robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Left arm command
 */
public class SetLeftGrabberArm extends Command {

	public boolean m_leftArmExtended = true;
	
    public SetLeftGrabberArm(boolean position) {
        requires(Robot.m_leftGrabber);
        m_leftArmExtended = position;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (m_leftArmExtended == true){
    		Robot.m_leftGrabber.setLeftArm(true);
    	}
    	else {
    		Robot.m_leftGrabber.setLeftArm(false);
    	}
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
