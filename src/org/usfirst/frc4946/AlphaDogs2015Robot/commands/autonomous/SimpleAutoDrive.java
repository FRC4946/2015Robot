package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleAutoDrive extends Command {

	private double m_driveSpeed = 0.0;
	private double m_strafeSpeed = 0.0;
	private double m_turnSpeed = 0.0;

	
    public SimpleAutoDrive(double driveSpeed, double strafeSpeed, double turnSpeed) {
        requires(Robot.m_driveTrain);
        requires(Robot.m_elevator);
        
        m_driveSpeed = driveSpeed;
        m_strafeSpeed = strafeSpeed;
        m_turnSpeed = turnSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_driveTrain.autoStrafeDrive(m_driveSpeed, m_turnSpeed, m_strafeSpeed);
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
    	end();
    }
}
