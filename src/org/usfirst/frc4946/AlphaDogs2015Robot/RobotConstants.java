package org.usfirst.frc4946.AlphaDogs2015Robot;

public final class RobotConstants {

	public static final double ELEVATOR_MAX_OUTPUT = 1.0;
	public static final double ELEVATOR_MINIMUM_HEIGHT = 8.2;
	public static final double ELEVATOR_MAXIMUM_HEIGHT = 66.25;
	
	public static final double DRIVETRAIN_MAX_ACCEL = 0.01;	// This value should be in the unit "Voltage per Cycle".

    public static final double[] kElevatorPresets = {
			(8.5),
			(8.5 + 3.6),
			(20 + 3.6),
			(31.5 + 3.6),
	};
	
	
	
	
	public static final double AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT = 8.1;	// Underneath the lips of the bottom tote. Go here to prep for pickup
	public static final double AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT = 15;	// Lifts the tote to just off the ground
	public static final double AUTONOMOUS_ELEVATOR_ABOVE_TOTE_HEIGHT = 26;	// Lifts the tote to just above another tote
	public static final double AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT = 20;	// Drops a tote onto another tote
	public static final double AUTONOMOUS_ELEVATOR_PICKUP_CONTAINER_HEIGHT = 16;
	public static final double AUTONOMOUS_ELEVATOR_CARRY_CONTAINER_HEIGHT = 24;
	public static final double AUTONOMOUS_ELEVATOR_CONTAINER_ABOVE_TOTE_HEIGHT = 38;
	public static final double AUTONOMOUS_ELEVATOR_CONTAINER_DROP_ON_TOTE_HEIGHT = 25;	// Drops a tote onto another tote



	
	
	public static final double AUTONOMOUS_DELAY_ACTUATE_ARMS = 0.1; // How long to wait after opening/closing the arms
	public static final double AUTONOMOUS_DELAY_AFTER_DRIVE = 0.05; // How long to wait after driving/turning the robot
	public static final double AUTONOMOUS_DELAY_ACTUATE_STRAFE = 0.1; // How long to wait after driving/turning the robot
	
	public static final double AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED = 0.8;	// The speed at which to drive 6'9" from one tote to another
	public static final double AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT = 3;	// How long it takes us to drive from one tote to the next. Should be 6'9"
	public static final double AUTONOMOUS_DRIVE_CLEAR_TOTE_SPEED = 0.5;		// The speed at which to drive ~2" back away from the initial pos
	public static final double AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT = 1;		// For how long to drive back ~2"
	public static final double AUTONOMOUS_DRIVE_INTO_ZONE_SPEED = 0.6;		// The speed at which to drive 12" into the auto zone
	public static final double AUTONOMOUS_DRIVE_INTO_ZONE_TIMEOUT = 1.0;	// For how long to drive into the auto zone, 12"
	public static final double AUTONOMOUS_DRIVE_BACKUP_SPEED = 0.8;			// The speed at which to drive 12" into the auto zone
	public static final double AUTONOMOUS_DRIVE_BACKUP_TIMEOUT = 0.5;		// For how long to drive into the auto zone, 12"

	public static final double AUTONOMOUS_DRIVE_STRAFE_FROM_RC_TO_TOTE_SPEED = 0.8;			// The speed at which to drive 1" away from the dropped totes
	public static final double AUTONOMOUS_DRIVE_STRAFE_FROM_RC_TO_TOTE_TIMEOUT = 0.8;		// For how long to drive away from the dropped totes
	public static final double AUTONOMOUS_DRIVE_FORWARDS_FROM_RC_TO_TOTE_SPEED = 0.3;		// The speed at which to drive 1" away from the dropped totes
	public static final double AUTONOMOUS_DRIVE_FORWARDS_FROM_RC_TO_TOTE_TIMEOUT = 0.9;		// For how long to drive away from the dropped totes
	public static final double ELEVATOR_MAX_VELOCITY = 12;

	public static int autonomousInitialPosition;
	public static int autonomousDirectionOrAmount;

	
}
