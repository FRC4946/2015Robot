package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc4946.AlphaDogs2015Robot.*;

/**
 *
 * @author Matthew
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
					m_gyroCorrect= 0.0;
	
	public Encoder m_leftEncoder = Robot.m_driveTrain.getLeftEncoder();
	public Encoder m_rightEncoder = Robot.m_driveTrain.getRightEncoder();
	public Encoder m_strafeEncoder = Robot.m_driveTrain.getStrafeEncoder();
	public Gyro m_gyro = Robot.m_driveTrain.getGyro();
	double kAccelerationPerSecond = 0.2;
	double kAccelerationPerCycle = kAccelerationPerSecond / 50;
	double m_accelerationTime = 0;
	double m_accelerationTimeStrafe = 0;
	double m_gyroAngle = 0;
	int m_counter = 0;
	int m_cyclesAfterAcceleration = 0;
	
	/**
	 * Drive the robot laterally and whateverTheWordForPerpendicularToLateralIs (literal) for a specified distance in each direction and at a specific speed.
	 * @param maxDriveSpeed The max speed at which to drive the robot in a whateverTheWordForPerpendicularToLateralIs (literal) direction.
	 * @param driveDistance
	 * @param maxStrafeSpeed
	 * @param strafeDistance
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
    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    	m_strafeEncoder.reset();
    	m_counter = 0;
    	m_cyclesAfterAcceleration = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	
    	m_counter++;
    	
    	//get distances from encoders
    	m_strafeTravelledDistance = m_strafeEncoder.getDistance();
    	//take the average of the left and right encoders for total distance travelled
    	m_driveTravelledDistance = (m_leftEncoder.getDistance() + m_leftEncoder.getDistance()) / 2;
    	
    	
        // m_accelerationTime * 50 gives number of cycles robot must accelerate for
    	if(m_counter <= m_accelerationTime * 50) {
    		m_driveSpeed += kAccelerationPerCycle; //accelerate
    	} else {
    		
    		// if the robot isnt past the midpoint, count. This count is used later to determine when to start deccelerating.
    		if (m_driveTravelledDistance <= m_driveTargetDistance / 2) {
    			m_cyclesAfterAcceleration++;
    		//if it is past the midway point, start counting down.
    		} else if (m_driveTravelledDistance >= m_driveTargetDistance / 2) {
    			m_cyclesAfterAcceleration--;
    		}
    		
    		if (m_cyclesAfterAcceleration <= 0) {
    			m_driveSpeed -= kAccelerationPerCycle; //deccelerate
    		}
    	}
    	
    	m_gyroAngle = m_gyro.getAngle();
    	
    	
    	if(m_driveTravelledDistance >= m_driveTargetDistance) {
    		m_driveSpeed = 0.0;
    	}
    	
    	if(m_strafeTravelledDistance >= m_strafeTargetDistance) {
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
