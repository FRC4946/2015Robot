package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.util.SimplePIController;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngle extends Command {

	public double m_angle = 0.0;
	public double curAngle;
	private Gyro m_gyro;
	
    public RotateToAngle(double newAngle) {
        requires(Robot.m_driveTrain);
    	m_gyro = Robot.m_driveTrain.getGyro();
        //m_angle = newAngle;    	// Uncomment this
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_gyro.reset();
    	Robot.m_driveTrain.m_gyroPIDController.setSetpoint(m_angle);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	curAngle = m_gyro.getAngle() % 360;
    	SmartDashboard.putNumber("Gyro", curAngle);
    	
    	
    	
    	// Uncomment this:
    	
    	//double error = m_angle - m_gyro.getAngle();
    	//double correct = error*-0.1;
    	//if(correct > 0.5) correct = 0.5;
    	//else if(correct < -0.5) correct = -0.5;
    	//Robot.m_driveTrain.autoStrafeDrive(0.0, correct, 0.0);

    	// Comment out this
    	Robot.m_driveTrain.autoStrafeDrive(0.0, Robot.m_driveTrain.m_gyroPIDController.getOutput(), 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_driveTrain.m_gyroPIDController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_driveTrain.autoStrafeDrive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
