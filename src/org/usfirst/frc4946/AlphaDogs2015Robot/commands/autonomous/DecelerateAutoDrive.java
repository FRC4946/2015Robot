package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class DecelerateAutoDrive extends Command {

	private double m_maxDriveSpeed = 0.0;
	private double m_curDriveSpeed = 0.0;
	private double m_driveAccelerationPerCycle = 0.0;
//	private double m_driveDecelerationPerCycle = 0.0;

	private double m_maxStrafeSpeed = 0.0;
	private double m_curStrafeSpeed = 0.0;
	private double m_strafeAccelerationPerCycle = 0.0;
//	private double m_strafeDecelerationPerCycle = 0.0;

	private double m_maxTurnSpeed = 0.0;
	private double m_curTurnSpeed = 0.0;
	private double m_turnAccelerationPerCycle = 0.0;
//	private double m_turnDecelerationPerCycle = 0.0;

	private double m_accelTime = 0.0;
	private double m_decelTime = 0.0;
	private double m_timeout = 0.0;
	private int m_elapsedTime = 0;
	

	
    public DecelerateAutoDrive(double driveSpeed, double strafeSpeed, double turnSpeed, double accelTime, double decelTime, double timeout) {
        requires(Robot.m_driveTrain);
        requires(Robot.m_elevator);
        
        // Change all values to number of cycles instead of number of seconds 
        m_accelTime = Math.abs(accelTime*50);
        m_decelTime = Math.abs(decelTime*50);
        m_timeout = timeout;
        
        m_maxDriveSpeed = driveSpeed;
        
        if (m_accelTime <= 0) {
        	m_accelTime = 0.001;
        }
        
        if (m_decelTime <= 0) {
        	m_decelTime = 0.001;
        }
        
        m_driveAccelerationPerCycle = (m_maxDriveSpeed/accelTime);
//        m_driveDecelerationPerCycle = (m_maxDriveSpeed/decelTime);
        
        m_maxStrafeSpeed = strafeSpeed;
        m_strafeAccelerationPerCycle = (m_maxStrafeSpeed/accelTime);
//        m_strafeDecelerationPerCycle = (m_maxStrafeSpeed/decelTime);
        
        m_maxTurnSpeed = turnSpeed;
        m_turnAccelerationPerCycle = (m_maxTurnSpeed/accelTime);
//        m_turnDecelerationPerCycle = (m_maxTurnSpeed/decelTime);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_elapsedTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_elapsedTime ++;
    	
    	// If we're accelerating
    	if(m_elapsedTime < m_accelTime){
    		
    			if (Math.abs(m_curDriveSpeed) <= Math.abs(m_maxDriveSpeed)) {
    				m_curDriveSpeed += m_driveAccelerationPerCycle;
    			}
    			
    			if (Math.abs(m_curStrafeSpeed) <= Math.abs(m_maxStrafeSpeed)) {
    				m_curStrafeSpeed += m_strafeAccelerationPerCycle;
    			}
    			
    			if (Math.abs(m_curTurnSpeed) <= Math.abs(m_maxTurnSpeed)) {
    				m_curTurnSpeed += m_turnAccelerationPerCycle;
    			}
    			
    	}		
    	// If we're decelerating
    	else if ((m_timeout - m_elapsedTime) > m_decelTime) {
    			
    			if(Math.abs(m_curDriveSpeed) > 0) {
    				m_curDriveSpeed -= m_driveAccelerationPerCycle;
    			}
    			
    			if(Math.abs(m_curStrafeSpeed) > 0) {
    				m_curStrafeSpeed -= m_strafeAccelerationPerCycle;
    			}
    			
    			if(Math.abs(m_curTurnSpeed)  > 0) {
    				m_curTurnSpeed -= m_turnAccelerationPerCycle;
    			}
    			
    	}		
    			
    			
    	Robot.m_driveTrain.autoStrafeDrive(m_curDriveSpeed, m_curTurnSpeed, m_curStrafeSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (m_elapsedTime >= m_timeout);
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
