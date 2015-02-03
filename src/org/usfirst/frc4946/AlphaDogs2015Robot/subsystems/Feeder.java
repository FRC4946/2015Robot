/*package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

*//**
 *
 *//*
public class Feeder extends Subsystem {
    
    Solenoid FeederSolenoid = RobotMap.feederSolenoid;
    SpeedController leftFeederMotor = RobotMap.feederLeftMotor;
    SpeedController rightFeederMotor = RobotMap.feederRightMotor;

    public void initDefaultCommand() {
       
    }
    public void engageFeederArms(boolean isEngaged){//expands and retracts feeder arms
    	if (isEngaged == true){//expand
    		FeederSolenoid.set(true);
    	}
    	else {//retract
    		FeederSolenoid.set(false);	
    	}
    }
    public void engageFeederWheels(int wheelMovementPatternForMovementOfWheelsThatMove){//pushes and pulls totes into feeder arms
    	if (wheelMovementPatternForMovementOfWheelsThatMove == 1){//CW
    		leftFeederMotor.set(1);
    		rightFeederMotor.set(1);
    	}
    	else if (wheelMovementPatternForMovementOfWheelsThatMove == 2){//CCW
    		leftFeederMotor.set(-1);
    		rightFeederMotor.set(-1);
    	}
    	else if (wheelMovementPatternForMovementOfWheelsThatMove == 3){//pulls
    		leftFeederMotor.set(1);
    		rightFeederMotor.set(-1);
    	}
    	else if (wheelMovementPatternForMovementOfWheelsThatMove == 4){//pushes
    		leftFeederMotor.set(-1);
    		rightFeederMotor.set(1);
    	}
    	else {
    		leftFeederMotor.set(0);
    		rightFeederMotor.set(0);
       	}
    }
}


*/