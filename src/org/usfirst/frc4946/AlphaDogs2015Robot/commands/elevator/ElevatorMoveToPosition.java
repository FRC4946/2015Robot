package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class ElevatorMoveToPosition extends Command {

	private double m_newSetPos;
	
    public ElevatorMoveToPosition(double newPos) {
        requires(Robot.m_elevator);
        m_newSetPos = newPos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	if (Robot.m_oi.togglePlaceCarry.get() == true &&
    			m_newSetPos != RobotConstants.ELEVATOR_HEIGHT_HOOK) { // Preset 0 is for the hook. Don't mess with it
    		m_newSetPos -= 4;
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double elevatorPos = Robot.m_elevator.getElevatorPos();
    	SmartDashboard.putNumber("Elevator Position", elevatorPos);
    	SmartDashboard.putNumber("Elevator User Target", m_newSetPos);
    	SmartDashboard.putNumber("Elevator Current Target", Robot.m_elevator.m_elevatorPIDController.getSetpoint());

    	
    	Robot.m_elevator.m_elevatorPIDController.setSetpoint(m_newSetPos);

  
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