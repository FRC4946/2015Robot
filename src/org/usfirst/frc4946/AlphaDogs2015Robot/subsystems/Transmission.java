package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.transmission.TransmissionDoNothing;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Transmission extends Subsystem {

	DoubleSolenoid m_gearShifterSolenoid = RobotMap.driveTrainGearShifterSolenoid;


	/**
	 * Shift the gearbox into either high or low gear
	 * 
	 * @param isHigh Whether to shift into high gear or not. 0 disables the solenoid, 1 shifts to low gear, 2 shifts to high gear.
	 */
	public void setGear(int isHigh) {

		if (isHigh == 1) {
			m_gearShifterSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (isHigh == 2) {
			m_gearShifterSolenoid.set(DoubleSolenoid.Value.kReverse);
		} else {
			m_gearShifterSolenoid.set(DoubleSolenoid.Value.kOff);
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TransmissionDoNothing());
	}
}

