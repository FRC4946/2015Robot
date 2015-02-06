package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

/**
 * A command to shift to the high gear on the Vex gear boxes
 *
 * @author Matthew
 */
public class ShiftGear extends Command {

	public boolean m_isHighGear;
	private int counter;
	
	public ShiftGear(boolean isHighGear) {
		requires(Robot.m_driveTrain);
		m_isHighGear = isHighGear;
	}

	protected void initialize() {
		counter = 0;
	}

	protected void execute() {
		if (m_isHighGear){
			Robot.m_driveTrain.setGear(2);
		} else {
			Robot.m_driveTrain.setGear(1);
		}
		
		counter ++;
	}

	protected boolean isFinished() {
		// The command only needs to loop once, simply return right away.
		return (counter > 5);
	}

	protected void end() {
		Robot.m_driveTrain.setGear(0);
	}

	protected void interrupted() {
		end();
	}
}
