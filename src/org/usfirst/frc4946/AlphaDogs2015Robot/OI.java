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

    	
    	
        
    	// =*=*=*=*=*= Driver Joystick =*=*=*=*=*=
    	        
        driveJoystick = new Joystick(0);
        
        lowGear = new JoystickButton(driveJoystick, 1);		// Button 1 shifts the gearboxes into low gear
        lowGear.whenPressed(new ShiftLowGear());
        highGear = new JoystickButton(driveJoystick, 2);	// Button 2 shifts the gearboxes into high gear
        highGear.whenPressed(new ShiftHighGear());
        
        actuateStrafe = new JoystickButton(driveJoystick, 3);	// Button 3 actuates the strafe wheel dropper.
        actuateStrafe.whenPressed(new ActuateStrafeUp());		// Lift the wheel whenever the button is held
        actuateStrafe.whenReleased(new ActuateStrafeDown());
        
        toggleDriveMode = new JoystickButton(driveJoystick, 5);	// Button 5 toggles driving modes.
        toggleDriveMode.whenPressed(new SetToStrafeMode());		// Strafe driving is active whenever the button is held
        toggleDriveMode.whenReleased(new SetToArcadeMode());
        
        
        
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Shift High Gear", new ShiftHighGear());

        SmartDashboard.putData("Shift Low Gear", new ShiftLowGear());

        SmartDashboard.putData("Drive Forwards", new DriveForwards());

        SmartDashboard.putData("Drive Reverse", new DriveReverse());

        SmartDashboard.putData("Strafe Left", new StrafeLeft());

        SmartDashboard.putData("Strafe Right", new StrafeRight());

        SmartDashboard.putData("Turn to Angle", new TurntoAngle());

        SmartDashboard.putData("Drive With Joystick Arcade", new DriveWithJoystickArcade());

        SmartDashboard.putData("Drive With Joystick Strafe", new DriveWithJoystickStrafe());

        SmartDashboard.putData("Elevator Go To Height 1", new ElevatorGoToHeight1());

        SmartDashboard.putData("Elevator Go To Height 2", new ElevatorGoToHeight2());

        SmartDashboard.putData("Elevator Go To Height 3", new ElevatorGoToHeight3());

        SmartDashboard.putData("Elevator Go To Height 4", new ElevatorGoToHeight4());

        SmartDashboard.putData("Elevator User Override", new ElevatorUserOverride());
        
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

