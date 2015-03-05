package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;

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
    	double currentMinimumHeight = RobotConstants.ELEVATOR_RAMP_HEIGHT;
    	
    	if(Math.abs(Robot.m_oi.getOperatorJoystick().getY())>RobotConstants.ELEVATOR_JOYSTICK_DEADZONE){
    		newSetPoint = (currentPos + (RobotConstants.ELEVATOR_MAX_VELOCITY*Robot.m_oi.getOperatorJoystick().getY()));
    		currentMinimumHeight = RobotConstants.ELEVATOR_MINIMUM_HEIGHT;
    	}
    	else{
    		// Leave the setpoint where it last was, but raise the minimum by 2.8 inches
    		currentMinimumHeight = RobotConstants.ELEVATOR_RAMP_HEIGHT;

    	}
    	
    	// Limit how far away from the current position the setPoint can be
    	if(newSetPoint-currentPos>RobotConstants.ELEVATOR_MAX_VELOCITY){
    		newSetPoint= currentPos+RobotConstants.ELEVATOR_MAX_VELOCITY;
    	} else if(newSetPoint-currentPos < -RobotConstants.ELEVATOR_MAX_VELOCITY){
        	newSetPoint= currentPos -RobotConstants.ELEVATOR_MAX_VELOCITY;
    	}
    	
    	// Limit the elevator to within the limits
    	if(newSetPoint > RobotConstants.ELEVATOR_MAXIMUM_HEIGHT){
    		newSetPoint = RobotConstants.ELEVATOR_MAXIMUM_HEIGHT;
    	}
    	if(newSetPoint < currentMinimumHeight){
    		newSetPoint = currentMinimumHeight;
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
