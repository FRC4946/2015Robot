package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.ElevatorMoveToPositionWithAccel;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.SetElevatorMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.strafedropper.ActuateStrafeSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Matthew
 */
public class ToteStackStraightAutonomousScript extends CommandGroup {
	private int m_initialPosition = 0;
	private int m_mouvementDirectionOrAmount = 0;

	public void setInitialPosition(int initPos){
		m_initialPosition = initPos;
	}
	public void setMouvementDirectionOrAmout(int mouvement){
		m_mouvementDirectionOrAmount = mouvement;
	}


	/**
	 * The standard autonomous command.
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
	public ToteStackStraightAutonomousScript(int position, int movementDirection) {
		m_initialPosition = position;
		m_mouvementDirectionOrAmount = movementDirection;
		SmartDashboard.putString("Autonomous Status", getStatus());


		// Lift the strafe wheel
		addSequential(new ActuateStrafeSolenoid(true));

		// Lower the elevator all the way down
		addSequential(new SetElevatorMode(true));
		addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));

		// Close the grabber arms in order to grasp the first tote.
		addSequential(new CloseGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));





		// If we need to pick up another tote
		if (m_mouvementDirectionOrAmount > 0) {

			// Drive forwards 3 feet while lifting the arms
			addParallel(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_ABOVE_TOTE_HEIGHT));
			addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT);
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// Drop the elevator slightly, then open the arms
			addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT));
			addSequential(new OpenGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));


			// Drop the elevator more, close the arms, lift to position 1
			addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
			addSequential(new CloseGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));
		}






		// If we need to pick up yet another tote
		if (m_mouvementDirectionOrAmount > 1) {

			// Drive forwards 3 feet while lifting the arms
			addParallel(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_ABOVE_TOTE_HEIGHT));
			addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT);
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_AFTER_DRIVE));


			// Drop the elevator slightly, then open the arms
			addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT));
			addSequential(new OpenGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));


			// Drop the elevator more, close the arms, lift to position 1
			addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
			addSequential(new CloseGrabberArms());
			addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));
		}


		// If we were driving towards the right...
		if(m_initialPosition == 0 || 
				(m_initialPosition == 1 && m_mouvementDirectionOrAmount == 2)){
			addSequential(new RotateToAngle(270));
		}
		// If we were driving towards the left...
		else if(m_initialPosition == 2 || 
				(m_initialPosition == 1 && m_mouvementDirectionOrAmount == 1)){
			addSequential(new RotateToAngle(90));
		}


		//Drive forwards 12 feet in order to enter the Auto zone
		addParallel(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT));
		//addSequential(new SimpleAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_SPEED, 0.0, 0.0), RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_TIMEOUT);
		addSequential(new DecelerateAutoDrive(RobotConstants.AUTONOMOUS_DRIVE_INTO_ZONE_SPEED, 0.0, 0.0, 0.001, 0.8, 1.8));
		// Place the tote stack on the ground. Open the grabber arms to full size
		addSequential(new ElevatorMoveToPositionWithAccel(RobotConstants.AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT));
		addSequential(new OpenGrabberArms());
		addSequential(new Wait(RobotConstants.AUTONOMOUS_DELAY_ACTUATE_ARMS));

		// End
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
