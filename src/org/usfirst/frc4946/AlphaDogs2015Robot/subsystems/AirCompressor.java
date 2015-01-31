package org.usfirst.frc4946.AlphaDogs2015Robot.subsystems;

import org.usfirst.frc4946.AlphaDogs2015Robot.RobotMap;
import org.usfirst.frc4946.AlphaDogs2015Robot.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Matthew
 */
public class AirCompressor extends Subsystem {
	Compressor m_compressor = RobotMap.airCompressorCompressor;

	public void initDefaultCommand() {
		m_compressor.start();
	}
}
