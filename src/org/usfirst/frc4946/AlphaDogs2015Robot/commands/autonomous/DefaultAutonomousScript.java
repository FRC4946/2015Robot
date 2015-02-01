package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * @author Matthew
 */
public class DefaultAutonomousScript extends CommandGroup {
	private int m_initialPosition = 0;
	private int m_mouvementDirectionOrAmount = 0;
	private boolean m_toteIsPreLoaded = false;

	/**
	 * The standard autonomous command.
	 * 
	 * Functionality:
	 * 1. Picks up the first tote
	 * 2. If we are picking up another tote, back up, strafe sideways, drive forwards, and pick up the tote.
	 * 3. Drive forwards unti we are a fair way into the auto zone
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
	 * @param isPreLoaded
	 *            Whether the first tote is pre-loaded or not. Typically, this
	 *            will be true;
	 */
	public DefaultAutonomousScript(int position, int movementDirection, boolean isPreLoaded) {
		m_initialPosition = position;
		m_mouvementDirectionOrAmount = movementDirection;
		m_toteIsPreLoaded = isPreLoaded;
        SmartDashboard.putString("Autonomous Status", getStatus());

        
        
		
		// If the tote isn't already in place, pick it up
		if (!m_toteIsPreLoaded) {
			// TODO: Close arms, lift elevator to position 1
		}

		// If we need to pick up another tote...
		while (m_mouvementDirectionOrAmount != 0) {

			// TODO: Drive backwards 2 feet

			// If we started in the left position
			if (m_initialPosition == 0) {
				// TODO: Drive right 3 feet 10.5 inches
			}

			// If we started in the right position
			if (m_initialPosition == 2) {
				// TODO: Drive left 3 feet 10.5 inches
			}

			// If we started in the middle position
			if (m_initialPosition == 1) {
				if (m_mouvementDirectionOrAmount == 1) {
					// TODO: Drive left 3 feet 10.5 inches
				} else if (m_mouvementDirectionOrAmount == 2) {
					m_mouvementDirectionOrAmount = 1;
					// TODO: Drive right 3 feet 10.5 inches
				}
			}

			// TODO: Drive forwards 2 feet (Or until limit switch hits?)

			// TODO: Drop the elevator slightly, open the arms, drop the
			// elevator more, close the arms, lift to position 1

			// Decrement the number of positions left to move
			m_mouvementDirectionOrAmount--;
		}

		// TODO: Drive forwards 11 feet 6 inches in order to enter the Auto zone
		// TODO: Place the tote stack on the ground. Open the grabber arms to
		// full size
		// TODO: Drive backwards 1 foot in order to be fully clear of the tote
		// stack

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
