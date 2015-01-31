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
    public JoystickButton strafePressed;
    public JoystickButton strafeReleased;
    public Joystick driveJoystick;
    public JoystickButton leftGrabber;
    public JoystickButton rightGrabberPosition0;
    public JoystickButton rightGrabberPosition1;
    public JoystickButton rightGrabberPosition2;
    public JoystickButton rightGrabberPosition3;
    public Joystick operatorJoystick;

    public OI() {


        operatorJoystick = new Joystick(1);
        
        leftGrabber = new JoystickButton(operatorJoystick, 1);
        leftGrabber.whenPressed(new SetLeftGrabberArm(true));
        leftGrabber.whenReleased(new SetLeftGrabberArm(false));

        rightGrabberPosition0 = new JoystickButton(operatorJoystick, 2);
        rightGrabberPosition0.whenPressed(new SetRightGrabberArm(0));
        rightGrabberPosition1 = new JoystickButton(operatorJoystick, 4);
        rightGrabberPosition1.whenPressed(new SetRightGrabberArm(1));
        rightGrabberPosition2 = new JoystickButton(operatorJoystick, 3);
        rightGrabberPosition2.whenPressed(new SetRightGrabberArm(2));
        rightGrabberPosition3 = new JoystickButton(operatorJoystick, 5);
        rightGrabberPosition3.whenPressed(new SetRightGrabberArm(3));
        
        driveJoystick = new Joystick(0);
        
        strafeReleased = new JoystickButton(driveJoystick, 3);
        strafeReleased.whenReleased(new DriveWithJoystickArcade());
        strafePressed = new JoystickButton(driveJoystick, 3);
        strafePressed.whileHeld(new DriveWithJoystickStrafe());
        lowGear = new JoystickButton(driveJoystick, 1);
        lowGear.whenPressed(new ShiftLowGear());
        highGear = new JoystickButton(driveJoystick, 2);
        highGear.whenPressed(new ShiftHighGear());

	    
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
    
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }

}

