package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntermediatePIDControl extends Command {

	double currentPos;
	
    public IntermediatePIDControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentPos = Robot.m_elevator.getElevatorPos(); 
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double newSetPoint = (currentPos + (0.01*Robot.m_oi.getOperatorJoystick().getY()));
    	
    	Robot.m_elevator.m_elevatorPIDController.setSetpoint(newSetPoint);
    			
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
