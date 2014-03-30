package fisherjk;

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

	@Override
	public String toString() {
		String errors = "";
		for(int i = 0; i < this.errorMsgs.size(); i++){
			errors = errors + "Error # " + (i+1) + " " + this.errorMsgs.get(i).toString() + "\n"; 
		}
		return errors;
	}
	
	
	
	

}
