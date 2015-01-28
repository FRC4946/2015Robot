// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Grabber extends Subsystem {
    Solenoid leftArmSolenoid = RobotMap.grabberLeftArmSolenoid;
    Solenoid smallRightArmSolenoid = RobotMap.grabbersmallRightArmSolenoid;
    Solenoid bigRightArmSolenoid = RobotMap.grabberbigRightArmSolenoid;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**Controls left arm solenoid
     * 
     */
    public void setLeftArm(boolean isExtended){
    	if(isExtended){
    		leftArmSolenoid.set(true);
    	}
    	else{
    		leftArmSolenoid.set(false);
    	}
    }
    
    
    
    public void setRightArm(int rightArmPosition){
    	if(rightArmPosition == 0){
    		smallRightArmSolenoid.set(false);
    		bigRightArmSolenoid.set(false);
    	}
    	else if (rightArmPosition == 1){
    		smallRightArmSolenoid.set(true);
    		bigRightArmSolenoid.set(false);
    	}
    	else if (rightArmPosition == 2){
    		smallRightArmSolenoid.set(false);
    		bigRightArmSolenoid.set(true);
    	}
    	else if (rightArmPosition == 3){
    		smallRightArmSolenoid.set(true);
    		bigRightArmSolenoid.set(true);
    	}
    	else{
    		smallRightArmSolenoid.set(false);
    		bigRightArmSolenoid.set(false);
    	}
    		
    }
}

