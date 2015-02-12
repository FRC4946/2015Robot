package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
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
	 * 2. If we are picking up another tote, back up, strafe sideways, drive forwards, and pick up the tote.
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

		addSequential(new ActuateStrafeSolenoid(true));
		addSequential(new OpenGrabberArms());
		
		addSequential(new Wait(2));
		
		// Lower the elevator all the way down
		addSequential(new SetElevatorMode(true));
		addSequential(new ElevatorMoveToPosition(9.5));
		
		addSequential(new Wait(2));

		
		// Close the grabber arms in order to grasp the first tote.
		addSequential(new CloseGrabberArms());

		addSequential(new Wait(2));

		
		// Lift the elevator to 12 inches
		addSequential(new ElevatorMoveToPosition(15.0));

		addSequential(new Wait(2));

		
		// If we need to pick up another tote
		if (m_mouvementDirectionOrAmount > 0) {
			
			addSequential(new ElevatorMoveToPosition(30.0));

			addSequential(new Wait(2));

			
			// Drive backwards 2 feet
			//addSequential(new DriveMaintainingOrientation(0.2, -24, 0, 0));
			addSequential(new SimpleAutoDrive(-0.5, 0.0, 0.0), 2);			
			
			addSequential(new Wait(2));
			
			addSequential(new ActuateStrafeSolenoid(false));
			
			addSequential(new Wait(2));


			
			// If we started in the left position
			if (m_initialPosition == 0) {
				// Drive right 3 feet 10.5 inches
				//addSequential (new DriveMaintainingOrientation(0.0, 0.0, 0.5, 46.5));
				addSequential(new SimpleAutoDrive(0.0, 0.5, 0.0), 3);			

			}

			// If we started in the right position
			else if (m_initialPosition == 2) {
				// Drive left 3 feet 10.5 inches
				//addSequential (new DriveMaintainingOrientation(0.0, 0.0, 0.5, -46.5));
				addSequential(new SimpleAutoDrive(0.0, -0.5, 0.0), 3);			
			}

			// If we started in the middle position
			else if (m_initialPosition == 1) {
				if (m_mouvementDirectionOrAmount == 1) {
					// Drive left 3 feet 10.5 inches
					//addSequential(new DriveMaintainingOrientation(0, 0, 0.2, -46.5));
					addSequential(new SimpleAutoDrive(0.0, 0.5, 0.0), 3);			

				} else if (m_mouvementDirectionOrAmount == 2) {
					m_mouvementDirectionOrAmount = 1;
					// Drive right 3 feet 10.5 inches
					//addSequential(new DriveMaintainingOrientation(0, 0, 0.2, 46.5));
					addSequential(new SimpleAutoDrive(0.0, -0.5, 0.0), 3);			
					
				}
			}

			
			addSequential(new Wait(2));
			addSequential(new ActuateStrafeSolenoid(true));
			addSequential(new Wait(2));
			
			// Drive forwards 2 feet (Or until limit switch hits?)
			//addSequential(new DriveMaintainingOrientation(0.2, 24, 0, 0));
			addSequential(new SimpleAutoDrive(0.5, 0.0, 0.0), 2);			

			addSequential(new Wait(2));

			
			// Drop the elevator slightly, then open the arms
			addSequential(new ElevatorMoveToPosition(18.0));

			addSequential(new Wait(2));
			
			addSequential(new OpenGrabberArms());

			addSequential(new Wait(2));


			// Drop the elevator more, close the arms, lift to position 1
			addSequential(new ElevatorMoveToPosition(9.5));
			
			addSequential(new Wait(2));
			
			addSequential(new CloseGrabberArms());
			
			addSequential(new Wait(2));
			
			addSequential(new ElevatorMoveToPosition(15));
			
			addSequential(new Wait(2));
		}

