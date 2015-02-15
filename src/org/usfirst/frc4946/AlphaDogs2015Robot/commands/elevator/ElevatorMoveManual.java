package org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class ElevatorMoveManual extends Command {

    public ElevatorMoveManual() {
        requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double elevatorPos = Robot.m_elevator.getElevatorPos();
    	SmartDashboard.putNumber("Elevator Position", elevatorPos);
    	
    	double joystickPositionYVal = Robot.m_oi.getOperatorJoystick().getY();
    	
    	joystickPositionYVal *= RobotConstants.ELEVATOR_MAX_OUTPUT ;
   		Robot.m_elevator.manualMoveElevator(joystickPositionYVal);

    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.manualMoveElevator(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}