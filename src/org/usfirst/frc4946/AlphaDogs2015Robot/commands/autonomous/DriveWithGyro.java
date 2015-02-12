package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithGyro extends Command {

	private double m_driveSpeed = 0.0;
	private double m_strafeSpeed = 0.0;
	private double m_gyroCorrect = 0.0;
	private double kP = 0.1;
	private Gyro m_gyro;


	
    public DriveWithGyro(double driveSpeed, double strafeSpeed) {
        requires(Robot.m_driveTrain);
        requires(Robot.m_elevator);
        
        m_driveSpeed = driveSpeed;
        m_strafeSpeed = strafeSpeed;
    	m_gyro = Robot.m_driveTrain.getGyro();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_gyro.reset();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_gyroCorrect = kP * m_gyro.getAngle();

    	Robot.m_driveTrain.autoStrafeDrive(m_driveSpeed, m_gyroCorrect, m_strafeSpeed);
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
