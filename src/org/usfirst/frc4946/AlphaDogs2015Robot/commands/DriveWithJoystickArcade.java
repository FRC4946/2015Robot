package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

/**
 * A command to drive the robot without the strafe wheel. Simply use the left
 * and right 'skids'
 *
 * @author Matthew
 */
public class DriveWithJoystickArcade extends Command {

	public DriveWithJoystickArcade() {
		requires(Robot.m_driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double Yval = Robot.m_oi.getDriveJoystick().getY();
		double Xval = Robot.m_oi.getDriveJoystick().getX();

		Robot.m_driveTrain.drive(Yval, -Xval);

		// Robot.m_driveTrain.drive(Robot.m_oi.getDriveJoystick());
	}

	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	protected void end() {
		Robot.m_driveTrain.drive(0, 0); // Stop driving
	}

	protected void interrupted() {
		end();
	}
}
