package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.GrabberDoNothing;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *Subsystem for controlling left solenoid
 */
public class LeftGrabber extends Subsystem {
    Solenoid leftArmSolenoid = RobotMap.grabberLeftArmSolenoid;


    public void initDefaultCommand() {	
    	setDefaultCommand(new GrabberDoNothing());
    }
    
    /**Controls left arm solenoid
     * 
     */
    public void setLeftArm(boolean isExtended){
    	if(isExtended == true){
    		leftArmSolenoid.set(true);
    	}
    	else{
    		leftArmSolenoid.set(false);
    	}
    }
}

