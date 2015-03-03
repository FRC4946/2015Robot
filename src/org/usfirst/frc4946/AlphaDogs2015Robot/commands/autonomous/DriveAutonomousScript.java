package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.ElevatorMoveToPositionWithAccel;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.SetElevatorMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.strafedropper.ActuateStrafeSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAutonomousScript extends CommandGroup {

	public  DriveAutonomousScript() {


		// Open the arms
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		// Lower the elevator to just beneath the lip of the RC
		addSequential(new SetElevatorMode(true));
		addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));

		// Make sure the strafe wheel is up
		addSequential(new ActuateStrafeSolenoid(true));
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_STRAFE));


		//Drive forwards 12 feet in order to enter the Auto zone
		addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_TIMEOUT);
		
		// Backup a tad
		addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_BACKUP_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_BACKUP_TIMEOUT);


		// End
		addSequential(new SimpleAutoDrive(0.0, 0.0, 0.0)); // Will run forever

	}
}
