package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc4946.AlphaDogs2015Robot.*;

/**
 *
 * @author Matthew, Ashish
 */
public class DriveMaintainingOrientation extends Command {

	private double m_driveSpeed,
					m_maxDriveSpeed,
					m_driveTargetDistance,
					m_driveTravelledDistance,
					m_strafeSpeed,
					m_maxStrafeSpeed,
					m_strafeTargetDistance,
					m_strafeTravelledDistance,
					m_gyroCorrect,
					m_accelerationTime,
					m_accelerationTimeStrafe;
	
	
	public Encoder m_leftEncoder;
	public Encoder m_rightEncoder;
	public Encoder m_strafeEncoder;
	public Gyro m_gyro;
	
	static double kAccelerationPerSecond = 0.2;
	static double kAccelerationPerCycle = kAccelerationPerSecond / 50;

	int m_counter;
	int m_cyclesAfterStrafeAcceleration;
	int m_cyclesAfterDriveAcceleration;

	
	/**
	 * Drive the robot laterally and forward/backwards for a specified distance in each direction.
	 * 
	 * @param maxDriveSpeed The max speed at which to drive the robot in a forward/backward direction, in the range [-1.0, 1.0].
	 * @param driveDistance How far to drive in a forward/backward direction, in inches.
	 * @param maxStrafeSpeed The max speed at which to drive the robot in a lateral direction, in the range [-1.0, 1.0].
	 * @param strafeDistance How far to drive in a lateral direction, in inches.
	 */
    public DriveMaintainingOrientation(double maxDriveSpeed, double driveDistance, double maxStrafeSpeed, double strafeDistance) {
        requires(Robot.m_driveTrain);
        m_maxDriveSpeed = maxDriveSpeed;
        m_driveTargetDistance = driveDistance;
        m_maxStrafeSpeed = maxStrafeSpeed;
        m_strafeTargetDistance = strafeDistance;
        
        // Time taken to complete acceleration, in seconds
        // This variable is needed to tell the robot when to accelerate, drive at constant speed, or decelerate.
        m_accelerationTime = m_maxDriveSpeed / kAccelerationPerSecond;
        m_accelerationTimeStrafe = m_maxStrafeSpeed / kAccelerationPerSecond;
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	m_leftEncoder = Robot.m_driveTrain.getLeftEncoder();
    	m_rightEncoder = Robot.m_driveTrain.getRightEncoder();
    	m_gyro = Robot.m_driveTrain.getGyro();
    	m_accelerationTime = 0;
    	m_counter = 0;
    	m_cyclesAfterDriveAcceleration = 0;
    	m_cyclesAfterStrafeAcceleration = 0;

    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    	m_strafeEncoder.reset();

    }

    protected void execute() {    	
    	m_counter++;
    	
    	
    	
    	// =*=*=*=*=*=*=*=*=*=   FORWARDS AND BACKWARDS CALCULATIONS   =*=*=*=*=*=*=*=*=*=
    	
    	
    	// Take the average of the left and right encoders to find the forward/backwards distance traveled
    	m_driveTravelledDistance = (m_leftEncoder.getDistance() + m_leftEncoder.getDistance()) / 2;
    	
    	
        // m_accelerationTime * 50 gives number of cycles robot must accelerate for, as the CPU runs at 50Hz
    	
    	// If the robot should be accelerating...
    	if(m_counter <= m_accelerationTime * 50) {
    		m_driveSpeed += kAccelerationPerCycle; //accelerate
    	}
    	
    	// If the robot should be driving at a constant speed or decelerating
    	else {
    		
    		// If the robot hasn't yet driven halfway, count up. This count is used later to determine when to start decelerating.
    		if (m_driveTravelledDistance <= m_driveTargetDistance / 2) {
    			m_cyclesAfterDriveAcceleration++;
    			
        	// If the robot is past the midway point, start counting down.
    		} else if (m_driveTravelledDistance >= m_driveTargetDistance / 2) {
    			m_cyclesAfterDriveAcceleration--;
    		}
    		
    		// If the robot should be decelerating
    		if (m_cyclesAfterDriveAcceleration <= 0) {
    			m_driveSpeed -= kAccelerationPerCycle; //decelerate
    		}
    	}
    	
    	// If the robot had traveled far enough forwards/backwards, stop.
    	if(m_driveTravelledDistance >= m_driveTargetDistance) {
    		m_driveSpeed = 0.0;
    	}
    	
    	

    	
    	
    	// =*=*=*=*=*=*=*=*=*=   LATERAL MOVEMENT CALCULATIONS   =*=*=*=*=*=*=*=*=*=
    	
    	
    	// Find the lateral distance traveled
    	m_strafeTravelledDistance = m_strafeEncoder.getDistance();
    	
        // m_accelerationTimeStrafe * 50 gives number of cycles robot must accelerate for, as the CPU runs at 50Hz
    	
    	// If the robot should be accelerating...
    	if(m_counter <= m_accelerationTimeStrafe * 50) {
    		m_strafeSpeed += kAccelerationPerCycle; //accelerate
    	}
    	
    	// If the robot should be driving at a constant speed or decelerating
    	else {
    		
    		// If the robot hasn't yet driven halfway, count up. This count is used later to determine when to start decelerating.
    		if (m_strafeTravelledDistance <= m_strafeTargetDistance / 2) {
    			m_cyclesAfterStrafeAcceleration++;
    			
        	// If the robot is past the midway point, start counting down.
    		} else if (m_strafeTravelledDistance >= m_strafeTargetDistance / 2) {
    			m_cyclesAfterStrafeAcceleration--;
    		}
    		
    		// If the robot should be decelerating
    		if (m_cyclesAfterStrafeAcceleration <= 0) {
    			m_strafeSpeed -= kAccelerationPerCycle; //decelerate
    		}
    	}
    	// If the robot had traveled far enough laterally, stop.
    	if(m_strafeTravelledDistance >= m_strafeTargetDistance) {
    		m_strafeSpeed = 0.0;
    	}
    	
    	
    	
    	
    	
    	
    	// Use the gyro to correct any drift in the robot's driving.
    	//m_gyroCorrect = kP * Robot.m_driveTrain.getGyroAngle();
    	m_gyroCorrect = 0.0;
    	
    	Robot.m_driveTrain.strafeDrive(m_driveSpeed, m_gyroCorrect, m_strafeSpeed);
    }

    protected boolean isFinished() {
    	
    	// If we've finished driving in both directions, return true.
    	if(m_driveTravelledDistance >= m_driveTargetDistance &&
    			m_strafeTravelledDistance >= m_strafeTargetDistance){
            return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.m_driveTrain.strafeDrive(0.0, 0.0, 0.0);
    }

    protected void interrupted() {
    	end();
    }
}
