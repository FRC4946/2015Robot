package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc4946.AlphaDogs2015Robot.*;

/**
 *
 * @author Matthew
 */
public class DriveMaintainingOrientationUntilSwitch extends Command {

	private double m_driveSpeed,
					m_maxDriveSpeed,
					m_driveTargetDistance,
					m_driveTravelledDistance,
					m_driveError,
					m_gyroCorrect= 0.0,
					m_accelerationTime;
	
	public Encoder m_leftEncoder;
	public Encoder m_rightEncoder;
	public Gyro m_gyro;
	
	int m_counter;
	int m_cyclesAfterAcceleration;

	double kAccelerationPerSecond = 0.2;
	double kAccelerationPerCycle = kAccelerationPerSecond / 50;
	
	/**
	 * Drive the robot forwards/backwards until it hits a tote and triggers a limit switch.
	 * If the robot drives too far past the estimate, assume something went wrong and stop driving.
	 * 
	 * @param maxDriveSpeed The max speed at which to drive the robot in a whateverTheWordForPerpendicularToLateralIs (literal) direction.
	 * @param driveDistance The estimate of how far the robot will drive before hitting its limit switch, in inches
	 * @param driveError The limit of how far past the target the robot will be allowed to drive, in inches
	 */
    public DriveMaintainingOrientationUntilSwitch(double maxDriveSpeed, double driveDistance, double driveError) {
        requires(Robot.m_driveTrain);
        m_maxDriveSpeed = maxDriveSpeed;
        m_driveTargetDistance = driveDistance;
        m_driveError = driveError;
        
        // Time taken to complete acceleration, in seconds
        // This variable is needed to tell the robot when to accelerate, drive at constant speed, or decelerate.
        m_accelerationTime = m_maxDriveSpeed / kAccelerationPerSecond;        
    }

    protected void initialize() {
    	
    	m_leftEncoder = Robot.m_driveTrain.getLeftEncoder();
    	m_rightEncoder = Robot.m_driveTrain.getRightEncoder();
    	m_gyro = Robot.m_driveTrain.getGyro();
    	m_accelerationTime = 0;
    	m_counter = 0;
    	m_cyclesAfterAcceleration = 0;
    	
    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    }

    protected void execute() {    	    	
    	m_counter++;
    	
    	// Take the average of the left and right encoders to find the distance traveled
    	m_driveTravelledDistance = (m_leftEncoder.getDistance() + m_leftEncoder.getDistance()) / 2;
    	
    	
        // m_accelerationTime * 50 gives number of cycles robot must accelerate for
    	
    	// If the robot should be accelerating...
    	if(m_counter <= m_accelerationTime * 50) {
    		m_driveSpeed += kAccelerationPerCycle; //accelerate
    	}
    	
    	// If the robot should be driving at a constant speed or decelerating
    	else {
    		
    		// If the robot hasn't yet driven halfway, count up. This count is used later to determine when to start decelerating.
    		if (m_driveTravelledDistance <= m_driveTargetDistance / 2) {
    			m_cyclesAfterAcceleration++;
    		
    		// If the robot is past the midway point, start counting down.
    		} else if (m_driveTravelledDistance >= m_driveTargetDistance / 2) {
    			m_cyclesAfterAcceleration--;
    		}
   		

    		
    		// If the robot should be decelerating
    		if (m_cyclesAfterAcceleration <= 0) {
    			m_driveSpeed -= kAccelerationPerCycle;
    			
    			// Keep driving slowly
    			if (m_driveSpeed < 0.05){
    				m_driveSpeed = 0.05; 
    			}
    		}
    	}    	
    	
    	
    	// Use the gyro to correct any drift in the robot's driving.
    	//m_gyroCorrect = kP * Robot.m_driveTrain.getGyroAngle();
    	
    	Robot.m_driveTrain.drive(m_driveSpeed, m_gyroCorrect);
    }

    protected boolean isFinished() {
    	
    	// If the robot had driven too far without hitting the switch, return true anyways
    	if(m_driveTravelledDistance >= m_driveTargetDistance + m_driveError){
            return true;
    	}
    	
    	return Robot.m_driveTrain.getLimitSwitchState();
    }

    protected void end() {
    	Robot.m_driveTrain.drive(0.0, 0.0);
    }

    protected void interrupted() {
    	end();
    }
}