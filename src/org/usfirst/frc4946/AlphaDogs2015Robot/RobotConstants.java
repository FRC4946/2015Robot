package org.usfirst.frc4946.AlphaDogs2015Robot;

public final class RobotConstants {

	public static final double ELEVATOR_MAX_OUTPUT = 0.8;
	public static final double ELEVATOR_MINIMUM_HEIGHT = 8.2;
	public static final double ELEVATOR_MAXIMUM_HEIGHT = 66.0;
	
	public static final double DRIVETRAIN_MAX_ACCEL = 0.01;	// This value should be in the unit "Voltage per Cycle".

	
	
	
	
	public static final double AUTONOMOUS_ELEVATOR_PICKUP_HEIGHT = 8.1;	// Underneath the lips of the bottom tote. Go here to prep for pickup
	public static final double AUTONOMOUS_ELEVATOR_TRANSPORT_HEIGHT = 15;	// Lifts the tote to just off the ground
	public static final double AUTONOMOUS_ELEVATOR_ABOVE_TOTE_HEIGHT = 26;	// Lifts the tote to just above another tote
	public static final double AUTONOMOUS_ELEVATOR_DROP_ON_TOTE_HEIGHT = 20;	// Drops a tote onto another tote
	
	public static final double AUTONOMOUS_DELAY_ACTUATE_ARMS = 0.1; // How long to wait after opening/closing the arms
	public static final double AUTONOMOUS_DELAY_AFTER_DRIVE = 0.05; // How long to wait after driving/turning the robot
	
	public static final double AUTONOMOUS_DRIVE_TO_NEXT_TOTE_SPEED = 0.8;	// The speed at which to drive 6'9" from one tote to another
	public static final double AUTONOMOUS_DRIVE_TO_NEXT_TOTE_TIMEOUT = 3;	// How long it takes us to drive from one tote to the next. Should be 6'9"
	public static final double AUTONOMOUS_DRIVE_CLEAR_TOTE_SPEED = 0.5;		// The speed at which to drive ~2" back away from the initial pos
	public static final double AUTONOMOUS_DRIVE_CLEAR_TOTE_TIMEOUT = 2;		// For how long to drive back ~2"
	public static final double AUTONOMOUS_DRIVE_INTO_ZONE_SPEED = 0.8;		// The speed at which to drive 12" into the auto zone
	public static final double AUTONOMOUS_DRIVE_INTO_ZONE_TIMEOUT = 5.2;	// For how long to drive into the auto zone, 12"
	public static final double AUTONOMOUS_DRIVE_FINISH_SPEED = 1.0;			// The speed at which to drive 1" away from the dropped totes
	public static final double AUTONOMOUS_DRIVE_FINISH_TIMEOUT = 0.75;		// For how long to drive away from the dropped totes

}