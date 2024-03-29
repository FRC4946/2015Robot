package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.DriveWithJoystick;
import org.usfirst.frc4946.AlphaDogs2015Robot.util.SimplePIController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.CounterBase.EncodingType;
//import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;


/**
 * @author Matthew, Ashish
 */
public class DriveTrain extends Subsystem {
	RobotDrive m_robotDrive = RobotMap.driveTrainRobotDrive;
	SpeedController m_strafeMotor = RobotMap.driveTrainStrafeMotor;
	public SimplePIController m_gyroPIDController;
	Gyro m_gyro = RobotMap.driveTrainGyro;
	Encoder m_leftEncoder = RobotMap.driveTrainLeftEncoder;
	Encoder m_rightEncoder = RobotMap.driveTrainRightEncoder;
	Encoder m_strafeEncoder = RobotMap.driveTrainStrafeEncoder;

	private boolean m_isStrafeMode;
	private boolean m_isGyroMode = false;
	boolean m_isLimitAccel = false;

	/* Encoder (presumably) pulses 1000 times per revolution
	 * Wheel's circumference is (6.0 * pi) inches
	 * 1 encoder pulse is (0.006 * pi) inches
	 * */
	double kDistancePerPulse = 0.006 * Math.PI;

	//save the initial position of the gyroscope
	double gyroInitialPosition;


	public void initGyro(){
		m_gyro.initGyro(); //Set up Gyro first, since DriveWithJoystick uses the gyro

	}

	public void initDefaultCommand() {	

		m_gyro.reset();
		m_gyro.setPIDSourceParameter(PIDSource.PIDSourceParameter.kAngle);
		m_gyroPIDController = new SimplePIController(0.01, 0.005, getGyro());
		m_gyroPIDController.setContinuous(true);
		m_gyroPIDController.setDirection(false);
		m_gyroPIDController.setInputRange(0, 360);
		m_gyroPIDController.setOutputRange(-0.3, 0.3);
		m_gyroPIDController.setTolerence(3);


		setDefaultCommand(new DriveWithJoystick());

		m_leftEncoder.setDistancePerPulse(kDistancePerPulse);
		m_rightEncoder.setDistancePerPulse(kDistancePerPulse);
		m_strafeEncoder.setDistancePerPulse(kDistancePerPulse);

	}

	/**
	 * Set the default command to DriveWithJoystickStrafe
	 */
	public void setDriveStrafe() {
		m_isStrafeMode = true;
	}

	/**
	 * Set the default command to DriveWithJoystickArcade
	 */
	public void setDriveArcade() {
		m_isStrafeMode = false;
	}


	public double getThrottle(){
		// Get the throttle value from the drive joystick
		double driveSpeed = Robot.m_oi.getDriveJoystick().getThrottle();
		driveSpeed *= -1;                        //Flip range from (1, -1) to (-1, 1)
		driveSpeed = (driveSpeed + 1) / 2;    // Shift to (0,1)

		return driveSpeed;
	}

	/**
	 * Arcade style driving for the DriveTrain. 
	 * @param moveValue Speed in range [-1,1]
	 * @param rotateValue Rotation in range [-1,1]
	 */
	public void drive(double moveValue, double rotateValue) {

		double driveSpeed = getThrottle();

		//Scale motor speed based off of the drive joystick throttle
		moveValue = moveValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
		rotateValue = rotateValue * (0.3 + 0.5 * driveSpeed); // 0.5 to 0.8
		m_strafeMotor.set(0.0);
		m_robotDrive.arcadeDrive(moveValue, rotateValue);	
	}


	/**
	 * Strafe driving for the DriveTrain. 
	 * @param moveValue Speed in range [-1,1]//
	 * @param rotateValue Rotation in range [-1,1]
	 * @param strafeValue Strafe speed in range [-1,1]
	 */
	public void strafeDrive(double moveValue, double rotateValue, double strafeValue) {		

		// Limit the value of the strafe wheel from [-1.0, 1.0]
		if (strafeValue > 1.0) {
			strafeValue = 1.0;
		}
		if (strafeValue < -1.0) {
			strafeValue = -1.0;
		}        


		// Manually square inputs to add precision near middle of Joystick
		if (moveValue >= 0.0) moveValue = (moveValue * moveValue);
		else moveValue = -(moveValue * moveValue);

		//if (rotateValue >= 0.0) rotateValue = (rotateValue * rotateValue);
		//else rotateValue = -(rotateValue * rotateValue);

		if (strafeValue >= 0.0) strafeValue = (strafeValue * strafeValue);
		else strafeValue = -(strafeValue * strafeValue);


		//Scale motor speed based off of the drive joystick throttle
		double driveSpeed = getThrottle();
		moveValue = moveValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
		rotateValue = rotateValue * (0.1 + 0.5 * driveSpeed); // 0.1 to 0.6
		strafeValue = strafeValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
		rotateValue = Math.copySign(Math.pow(Math.abs(rotateValue), 3), rotateValue);

		if (Math.abs(moveValue) >= 0.75) {
			Robot.m_strafeDropper.setDropWheel(2);
		}

		// Set the motors to the desired speed
		m_robotDrive.arcadeDrive(moveValue, rotateValue, false);
		m_strafeMotor.set(strafeValue);
	}

