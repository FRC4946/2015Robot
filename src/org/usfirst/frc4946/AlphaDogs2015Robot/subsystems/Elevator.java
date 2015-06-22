// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotConstants;
import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.elevator.IntermediatePIDControl;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    SpeedController m_elevatorMotor = RobotMap.elevatorElevatorMotor;
    AnalogPotentiometer m_analogPotentiometer = RobotMap.elevatorAnalogPotentiometer;
    public PIDController m_elevatorPIDController = new PIDController(0.2, 0.0, 0.0, m_analogPotentiometer, m_elevatorMotor);
    DigitalInput m_bottomLimitSwitch = RobotMap.elevatorBottomLimitSwitch;
    DigitalInput m_topLimitSwitch = RobotMap.elevatorTopLimitSwitch;

    double q_0 = 0.0; // The initial position when a new setPoint is set
    double q_f = 0.0; // The final position, or the setPoint
    double t_i = 0.0; // The initial time when a new setPoint is set
    double t_f = 0.0; // The final time, or when the movement is expected to finish

    
    
    private boolean m_PIDisAtPosition = false;
    private boolean m_isPIDControl = false;
    private boolean m_isCarryMode;
    
    // Initialize your subsystem here
    public Elevator(double kP, double kI, double kD) {
        m_elevatorPIDController.setAbsoluteTolerance(0.2);        
        
        // Default to closed-loop operation mode
        setControlMode(true);
      
        m_elevatorPIDController.setInputRange(RobotConstants.ELEVATOR_MINIMUM_HEIGHT, RobotConstants.ELEVATOR_MAXIMUM_HEIGHT);
        m_elevatorPIDController.setOutputRange(-1 * RobotConstants.ELEVATOR_MAX_OUTPUT, RobotConstants.ELEVATOR_MAX_OUTPUT);

    }
    
    public void initDefaultCommand() {    
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntermediatePIDControl());
        
        // Default to closed-loop operation mode
        setControlMode(true);
       
    }
     	
    /**
     * Get the current position of the elevator, according to the linear transducer
     * 
     * @return The height of the elevator, in inches
     */
    public double getElevatorPos() {
    	return m_analogPotentiometer.get();
    }
    
    
    public void manualMoveElevator(double joystickValue) {
    	
    	double curPos = m_analogPotentiometer.get();
    	if (curPos > RobotConstants.ELEVATOR_MINIMUM_HEIGHT &&
    			curPos < RobotConstants.ELEVATOR_MAXIMUM_HEIGHT){
    		m_elevatorMotor.set(joystickValue);
    	} else if(curPos < RobotConstants.ELEVATOR_MINIMUM_HEIGHT){
    		m_elevatorMotor.set(0.2);
    	} else if(curPos > RobotConstants.ELEVATOR_MAXIMUM_HEIGHT){
     		m_elevatorMotor.set(-0.2);
     	}
    }

    /**
     * Calculate the current desired position, and pass that position to the PID controller.
     * 
     */
    public void updateTrajectoryTrack(){
    	double t_now = System.currentTimeMillis()/1000.0;
    	double t_elapsed = (t_now - t_i);
    	double q_now = q_0 + ((-3* ((q_f-q_0)/(-0.5*t_f*t_f*t_f)) *t_f )/2)*t_elapsed*t_elapsed + ((q_f-q_0)/(-0.5*t_f*t_f*t_f))*t_elapsed*t_elapsed*t_elapsed;
    	
    	m_PIDisAtPosition = false;

    	
    	// If the elevator's position delta is too large (Greater than 6in) stop the motor
    	if(Math.abs(getElevatorPos() - q_now) > 6){
    		m_elevatorPIDController.setSetpoint(getElevatorPos());
    	}
    	
    	// Once the desired time has been reached, stop calculating and updating the desired position
    	if(t_elapsed > t_f){
    		m_elevatorPIDController.setSetpoint(q_f);
    		m_PIDisAtPosition = true;
    	}
    	else{
    		m_elevatorPIDController.setSetpoint(q_now);
        	m_PIDisAtPosition = false;
    	}
    }
    
    /**
     * Calculate the final position and time to be used in the trajectory tracking controller.
     * This should be called when a new setpoint is first set.
     * 
     * @param setPos The height to move to, in inches
     */
	public void setFinalTarget(double setPos) {
		q_0 = getElevatorPos();
		t_i = System.currentTimeMillis()/1000.0;
		q_f = setPos;

		t_f = 1.5*	Math.abs(q_f-q_0)/RobotConstants.ELEVATOR_MAX_VELOCITY;

		m_PIDisAtPosition = false;
	}
	
	/**
	 * Set the mode of the elevator motor to either closed-loop control or open-loop control
	 * 
	 * @param modeIsPID If the control mode should be set to closed-loop PID control. [true] for closed-loop, [false] for open
	 */
	public void setControlMode(boolean modeIsPID) {		
		m_isPIDControl = modeIsPID;
		
		if(m_isPIDControl){
			m_elevatorPIDController.enable();
		} else {
			m_elevatorPIDController.disable();
		}
	}
	
	public boolean getPIDisAtPosition() {
		return m_PIDisAtPosition;
	}
	
	public boolean getCarryMode() {
		return m_isCarryMode;
	}
	
	public void setCarryMode(boolean carryMode) {
		m_isCarryMode = carryMode;
	}
}
