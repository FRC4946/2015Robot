package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command group to switch to the advanced strafe method of driving.
 *
 * @author Ashish
 */
public class SetToStrafeMode extends CommandGroup {

	public SetToStrafeMode() {
		Robot.m_driveTrain.setDriveStrafe(); // Set the default command of the
												// drive train to Strafe drive

		addParallel(new ActuateStrafeSolenoid(false)); // Drop the strafe wheel
	}
}
