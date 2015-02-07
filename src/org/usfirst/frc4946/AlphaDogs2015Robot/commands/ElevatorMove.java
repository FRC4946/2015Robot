package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class ElevatorMove extends Command {

    public ElevatorMove() {
        requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double elevatorPos = Robot.m_elevator.getElevatorPos();
    	SmartDashboard.putNumber("Elevor Position", elevatorPos);
    	
    	double joystickPositionYVal = Robot.m_oi.getOperatorJoystick().getY();
    	if (joystickPositionYVal > 0){
    		joystickPositionYVal *= 0.1 ;
    		//if (robot arm is below max position according to sensor) {
    		 Robot.m_elevator.moveElevator(joystickPositionYVal);
    		 //}
    	}
    	else if (joystickPositionYVal < 0){
    		//if (robot arm is above min position according to sensor){
    		joystickPositionYVal *= 0.1 ;
    		Robot.m_elevator.moveElevator(joystickPositionYVal);
    		//}
    	}
    	
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