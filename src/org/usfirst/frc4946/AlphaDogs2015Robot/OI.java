package org.usfirst.frc4946.AlphaDogs2015Robot;

import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ActuateStrafeSolenoid;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.SetToArcadeMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.SetToStrafeMode;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.SetLeftGrabberArm;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.SetRightGrabberArm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.command.Command;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

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

    public JoystickButton toggleDriveMode;
    
    public JoystickButton gyroButton0;
    public JoystickButton gyroButton90;
    public JoystickButton gyroButton180;
    public JoystickButton gyroButton270;
    
    public Joystick driveJoystick;
    public Joystick operatorJoystick;
    
    //public JoystickButton feederArmToggle;
    //public JoystickButton feederMotorButtonCW;
    //public JoystickButton feederMotorButtonCCW;
    //public JoystickButton feederMotorButtonIn;
    //public JoystickButton feederMotorButtonOut;
    
    
    
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
        
        setPIDMode = new JoystickButton(operatorJoystick, 8);	// Button 5 puts the right arm in the large position
        setPIDMode.whenPressed(new SetElevatorMode(true));	// Button 5 puts the right arm in the large position
        setManualMode = new JoystickButton(operatorJoystick, 9);
        setManualMode.whenPressed(new SetElevatorMode(false));	// Button 5 puts the right arm in the large position

        setElevatorPosition1 = new JoystickButton(operatorJoystick, 6);	// Button 5 puts the right arm in the large position
        setElevatorPosition1.whenPressed(new ElevatorMoveToPosition(20.0));	// Button 5 puts the right arm in the large position
        setElevatorPosition2 = new JoystickButton(operatorJoystick, 7);	// Button 5 puts the right arm in the large position
        setElevatorPosition2.whenPressed(new ElevatorMoveToPosition(40.0));	// Button 5 puts the right arm in the large position
        setElevatorPosition3 = new JoystickButton(operatorJoystick, 10);	// Button 5 puts the right arm in the large position
        setElevatorPosition3.whenPressed(new ElevatorMoveToPosition(65.0));	// Button 5 puts the right arm in the large position

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
        
        // Gyro Buttons
        gyroButton0 = new JoystickButton(driveJoystick, 5);
        gyroButton0.whenPressed(RotateRobotWithGyro(0));
        gyroButton90 = new JoystickButton(driveJoystick, 6);
        gyroButton180 = new JoystickButton(driveJoystick, 7);
        gyroButton270 = new JoystickButton(driveJoystick, 8);
        
        
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Shift High Gear", new ShiftGear(true));

        SmartDashboard.putData("Shift Low Gear", new ShiftGear(false));

        SmartDashboard.putData("Left Arm Retracted (Postion 0)", new SetLeftGrabberArm(false));

        SmartDashboard.putData("Left Arm Retracted (Postion 1)", new SetLeftGrabberArm(true));

        SmartDashboard.putData("Right Arm Position 0", new SetRightGrabberArm(0));

        SmartDashboard.putData("Right Arm Postion 1", new SetRightGrabberArm(1));

        SmartDashboard.putData("Right Arm Postion 2", new SetRightGrabberArm(2));

        SmartDashboard.putData("Right Arm Postion 3", new SetRightGrabberArm(3));
        
        
        
        
      //  double m_desiredElevatorPosition = SmartDashboard.getNumber("Elevator Height", 30);
        
       // SmartDashboard.putData("Move Elevator", new ElevatorMoveToPosition(m_desiredElevatorPosition));
    }

    
    private Command RotateRobotWithGyro(int i) {
		// TODO Auto-generated method stub
		return null;
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