	/**
	 * Straight driving using Gyro for the DriveTrain. 
	 * @param moveValue Speed in range [-1,1]
	 * @param strafeValue Strafe speed in range [-1,1]
	 */
	public void gyroDrive(double moveValue, double strafeValue) {
		double gyroPos = m_gyro.getAngle();

		if(gyroPos > 360){
			gyroPos = gyroPos % 360;
		}
		else if (gyroPos < 0){
			while (gyroPos < 0){
				gyroPos += 360;
			}
			gyroPos = gyroPos % 360;
		}
		
		//If the closest angle is 0
		if (Math.abs(gyroPos) <= 45) {
			m_gyroPIDController.setSetpoint(0.0);
		}
		//If the closest angle is 90
		else if (Math.abs(gyroPos - 90) <= 45) {
			m_gyroPIDController.setSetpoint(90.0);
		}
		//If the closest angle is 180
		else if (Math.abs(gyroPos - 180) <= 45) {
			m_gyroPIDController.setSetpoint(180.0);
		}
		//If the closest angle is 270
		else if (Math.abs(gyroPos - 270) <= 45) {
			m_gyroPIDController.setSetpoint(270.0);
		}
		//If the closest angle is 360 (Which is 0)
		else if (gyroPos >= 315) {
			m_gyroPIDController.setSetpoint(0.0);
		}


		// Manually square inputs to add precision near middle of Joystick
		if (moveValue >= 0.0) moveValue = (moveValue * moveValue);
		else moveValue = -(moveValue * moveValue);

		if (strafeValue >= 0.0) strafeValue = (strafeValue * strafeValue);
		else strafeValue = -(strafeValue * strafeValue);


		//Scale motor speed based off of the drive joystick throttle
		double driveSpeed = getThrottle();
		moveValue = moveValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0
		strafeValue = strafeValue * (0.5 + 0.5 * driveSpeed); // 0.5 to 1.0

		if (moveValue >= 0.75) {
			Robot.m_strafeDropper.setDropWheel(2);
		}

		// Set the motors to the desired speed
		m_robotDrive.arcadeDrive(moveValue, m_gyroPIDController.getOutput(), false);
		m_strafeMotor.set(strafeValue);
	}

	/**
	 * @param currentSpeed The speed that was calculated this cycle.
	 * @param oldSpeed The (saved) speed that was calculated in the previous cycle.
	 * @return
	 */
	public double limitAcceleration(double currentSpeed, double oldSpeed) {
		// Right now, only limiting acceleration is implemented. This means the driver may decelerate as quickly as possible.
		if (currentSpeed > 0 && oldSpeed > 0) {					// If movement is positive

			if (currentSpeed > oldSpeed + RobotConstants.DRIVETRAIN_MAX_ACCEL) {	// Limit Accel
				currentSpeed = oldSpeed + RobotConstants.DRIVETRAIN_MAX_ACCEL;
			}

		} else if (currentSpeed < 0 && oldSpeed < 0) {			// If movement is negative

			if (currentSpeed < oldSpeed - RobotConstants.DRIVETRAIN_MAX_ACCEL) {	// Limit Accel
				currentSpeed = oldSpeed - RobotConstants.DRIVETRAIN_MAX_ACCEL;
			}

		}

		return currentSpeed;
	}


	/**
	 * Strafe driving for the DriveTrain. To be used in autonomous. Does not scale the outputs based off of the throttle. 
	 * @param moveValue Speed in range [-1,1]
	 * @param rotateValue Rotation in range [-1,1]
	 * @param strafeValue Strafe speed in range [-1,1]
	 */
	public void autoStrafeDrive(double moveValue, double rotateValue, double strafeValue) {
		// Set the motors to the desired speed
		m_robotDrive.arcadeDrive(moveValue, rotateValue, false);
		m_strafeMotor.set(strafeValue);
	}


	/**
	 * Get the Left Encoder
	 * 
	 * @return The left encoder
	 */
	public Encoder getLeftEncoder() {
		return m_leftEncoder;
	}

	/**
	 * Get the Right Encoder
	 * 
	 * @return The right encoder
	 */
	public Encoder getRightEncoder() {
		return m_rightEncoder;
	}

	/**
	 * Get the Strafe Encoder
	 * 
	 * @return The strafe encoder
	 */
	public Encoder getStrafeEncoder() {
		return m_strafeEncoder;
	}

	/**
	 * Get the Gyro
	 * 
	 * @return The gyro
	 */
	public Gyro getGyro() {
		return m_gyro;
	}

	public boolean getDriveMode(){
		return m_isStrafeMode;
	}

	public boolean getGyroMode() {
		return m_isGyroMode;
	}

	public void setGyroMode(boolean GyroMode) {
		m_isGyroMode = GyroMode;
	}

	public void resetGyroToZero() {
		m_gyro.reset();
	}

	public boolean getLimitAccel() {
		return m_isLimitAccel;
	}

	public void setLimitAccel(boolean limitAccelMode) {
		m_isLimitAccel = limitAccelMode;
	}
}
