package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SetToArcadeMode extends CommandGroup {
    
    public  SetToArcadeMode() {

    	Robot.m_driveTrain.setDriveArcade(); // Set the default command of the drive train to arcade drive
        
        // Check the state of the manual strafe button
        boolean strafeButtonPressed = Robot.m_oi.actuateStrafe.get();
        
        // If the button is down, lift the wheel
        if (strafeButtonPressed == true) {
        	addParallel(new ActuateStrafeUp());
        } else if (strafeButtonPressed == false) {
        	addParallel(new ActuateStrafeDown());
        }
    }
}
