package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.RightGrabberDoNothing;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *Subsystem for controlling right solenoids
 */
public class RightGrabber extends Subsystem {
    Solenoid smallRightArmSolenoid = RobotMap.grabberSmallRightArmSolenoid;
    Solenoid bigRightArmSolenoid = RobotMap.grabberBigRightArmSolenoid;


    public void initDefaultCommand() {	
    	setDefaultCommand(new RightGrabberDoNothing());
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
    		smallRightArmSolenoid.set(true);
    		bigRightArmSolenoid.set(true);
    	}
    	else if (rightArmPosition == 1){
    		smallRightArmSolenoid.set(false);
    		bigRightArmSolenoid.set(true);
    	}
    	else if (rightArmPosition == 2){
    		smallRightArmSolenoid.set(true);
    		bigRightArmSolenoid.set(false);
    	}
    	else if (rightArmPosition == 3){
    		smallRightArmSolenoid.set(false);
    		bigRightArmSolenoid.set(false);
    	}
    	else{
    		smallRightArmSolenoid.set(true);
    		bigRightArmSolenoid.set(true);
    	}
    		
    }
}

