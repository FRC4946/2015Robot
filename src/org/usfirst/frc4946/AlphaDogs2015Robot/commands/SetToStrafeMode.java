package org.usfirst.frc4946.AlphaDogs2015Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SetToStrafeMode extends CommandGroup {
    
    public  SetToStrafeMode() {
        addParallel(new DriveWithJoystickStrafe());
        addParallel(new ActuateStrafeUp());
    }
}
