package org.usfirst.frc4946.AlphaDogs2015Robot;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public JoystickButton highGear;
    public JoystickButton lowGear;
    public JoystickButton actuateStrafe;
    public Joystick driveJoystick;
    public JoystickButton leftGrabber;
    public JoystickButton rightGrabberPosition0;
    public JoystickButton rightGrabberPosition1;
    public JoystickButton rightGrabberPosition2;
    public JoystickButton rightGrabberPosition3;
    public JoystickButton toggleDriveMode;
    public Joystick operatorJoystick;
    
    public JoystickButton feederArmToggle;
    public JoystickButton feederMotorButtonCW;
    public JoystickButton feederMotorButtonCCW;
    public JoystickButton feederMotorButtonIn;
    public JoystickButton feederMotorButtonOut;
    
    
    
    public OI() {

    	
    	
    	// =*=*=*=*=*= Operator Joystick =*=*=*=*=*=
    	
        operatorJoystick = new Joystick(1);
        
        leftGrabber = new JoystickButton(operatorJoystick, 1);	// Button 1 actuates the left arm.
        leftGrabber.whenPressed(new SetLeftGrabberArm(true));	// Actuate the left arm in when button is held,
        leftGrabber.whenReleased(new SetLeftGrabberArm(false));	// And actuate it out when the button is released

        rightGrabberPosition0 = new JoystickButton(operatorJoystick, 2);	// Button 2 brings the right arm all the way in
        rightGrabberPosition0.whenPressed(new SetRightGrabberArm(0));
        rightGrabberPosition2 = new JoystickButton(operatorJoystick, 3);	// Button 3 puts the right arm in the middle position
        rightGrabberPosition2.whenPressed(new SetRightGrabberArm(2));
        rightGrabberPosition1 = new JoystickButton(operatorJoystick, 4);	// Button 4 puts the right arm in the short position
        rightGrabberPosition1.whenPressed(new SetRightGrabberArm(1));
        rightGrabberPosition3 = new JoystickButton(operatorJoystick, 5);	// Button 5 puts the right arm in the large position
        rightGrabberPosition3.whenPressed(new SetRightGrabberArm(3));

    	feederArmToggle = new JoystickButton(operatorJoystick, 5);
	    feederMotorButtonCW = new JoystickButton(operatorJoystick, 6);
	    feederMotorButtonCW.whenPressed(new WheelMovement(1));//CW movement I think
	    feederMotorButtonCW.whenReleased(new WheelMovement(0));//stops pls ty
	    feederMotorButtonCCW = new JoystickButton(operatorJoystick, 7);
	    feederMotorButtonCCW.whenPressed(new WheelMovement(2));//CCW movement I think
	    feederMotorButtonCCW.whenReleased(new WheelMovement(0));//stops pls ty
	    feederMotorButtonIn = new JoystickButton(operatorJoystick, 8);
	    feederMotorButtonIn.whenPressed(new WheelMovement(3));//Intake movement I think
	    feederMotorButtonIn.whenReleased(new WheelMovement(0));//stops pls ty
    	
        
    	// =*=*=*=*=*= Driver Joystick =*=*=*=*=*=
    	        
        driveJoystick = new Joystick(0);
        
        lowGear = new JoystickButton(driveJoystick, 1);		// Button 1 shifts the gearboxes into low gear
        lowGear.whenPressed(new ShiftGear(false));
        highGear = new JoystickButton(driveJoystick, 2);	// Button 2 shifts the gearboxes into high gear
        highGear.whenPressed(new ShiftGear(true));
        
        actuateStrafe = new JoystickButton(driveJoystick, 3);		// Button 3 actuates the strafe wheel dropper.
        actuateStrafe.whenPressed(new ActuateStrafeSolenoid(true));	// Lift the wheel whenever the button is held
        actuateStrafe.whenReleased(new ActuateStrafeSolenoid(false));
        
        toggleDriveMode = new JoystickButton(driveJoystick, 5);	// Button 5 toggles driving modes.
        toggleDriveMode.whenPressed(new SetToStrafeMode());		// Strafe driving is active whenever the button is held
        toggleDriveMode.whenReleased(new SetToArcadeMode());
        
        
        
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Shift High Gear", new ShiftGear(true));

        SmartDashboard.putData("Shift Low Gear", new ShiftGear(false));
        
        SmartDashboard.putData("Left Arm Retracted (Postion 0)", new SetLeftGrabberArm(false));

        SmartDashboard.putData("Left Arm Retracted (Postion 1)", new SetLeftGrabberArm(true));
        
        SmartDashboard.putData("Right Arm Position 0", new SetRightGrabberArm(0));
        
        SmartDashboard.putData("Right Arm Postion 1", new SetRightGrabberArm(1));
        
        SmartDashboard.putData("Right Arm Postion 2", new SetRightGrabberArm(2));
        
        SmartDashboard.putData("Right Arm Postion 3", new SetRightGrabberArm(3));

    }
    
    /**
     * Get the Drive joystick
     * 
     * @return The driver's joystick
     */
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

    /**
     * Get the Operator joystick
     * 
     * @return The operator's joystick
     */
    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }

}

