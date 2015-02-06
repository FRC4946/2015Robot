package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *Subsystem for controlling left and right solenoids
 */
public class Grabber extends Subsystem {
    Solenoid leftArmSolenoid = RobotMap.grabberLeftArmSolenoid;
    Solenoid smallRightArmSolenoid = RobotMap.grabberSmallRightArmSolenoid;
    Solenoid bigRightArmSolenoid = RobotMap.grabberBigRightArmSolenoid;


    public void initDefaultCommand() {	
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
    
    
    /**
     *Controls right arm solenoid
     *Position 0 is at neutral
     *Position 1 is at 2 inches
     *Position 2 is at 4 inches
     *Position 3 is at 6 inches
     */
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

