package edu.uwec.cs355.group11.utilities;

import java.util.ArrayList;


public class TimerLogs {
	private String methodName;
	private ArrayList<String> timerLogs;
	private String totalTime;
	
	//private long totalTime;			// difference of stop and start time
	
	// --- default constructor
	public TimerLogs() {
		timerLogs = new ArrayList<String>();
		//totalTime = 0;
	}
	
	
	
	public ArrayList<String> getTimerLogs() {
		return timerLogs;
	}


	public void setTimerLogs(ArrayList<String> timerLogs) {
		this.timerLogs = timerLogs;
	}


	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}



	@Override
	public String toString() {
		String report = "";
		for(int i = 0; i < this.timerLogs.size(); i++){
				report = this.methodName + this.totalTime+ " \n";
		}
		return report;
	}
	
	
	
	
}	// end - class Timer