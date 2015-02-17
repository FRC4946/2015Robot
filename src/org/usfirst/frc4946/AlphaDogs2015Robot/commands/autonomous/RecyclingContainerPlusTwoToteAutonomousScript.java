package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.ElevatorMoveToPosition;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.SetElevatorMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.strafedropper.ActuateStrafeSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RecyclingContainerPlusTwoToteAutonomousScript extends CommandGroup {

	public  RecyclingContainerPlusTwoToteAutonomousScript() {


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



		//Lift the elevator up high enough to place the RC on the tote
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_CONTAINER_ABOVE_TOTE_HEIGHT));

		// If we're facing perpendicular to the driver's wall
		//Drop the strafe
		addSequential(new ActuateStrafeSolenoid(false));
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_STRAFE));

		// Strafe right
		addSequential(new SimpleAutoDrive(0.0, -RobotConstants.AUTONOMOUS_DRIVE_STRAFE_FROM_RC_TO_TOTE_SPEED, 0.0), RobotConstants.AUTONOMOUS_DRIVE_STRAFE_FROM_RC_TO_TOTE_TIMEOUT);
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));

		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_CONTAINER_DROP_ON_TOTE_HEIGHT));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));
		
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
		addSequential(new CloseGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_ABOVE_TOTE_HEIGHT));
		addSequential(new SimpleAutoDrive(0.0, 0.8, 0.0), 2);
		
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));
		
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
		addSequential(new CloseGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));		
		
		
		// Raise the strafe
		addSequential(new ActuateStrafeSolenoid(true));
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_STRAFE));

		
		
		//Drive forwards 12 feet in order to enter the Auto zone
		addParallel(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT));
		addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_TIMEOUT);

		// Place the tote stack on the ground. Open the grabber arms to full size
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));


		// End
		addSequential(new SimpleAutoDrive(0.0, 0.0, 0.0)); // Will run forever

	}
}
