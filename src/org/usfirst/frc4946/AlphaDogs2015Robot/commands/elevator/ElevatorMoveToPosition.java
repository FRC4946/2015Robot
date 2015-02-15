package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class ElevatorMoveToPosition extends Command {

	private double m_newSetPos;
	private double m_setPos;
	
    public ElevatorMoveToPosition(double newPos) {
        requires(Robot.m_elevator);
        m_newSetPos = newPos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	m_setPos = m_newSetPos;
    	
    	//Add the 2.5 inches for the carry mode
    	if (Robot.m_oi.togglePlaceCarry.get() == true) {
    		m_setPos -= 4;
    	}
    	
    	Robot.m_elevator.setFinalTarget(m_setPos);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double elevatorPos = Robot.m_elevator.getElevatorPos();
    	SmartDashboard.putNumber("Elevator Position", elevatorPos);
    	SmartDashboard.putNumber("Elevator User Target", m_setPos);
    	SmartDashboard.putNumber("Elevator Current Target", Robot.m_elevator.m_elevatorPIDController.getSetpoint());

    	
    	Robot.m_elevator.updateTrajectoryTrack();
  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    		return Robot.m_elevator.getPIDisAtPosition();
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.m_elevatorPIDController.setSetpoint(Robot.m_elevator.getElevatorPos());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}