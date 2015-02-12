package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorMode extends Command {

	private boolean m_modeIsPID;
	
    public SetElevatorMode(boolean newModeIsPID) {
       requires(Robot.m_elevator);
       m_modeIsPID = newModeIsPID;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_elevator.setControlMode(m_modeIsPID);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
