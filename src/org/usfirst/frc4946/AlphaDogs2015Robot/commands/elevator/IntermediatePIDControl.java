package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntermediatePIDControl extends Command {

	double currentPos;
	double newSetPoint;
	
    public IntermediatePIDControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {  
    	currentPos = Robot.m_elevator.getElevatorPos();
    	newSetPoint=currentPos;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentPos = Robot.m_elevator.getElevatorPos(); 
    	if(Math.abs(Robot.m_oi.getOperatorJoystick().getY())>0.05){
    		newSetPoint = (currentPos + (3*Robot.m_oi.getOperatorJoystick().getY()));
    	}
    	
    	if(newSetPoint-currentPos>6){
    		newSetPoint= currentPos+6;
    	}
    	else if(newSetPoint-currentPos<-6){
        	newSetPoint= currentPos -6;
    	}
    	if(newSetPoint>60){
    		newSetPoint=60;
    	}
    	if(newSetPoint<9.5){
    		newSetPoint=9.5;
    	}
    	
    	
    	SmartDashboard.putNumber("Elevator Position", currentPos);
    	SmartDashboard.putNumber("Elevator User Target", newSetPoint);
    	SmartDashboard.putNumber("Elevator Current Target", Robot.m_elevator.m_elevatorPIDController.getSetpoint());

    	
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
