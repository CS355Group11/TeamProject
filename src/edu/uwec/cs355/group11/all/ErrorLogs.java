package edu.uwec.cs355.group11.all;

import java.util.ArrayList;

public class ErrorLogs {
	
	
	private ArrayList<String> errorMsgs;

	public ErrorLogs(){
		this.errorMsgs = new ArrayList<String>();
	}
	
	public ArrayList<String> getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(ArrayList<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}
	
	public int getErrorCount(){
		return this.errorMsgs.size();
		
	}
	
	public void add(ErrorLogs errorLogs){
		for(int i = 0; i < errorLogs.getErrorCount(); i++){
			String new_error = errorLogs.getErrorMsgs().get(i);
			this.errorMsgs.add(new_error);
		}
		
	}
	

	@Override
	public String toString() {
		String errors = "";
		for(int i = 0; i < this.errorMsgs.size(); i++){
			errors = errors + "Error # " + (i+1) + " " + this.errorMsgs.get(i).toString() + "\n"; 
		}
		return errors;
	}

	public void add(String error) {
		this.errorMsgs.add(error); 
	
		
	}
	
	
	
	

}
