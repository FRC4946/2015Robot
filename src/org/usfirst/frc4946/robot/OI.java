// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4946.robot;

import org.usfirst.frc4946.robot.commands.*;
import org.usfirst.frc4946.robot.commands.drivetrain.DriveForwards;
import org.usfirst.frc4946.robot.commands.drivetrain.DriveReverse;
import org.usfirst.frc4946.robot.commands.drivetrain.DriveWithJoystickArcade;
import org.usfirst.frc4946.robot.commands.drivetrain.DriveWithJoystickStrafe;
import org.usfirst.frc4946.robot.commands.drivetrain.ShiftHighGear;
import org.usfirst.frc4946.robot.commands.drivetrain.ShiftLowGear;
import org.usfirst.frc4946.robot.commands.drivetrain.StrafeLeft;
import org.usfirst.frc4946.robot.commands.drivetrain.StrafeRight;
import org.usfirst.frc4946.robot.commands.drivetrain.TurntoAngle;
import org.usfirst.frc4946.robot.commands.elevator.ElevatorGoToHeight1;
import org.usfirst.frc4946.robot.commands.elevator.ElevatorGoToHeight2;
import org.usfirst.frc4946.robot.commands.elevator.ElevatorGoToHeight3;
import org.usfirst.frc4946.robot.commands.elevator.ElevatorGoToHeight4;
import org.usfirst.frc4946.robot.commands.elevator.ElevatorUserOverride;
import org.usfirst.frc4946.robot.commands.grabber.CloseGrabberArms;
import org.usfirst.frc4946.robot.commands.grabber.OpenGrabberArms;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author Matthew
 */
public class OI {
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public OI() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		// SmartDashboard Buttons
		SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

		SmartDashboard.putData("Shift High Gear", new ShiftHighGear());

		SmartDashboard.putData("Shift Low Gear", new ShiftLowGear());

		SmartDashboard.putData("Drive Forwards", new DriveForwards());

		SmartDashboard.putData("Drive Reverse", new DriveReverse());

		SmartDashboard.putData("Strafe Left", new StrafeLeft());

		SmartDashboard.putData("Strafe Right", new StrafeRight());

		SmartDashboard.putData("Turn to Angle", new TurntoAngle());

		SmartDashboard.putData("Drive With Joystick Arcade",
				new DriveWithJoystickArcade());

		SmartDashboard.putData("Drive With Joystick Strafe",
				new DriveWithJoystickStrafe());

		SmartDashboard.putData("Open Grabber Arms", new OpenGrabberArms());

		SmartDashboard.putData("Close Grabber Arms", new CloseGrabberArms());

		SmartDashboard.putData("Elevator Go To Height 1",
				new ElevatorGoToHeight1());

		SmartDashboard.putData("Elevator Go To Height 2",
				new ElevatorGoToHeight2());

		SmartDashboard.putData("Elevator Go To Height 3",
				new ElevatorGoToHeight3());

		SmartDashboard.putData("Elevator Go To Height 4",
				new ElevatorGoToHeight4());

		SmartDashboard.putData("Elevator User Override",
				new ElevatorUserOverride());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}