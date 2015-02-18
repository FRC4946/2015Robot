package org.usfirst.frc4946.AlphaDogs2015Robot;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.SetGyroMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.SetToArcadeMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.SetToStrafeMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ToggleLimitAcceleration;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.SetLeftGrabberArm;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.SetRightGrabberArm;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.ToggleLeftArm;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.strafedropper.ActuateStrafeSolenoid;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.transmission.ShiftGear;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.command.Command;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//public String WOW = "SUCH ROBOT MUCH ALPHACA DOGE"; 
    public JoystickButton highGear;
    public JoystickButton lowGear;
    public JoystickButton actuateStrafe;
    
    public JoystickButton leftGrabber;
    public JoystickButton rightGrabberPosition0;
    public JoystickButton rightGrabberPosition1;
    public JoystickButton rightGrabberPosition2;
    public JoystickButton rightGrabberPosition3;
    public JoystickButton setPIDMode;
    public JoystickButton setManualMode;
    public JoystickButton setElevatorPosition1;
    public JoystickButton setElevatorPosition2;
    public JoystickButton setElevatorPosition3;
    public JoystickButton setElevatorPosition4;   
	public JoystickButton togglePlaceCarry;
	
    public JoystickButton toggleGyroMode;
    public JoystickButton gyroReset;
    
    public JoystickButton toggleLimitAcceleration;
    
    public Joystick driveJoystick;
    public Joystick operatorJoystick;
    
    //public JoystickButton feederArmToggle;
    //public JoystickButton feederMotorButtonCW;
    //public JoystickButton feederMotorButtonCCW;
    //public JoystickButton feederMotorButtonIn;
    //public JoystickButton feederMotorButtonOut;
    
    
    public double[] kElevatorPresets = {
			(8.5 + 3.6),
			(20 + 3.6),
			(31.5 + 3.6),
			(43 + 3.6)
	};
	
    
    public OI() {

    	
    	
    	// =*=*=*=*=*= Operator Joystick =*=*=*=*=*=
    	
        operatorJoystick = new Joystick(1);
        
        leftGrabber = new JoystickButton(operatorJoystick, 1);	// Button 1 actuates the left arm.
        leftGrabber.whenPressed(new ToggleLeftArm());
        //leftGrabber.whenPressed(new SetLeftGrabberArm(true));	// Actuate the left arm in when button is held,
        //leftGrabber.whenReleased(new SetLeftGrabberArm(false));	// And actuate it out when the button is released

        rightGrabberPosition2 = new JoystickButton(operatorJoystick, 3);	// Button 3 puts the right arm in the middle position
        rightGrabberPosition2.whenPressed(new SetRightGrabberArm(2));
        //rightGrabberPosition2.whenReleased(new SetRightGrabberArm(0));

        rightGrabberPosition1 = new JoystickButton(operatorJoystick, 4);	// Button 4 puts the right arm in the short position
        rightGrabberPosition1.whenPressed(new SetRightGrabberArm(1));
        //rightGrabberPosition1.whenReleased(new SetRightGrabberArm(0));

        rightGrabberPosition3 = new JoystickButton(operatorJoystick, 5);	// Button 5 puts the right arm in the large position
        rightGrabberPosition3.whenPressed(new SetRightGrabberArm(3));
       // rightGrabberPosition3.whenReleased(new SetRightGrabberArm(0));
        
        rightGrabberPosition0 = new JoystickButton(operatorJoystick, 2);	// Button 5 puts the right arm in the large position
        rightGrabberPosition0.whenPressed(new SetRightGrabberArm(0));

        
        
        setManualMode = new JoystickButton(operatorJoystick, 9);
        setManualMode.whenPressed(new SetElevatorMode(false));	// Button 5 puts the right arm in the large position
        setManualMode.whileHeld(new ElevatorMoveManual());	// Button 5 puts the right arm in the large position
        setManualMode.whenReleased(new SetElevatorMode(true));

        setElevatorPosition1 = new JoystickButton(operatorJoystick, 6);	// Button 5 puts the right arm in the large position
        setElevatorPosition1.whenPressed(new SetElevatorMode(true));
       	setElevatorPosition1.whileHeld(new ElevatorMoveToPosition(kElevatorPresets[0]));    

        setElevatorPosition2 = new JoystickButton(operatorJoystick, 7);	// Button 5 puts the right arm in the large position
        setElevatorPosition2.whenPressed(new SetElevatorMode(true));
        setElevatorPosition2.whileHeld(new ElevatorMoveToPosition(kElevatorPresets[1]));	// Button 5 puts the right arm in the large position
 
        setElevatorPosition3 = new JoystickButton(operatorJoystick, 10);	// Button 5 puts the right arm in the large position
        setElevatorPosition3.whenPressed(new SetElevatorMode(true));
        setElevatorPosition3.whileHeld(new ElevatorMoveToPosition(kElevatorPresets[2]));	// Button 5 puts the right arm in the large position

        setElevatorPosition4 = new JoystickButton(operatorJoystick, 11);	// Button 5 puts the right arm in the large position
        setElevatorPosition4.whenPressed(new SetElevatorMode(true));
        setElevatorPosition4.whileHeld(new ElevatorMoveToPosition(kElevatorPresets[3]));
        
        togglePlaceCarry = new JoystickButton(operatorJoystick, 8);
        togglePlaceCarry.whenPressed(new ToggleCarry(false));
        togglePlaceCarry.whenReleased(new ToggleCarry(true));
        
        
    	//feederArmToggle = new JoystickButton(operatorJoystick, 5);
	    //feederMotorButtonCW = new JoystickButton(operatorJoystick, 6);
	    //feederMotorButtonCW.whenPressed(new WheelMovement(1));//CW movement I think
	    //feederMotorButtonCW.whenReleased(new WheelMovement(0));//stops pls ty
	    //feederMotorButtonCCW = new JoystickButton(operatorJoystick, 7);
	    //feederMotorButtonCCW.whenPressed(new WheelMovement(2));//CCW movement I think
	    //feederMotorButtonCCW.whenReleased(new WheelMovement(0));//stops pls ty
	    //feederMotorButtonIn = new JoystickButton(operatorJoystick, 8);
	    //feederMotorButtonIn.whenPressed(new WheelMovement(3));//Intake movement I think
	    //feederMotorButtonIn.whenReleased(new WheelMovement(0));//stops pls ty
    	
        
    	// =*=*=*=*=*= Driver Joystick =*=*=*=*=*=
    	        
        driveJoystick = new Joystick(0);
        
        lowGear = new JoystickButton(driveJoystick, 3);		// Button 3 shifts the gearboxes into low gear
        lowGear.whenPressed(new ShiftGear(false));
        highGear = new JoystickButton(driveJoystick, 4);	// Button 4 shifts the gearboxes into high gear
        highGear.whenPressed(new ShiftGear(true));
        
        actuateStrafe = new JoystickButton(driveJoystick, 1);		// Trigger actuates the strafe wheel dropper.
        actuateStrafe.whenPressed(new ActuateStrafeSolenoid(false));	// Lift the wheel whenever the button is held
        actuateStrafe.whenReleased(new ActuateStrafeSolenoid(true));

        // Gyro Buttons
        toggleGyroMode = new JoystickButton(driveJoystick, 5);
        toggleGyroMode.whenPressed(new SetGyroMode(true));
        toggleGyroMode.whenReleased(new SetGyroMode(false));
        
        gyroReset = new JoystickButton(driveJoystick, 7);
        gyroReset.whenPressed(new ResetGyro());
        
        // Not properly implemented
        toggleLimitAcceleration = new JoystickButton(driveJoystick, 8);
        toggleLimitAcceleration.whenPressed(new ToggleLimitAcceleration());
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
