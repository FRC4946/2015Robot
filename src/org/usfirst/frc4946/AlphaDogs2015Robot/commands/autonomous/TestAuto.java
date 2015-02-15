package org.usfirst.frc4946.AlphaDogs2015Robot.commands.autonomous;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TestAuto extends CommandGroup {
    
	
     public TestAuto() {
		addSequential(new DriveWithGyro(0.0, 0.0));

    }
}
