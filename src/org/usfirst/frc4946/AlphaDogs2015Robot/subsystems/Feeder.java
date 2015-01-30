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
    public void engageFeederArms(boolean isEngaged){//expands and retracts feeder arms
    	if (isEngaged == true){//expand
    		leftFeederSolenoid.set(true);
    		rightFeederSolenoid.set(true);
    	}
    	else {//retract
    		leftFeederSolenoid.set(false);
    		rightFeederSolenoid.set(false);    		
    	}
    }
    public void engageFeederWheels(int isSucked){//pushes and pulls totes into feeder arms
    	if (isSucked == 1){//pulls
    		leftFeederMotor.set(1);
    		rightFeederMotor.set(1);
    	}
    	else if (isSucked == 2){//pushes
    		leftFeederMotor.set(-1);
    		rightFeederMotor.set(-1);
    	}
    	else {//neutral
    		leftFeederMotor.set(0);
    		rightFeederMotor.set(0);
    	}
    }
}


