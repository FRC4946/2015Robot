package org.usfirst.frc4946.AlphaDogs2015Robot.util;

import edu.wpi.first.wpilibj.PIDOutput;

public interface DummyPIDOutputInterface extends PIDOutput {
	
    /**
     * Common interface for getting the current set speed of a speed controller.
     *
     * @return The current set speed.  Value is between -1.0 and 1.0.
     */
    double get();
	
}
