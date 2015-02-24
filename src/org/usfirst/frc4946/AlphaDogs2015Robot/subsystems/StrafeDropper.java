package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.strafedropper.DropperDoNothing;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class StrafeDropper extends Subsystem {
	DoubleSolenoid m_wheelDropperSolenoid = RobotMap.driveTrainWheelDropperSolenoid;

	/**
	 * Lower or raise the middle wheel.
	 * 
	 * @param isRaised Whether to raise the wheel or not. 0 disables the solenoid, 1 lowers the wheel, 2 raises it.
	 */
	public void setDropWheel(int isRaised) {

		if (isRaised == 1) {
			m_wheelDropperSolenoid.set(DoubleSolenoid.Value.kForward);
		} else if (isRaised == 2) {
			m_wheelDropperSolenoid.set(DoubleSolenoid.Value.kReverse);
		} else {
			m_wheelDropperSolenoid.set(DoubleSolenoid.Value.kOff);
		}
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DropperDoNothing());
    }
}