//		// If we need to pick up another tote
//		if (m_mouvementDirectionOrAmount > 0) {
//
//			// Drive backwards 2 feet
//			//addSequential(new DriveMaintainingOrientation(0.2, -24, 0, 0));
//			addSequential(new SimpleAutoDrive(0.2, 0.0, 0.0), 0.5);			
//
//			
//			// If we started in the left position
//			if (m_initialPosition == 0) {
//				// Drive right 3 feet 10.5 inches
//				//addSequential (new DriveMaintainingOrientation(0.0, 0.0, 0.5, 46.5));
//				addSequential(new SimpleAutoDrive(0.0, 0.5, 0.0), 3);			
//
//			}
//
//			// If we started in the right position
//			else if (m_initialPosition == 2) {
//				// Drive left 3 feet 10.5 inches
//				//addSequential (new DriveMaintainingOrientation(0.0, 0.0, 0.5, -46.5));
//				addSequential(new SimpleAutoDrive(0.0, -0.5, 0.0), 3);			
//			}
//
//			// Drive forwards 2 feet (Or until limit switch hits?)
//			//addSequential(new DriveMaintainingOrientation(0.2, 24, 0, 0));
//			addSequential(new SimpleAutoDrive(0.2, 0.0, 0.0), 2);			
//
//
//			// Drop the elevator slightly, then open the arms
//			addSequential(new ElevatorMoveToPosition(10.0));
//			addSequential(new OpenGrabberArms());
//
//
//
//			// Drop the elevator more, close the arms, lift to position 1
//			addSequential(new ElevatorMoveToPosition(9.5));
//			addSequential(new CloseGrabberArms());
//			addSequential(new ElevatorMoveToPosition(12));
//		}
		
		//Drive forwards 11 feet 6 inches in order to enter the Auto zone
		//addSequential(new DriveMaintainingOrientation(0.2, 138, 0, 0));
		addSequential(new SimpleAutoDrive(0.5, 0.0, 0.0), 2);			

		addSequential(new Wait(2));
		
		// Place the tote stack on the ground. Open the grabber arms to full size
		addSequential(new ElevatorMoveToPosition(9.5));
		
		addSequential(new Wait(2));
		
		addSequential(new OpenGrabberArms());
		
		addSequential(new Wait(2));

		// Drive backwards 1 foot in order to be fully clear of the tote stack
		//addSequential(new DriveMaintainingOrientation(0.2, -12, 0, 0));
		addSequential(new SimpleAutoDrive(-0.5, 0.0, 0.0), 2);			

		
		// Do donuts
		addSequential(new SimpleAutoDrive(0.0, 0.0, 0.5));
		

		
		
		
		// End
		
		
		
		
		
		
		//		// If the tote isn't already in place, pick it up
		//		if (!m_toteIsPreLoaded) {
		//			// TODO: Close arms, lift elevator to position 1
		//			addParallel(new SetLeftGrabberArm(false));
		//			addSequential(new SetRightGrabberArm(0));
		//			//Robot.m_elevator.enable();
		//	        //Robot.m_elevator.setSetpoint(1.0);
		//		}


		//		// If we need to pick up another tote...
		//		while (m_mouvementDirectionOrAmount != 0) {
		//
		//			// TODO: Drive backwards 2 feet
		//			addSequential(new DriveMaintainingOrientation(0.2, -24, 0, 0));
		//			// If we started in the left position
		//			if (m_initialPosition == 0) {
		//				// TODO: Drive right 3 feet 10.5 inches
		//				addSequential (new DriveMaintainingOrientation(0.0, 0.0, 0.5, 46.5));
		//			}
		//
		//			// If we started in the right position
		//			if (m_initialPosition == 2) {
		//				// TODO: Drive left 3 feet 10.5 inches
		//				addSequential (new DriveMaintainingOrientation(0.0, 0.0, 0.5, -46.5));
		//			}
		//
		//			// If we started in the middle position
		//			if (m_initialPosition == 1) {
		//				if (m_mouvementDirectionOrAmount == 1) {
		//					// TODO: Drive left 3 feet 10.5 inches
		//					addSequential(new DriveMaintainingOrientation(0, 0, 0.2, -46));
		//				} else if (m_mouvementDirectionOrAmount == 2) {
		//					m_mouvementDirectionOrAmount = 1;
		//					// TODO: Drive right 3 feet 10.5 inches
		//					addSequential(new DriveMaintainingOrientation(0, 0, 0.2, 46));
		//				}
		//			}
		//
		//			// TODO: Drive forwards 2 feet (Or until limit switch hits?)
		//			addSequential(new DriveMaintainingOrientation(0.2, 24, 0, 0));
		//			// TODO: Drop the elevator slightly, open the arms, 
		//			addParallel(new SetLeftGrabberArm(true));
		//			addSequential(new SetRightGrabberArm(3));
		//			// TODO: drop the elevator more, close the arms, lift to position 1
		//			addParallel(new SetLeftGrabberArm(false));
		//			addSequential(new SetRightGrabberArm(0));
		//			// Decrement the number of positions left to move
		//			m_mouvementDirectionOrAmount--;
		//		}
		//
		//		// TODO: Drive forwards 11 feet 6 inches in order to enter the Auto zone
		//		addSequential(new DriveMaintainingOrientation(0.2, 138, 0, 0));
		//		// TODO: Place the tote stack on the ground. Open the grabber arms to
		//		// full size
		//		addParallel(new SetLeftGrabberArm(true));
		//		addSequential(new SetRightGrabberArm(3));
		//		// TODO: Drive backwards 1 foot in order to be fully clear of the tote
		//		// stack
		//		addSequential(new DriveMaintainingOrientation(0.2, -12, 0, 0));

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
