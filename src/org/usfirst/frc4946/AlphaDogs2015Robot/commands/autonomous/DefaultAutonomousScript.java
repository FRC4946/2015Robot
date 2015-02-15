package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ActuateStrafeSolenoid;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.ElevatorMoveToPosition;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.SetElevatorMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.SetLeftGrabberArm;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.SetRightGrabberArm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * @author Matthew
 */
public class DefaultAutonomousScript extends CommandGroup {
	private int m_initialPosition = 0;
	private int m_mouvementDirectionOrAmount = 0;

	/**
	 * The standard autonomous command.
	 * 
	 * Functionality:
	 * 1. Picks up the first tote
	 * 2. If we are picking up another tote, back up, turns, drives, turns, drive forwards, and pick up the tote.
	 * 3. Drive forwards until we are a fair way into the auto zone
	 * 4. Drop the tote stack, and open the arms wide
	 * 5. Drive backwards until we are clear of the totes
	 * 
	 * @param position
	 *            The starting position of the robot. [0 = left, 1 = middle, 2 =
	 *            right]
	 * @param movementDirection
	 *            If the robot is in the middle, the direction to move. [1 =
	 *            left, 2 = right] If the robot is at the left or right, the
	 *            number of totes to move. If this value is 0, the robot will
	 *            not pick up any additional totes.
	 */
	public DefaultAutonomousScript(int position, int movementDirection) {
		m_initialPosition = position;
		m_mouvementDirectionOrAmount = movementDirection;
		SmartDashboard.putString("Autonomous Status", getStatus());


		// There are currently 1.5 seconds worth of delays
		// 11 x 0.05 sec delays
		//  5 x 0.1  sec delays


		// Lift the strafe wheel, open the arms
		addParallel(new ActuateStrafeSolenoid(true));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		// Lower the elevator all the way down
		addSequential(new SetElevatorMode(true));
		addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));

		// Close the grabber arms in order to grasp the first tote.
		addSequential(new CloseGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));





		// If we need to pick up another tote
		if (m_mouvementDirectionOrAmount > 0) {

			// Drive backwards ~2 feet while lifting the arms
			addParallel(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT));
			addSequential(new SimpleAutoDrive(-RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT);
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// If we started in the left position, drive right 6'9"
			if (m_initialPosition == 0) {
				addSequential(new RotateToAngle(90));
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT);
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new RotateToAngle(-90));
			}

			// If we started in the right position, drive left 6'9"
			else if (m_initialPosition == 2) {
				addSequential(new RotateToAngle(-90));
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT);
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new RotateToAngle(90));
			}

			// If we started in the middle position
			else if (m_initialPosition == 1) {
				// If we want to move left, drive left 6'9"
				if (m_mouvementDirectionOrAmount == 1) {
					addSequential(new RotateToAngle(90));
					addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
					addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT);
					addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
					addSequential(new RotateToAngle(-90));
				}
				// If we want to move right, drive right 6'9"
				else if (m_mouvementDirectionOrAmount == 2) {
					m_mouvementDirectionOrAmount = 1;
					addSequential(new RotateToAngle(-90));
					addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
					addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT);
					addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
					addSequential(new RotateToAngle(90));
				}
			}
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// Drive forwards 2 feet, until we hit the tote
			addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT);
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// Drop the elevator slightly, then open the arms
			addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT));
			addSequential(new OpenGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));


			// Drop the elevator more, close the arms, lift to position 1
			addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
			addSequential(new CloseGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));
		}

		
		
		


		// If we need to pick up yet another tote
		if (m_mouvementDirectionOrAmount > 1) {

			// Drive backwards ~2 feet while lifting the arms
			addParallel(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT));
			addSequential(new SimpleAutoDrive(-RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT);
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// If we started in the left position, drive right 6'9"
			if (m_initialPosition == 0) {
				addSequential(new RotateToAngle(90));
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT);
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new RotateToAngle(-90));
			}

			// If we started in the right position, drive left 6'9"
			else if (m_initialPosition == 2) {
				addSequential(new RotateToAngle(-90));
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT);
				addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));
				addSequential(new RotateToAngle(90));
			}
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// Drive forwards 2 feet, until we hit the tote
			addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT);
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// Drop the elevator slightly, then open the arms
			addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT));
			addSequential(new OpenGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));


			// Drop the elevator more, close the arms, lift to position 1
			addSequential(new ElevatorMoveToPosition(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
			addSequential(new CloseGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));
		}





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


	public String getStatus(){
		String status = "";

		if (m_initialPosition == 0){
			status = status + "Starting in the left position.\n";
		} else 	if (m_initialPosition == 1){
			status = status + "Starting in the middle position.\n";
		} else 	if (m_initialPosition == 2){
			status = status + "Starting in the right position.\n";
		}


		if (m_mouvementDirectionOrAmount == 0){
			status = status + "Not picking up any additional totes.\n";
		} else 	if (m_initialPosition == 1){
			status = status + "Picking up one additional tote.\n";
		} else 	if (m_initialPosition == 2 && m_initialPosition != 2){
			status = status + "Picking up two additional tote.\n";
		} else 	if (m_initialPosition == 2 && m_initialPosition == 2){
			status = status + "Picking up one additional tote.\n";
		}

		return status;
	}
}
