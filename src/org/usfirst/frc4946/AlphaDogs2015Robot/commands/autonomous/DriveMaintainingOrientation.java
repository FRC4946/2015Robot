package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4946.AlphaDogs2015Robot.*;

/**
 *
 * @author Matthew
 */
public class DriveMaintainingOrientation extends Command {

	private double m_driveSpeed,
					m_driveTargetDistance,
					m_driveTravelledDistance,
					m_strafeSpeed,
					m_strafeTargetDistance,
					m_strafeTravelledDistance,
					m_gyroCorrect= 0.0;
	
	/**
	 * Drive the robot laterally and whateverTheWordForPerpendicularToLateralIs for a specified distance in each direction and at a specific speed.
	 * @param driveSpeed The max speed at which to drive the robot in a whateverTheWordForPerpendicularToLateralIs direction.
	 * @param driveDistance
	 * @param strafeSpeed
	 * @param strafeDistance
	 */
    public DriveMaintainingOrientation(double driveSpeed, double driveDistance, double strafeSpeed, double strafeDistance) {
        requires(Robot.m_driveTrain);
        m_driveSpeed = driveSpeed;
        m_driveTargetDistance = driveDistance;
        m_strafeSpeed = strafeSpeed;
        m_strafeTargetDistance = strafeDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: Keep track of the travelled distance using encoders.
    	//Robot.m_driveTrain.getLeftEncoder;
    	
    	if(m_driveTravelledDistance >= m_driveTargetDistance){
    		m_driveSpeed = 0.0;
    	}
    	if(m_strafeTravelledDistance >= m_strafeTargetDistance){
    		m_strafeSpeed = 0.0;
    	}
    	
    	//TODO: A PID controller (Maybe just P?) to correct any drift. Uses the gyro.
    	//m_gyroCorrect = kP * Robot.m_driveTrain.getGyroAngle();
    	
    	//Maybe we should replace this with a PID version? To allow for gentle accel and deccel?
    	Robot.m_driveTrain.strafeDrive(m_driveSpeed, m_gyroCorrect, m_strafeSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if(m_driveTravelledDistance >= m_driveTargetDistance &&
    			m_strafeTravelledDistance >= m_strafeTargetDistance){
            return true;
    	}
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
