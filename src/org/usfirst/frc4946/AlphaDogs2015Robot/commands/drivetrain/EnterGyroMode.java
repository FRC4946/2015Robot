package org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EnterGyroMode extends CommandGroup {
    
    public  EnterGyroMode() {
    	Robot.m_driveTrain.resetGyroToZero();
    	Robot.m_driveTrain.setGyroMode(true);

    }
}
