package org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleLimitAcceleration extends Command {

	boolean shouldLimitAccel = false;
	
    public ToggleLimitAcceleration() {
        requires(Robot.m_driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shouldLimitAccel = !(Robot.m_driveTrain.getLimitAccel());
    	Robot.m_driveTrain.setLimitAccel(shouldLimitAccel);
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

    protected void interrupted() {
    	end();
    }
}
