package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {
    
    Solenoid leftFeederSolenoid = RobotMap.feederLeftSolenoid;
    Solenoid rightFeederSolenoid = RobotMap.feederRightSolenoid;
    SpeedController leftFeederMotor = RobotMap.feederLeftMotor;
    SpeedController rightFeederMotor = RobotMap.feederRightMotor;

    public void initDefaultCommand() {
       
    }
    public void engageFeederArms(boolean isEngaged){
    	if (isEngaged == true){
    		leftFeederSolenoid.set(true);
    		rightFeederSolenoid.set(true);
    		leftFeederMotor.set(1);
    		rightFeederMotor.set(1);
    	}
    	else {
    		leftFeederSolenoid.set(false);
    		rightFeederSolenoid.set(false);
    		leftFeederMotor.set(0);
    		rightFeederMotor.set(0);
    		
    	}
    }
}


