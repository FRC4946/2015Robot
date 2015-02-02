package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to actuate the strafe wheel down onto the ground
 *
 * @author Ashish, Matthew
 */
public class ActuateStrafeSolenoid extends Command {

	public boolean m_isWheelRaised;

	public ActuateStrafeSolenoid(boolean isWheelRaised) {
		requires(Robot.m_driveTrain);
		m_isWheelRaised = isWheelRaised;
	}

	protected void initialize() {
	}

	protected void execute() {
		if (m_isWheelRaised){
			Robot.m_driveTrain.setDropWheel(2);
		} else {
			Robot.m_driveTrain.setDropWheel(1);
		}
	}

	protected boolean isFinished() {
		// The command only needs to loop once, simply return right away.
		return true;
	}

	protected void end() {
		Robot.m_driveTrain.setDropWheel(0);
	}

	protected void interrupted() {
		end();
	}
}
