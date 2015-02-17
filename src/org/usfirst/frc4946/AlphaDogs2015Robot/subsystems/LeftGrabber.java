package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.grabberarms.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *Subsystem for controlling left solenoid
 */
public class LeftGrabber extends Subsystem {
	private boolean m_isExtended;
	Solenoid leftArmSolenoid = RobotMap.grabberLeftArmSolenoid;


	public void initDefaultCommand() {	
		setDefaultCommand(new LeftGrabberDoNothing());
	}

	/**Controls left arm solenoid
	 * 
	 */
	public void setLeftArm(boolean isExtended){
		m_isExtended = isExtended;
		if(m_isExtended == true){
			leftArmSolenoid.set(false);
		}
		else{
			leftArmSolenoid.set(true);
		}
	}
	
	
	/**Controls left arm solenoid
	 * 
	 */
	public void toggleLeftArm(){
		m_isExtended = !m_isExtended;
		setLeftArm(m_isExtended);
	}
}

