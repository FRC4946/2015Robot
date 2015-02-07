package org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command group to switch to the simple arcade drive method of driving.
 *
 * @author Ashish
 */
public class SetToArcadeMode extends CommandGroup {

	public SetToArcadeMode() {

		// Set the default command of the drive train to arcade drive
		Robot.m_driveTrain.setDriveArcade();

		boolean strafeButtonPressed = false;
		
		// Check the state of the manual strafe button
		if(Robot.m_oi != null){
			strafeButtonPressed = Robot.m_oi.actuateStrafe.get();
		}
		
		// If the button is pressed, lift the wheel
		addSequential(new ActuateStrafeSolenoid(strafeButtonPressed));
	}
}
