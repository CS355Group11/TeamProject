package fisherjk;

import java.util.Date;

public class Timer {
	private long startTime;			// start time in absolute milliseconds
	private long stopTime;			// stop time in absolute milliseconds
	//private long totalTime;			// difference of stop and start time
	
	// --- default constructor
	public Timer() {
		startTime = 0;
		stopTime = 0;
		//totalTime = 0;
	}
	
	// --- startTimer - get a starting time
	void startTimer() {
		startTime = System.currentTimeMillis();
	}

	// --- stopTimer - get an ending time
	void stopTimer() {
		stopTime = System.currentTimeMillis();
	}

	// --- getTotal - return the elapsed time
	long getTotal() {
		long result = 0;
		if (stopTime > startTime) {
			result = stopTime - startTime;
		}
		return result;
	}
}	// end - class Timer