package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ActuateStrafeSolenoid;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.ElevatorMoveToPosition;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.SetElevatorMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RecyclingContainerAutonomousScript extends CommandGroup {

	public  RecyclingContainerAutonomousScript() {


		// Lift the strafe wheel, open the arms
		addParallel(new ActuateStrafeSolenoid(true));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		// Lower the elevator to just beneath the lip of the RC
		addSequential(new SetElevatorMode(true));
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_CONTAINER_HEIGHT));

		// Close the grabber arms in order to grasp recycling container
		addSequential(new CloseGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		// Lift the RC just off of the ground
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_CARRY_CONTAINER_HEIGHT));


		//Drive forwards 12 feet in order to enter the Auto zone
		addParallel(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT));
		addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_TIMEOUT);
		
		// Place the tote stack on the ground. Open the grabber arms to full size
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		// Drive backwards 1 foot in order to be fully clear of the tote stack
		addSequential(new SimpleAutoDrive(-RobotConstants.AUTONOMOUS_DRIVE_FINISH_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_FINISH_TIMEOUT);



		// End
		addParallel(new CloseGrabberArms());
		addSequential(new SimpleAutoDrive(0.0, 0.0, 0.0)); // Will run forever

	}
}
