package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveArcade extends Command {
	
	public SetDriveArcade() {
        requires(Robot.m_driveTrain);
    }


	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {

    	Robot.m_driveTrain.setDriveArcade();  //the function
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		end();
	}

}
