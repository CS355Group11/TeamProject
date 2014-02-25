package fisherjk;

import java.util.ArrayList;

public class Rule {
	
	private ArrayList<ItemSet> rule;
	private double actualConfidenceLevel;//4 digits of precision
	
	public Rule(ArrayList<ItemSet> rule, double actualConfidenceLevel) {
		this.rule = rule;
		this.actualConfidenceLevel = actualConfidenceLevel;
	}

	public ArrayList<ItemSet> getRule() {
		return rule;
	}

	public void setRule(ArrayList<ItemSet> rule) {
		this.rule = rule;
	}

	public double getActualConfidenceLevel() {
		return actualConfidenceLevel;
	}

	public void setActualConfidenceLevel(double actualConfidenceLevel) {
		this.actualConfidenceLevel = actualConfidenceLevel;
	}

	

	
	

}
