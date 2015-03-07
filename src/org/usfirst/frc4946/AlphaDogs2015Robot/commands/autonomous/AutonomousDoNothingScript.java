package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDoNothingScript extends CommandGroup {
    
    public  AutonomousDoNothingScript() {
    	
    	Robot.setAutonomousStatus("Doing nothing...");
    	
		// End
		addSequential(new SimpleAutoDrive(0.0, 0.0, 0.0)); // Will run forever
    }
}
