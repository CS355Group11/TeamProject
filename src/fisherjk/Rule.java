package fisherjk;

import java.io.Serializable;



/*Class to hold information about individual Rules for a transaction*/
public class Rule implements Serializable {

	private static final long serialVersionUID = 1L;
	private ItemSet antecedent;//IF part of the rule
	private ItemSet consequent;//THEN part of the rule
	private double minSupportLevel;// must have 4 digits of precision
	private double actualConfidenceLevel;// must have 4 digits of precision
	private int ruleSet_ID = 0;


	/*Constructors*/
	public Rule(ItemSet antecedent, ItemSet consequent, double minSupportLevel,
			double actualConfidenceLevel) {
		this.antecedent = antecedent;
		this.consequent = consequent;
		this.minSupportLevel = minSupportLevel;
		this.actualConfidenceLevel = actualConfidenceLevel;
	}

	public Rule() {
		this.antecedent = new ItemSet();
		this.consequent = new ItemSet();
		this.minSupportLevel = 0;
		this.actualConfidenceLevel = 0;
	}

	/*Respective Getters and Setters*/
	public ItemSet getX() {
		return antecedent;
	}

	public void setX(ItemSet x) {
		this.antecedent = x;
	}

	public ItemSet getY() {
		return consequent;
	}

	public void setY(ItemSet y) {
		this.consequent = y;
	}

	public double getMinSupportLevel() {
		return minSupportLevel;
	}

	public void setMinSupportLevel(double minSupportLevel) {
		this.minSupportLevel = minSupportLevel;
	}

	public double getActualConfidenceLevel() {
		return actualConfidenceLevel;
	}

	public void setActualConfidenceLevel(double actualConfidenceLevel) {
		this.actualConfidenceLevel = actualConfidenceLevel;
	}

	public int getRuleSet_ID() {
		return ruleSet_ID;
	}

	public void setRuleSet_ID(int ruleSet_ID) {
		this.ruleSet_ID = ruleSet_ID;
	}

	/*Override to correctly display rule set with confidence level to 4 decimal places of precision*/
	@Override
	public String toString() {
		String strConfidence = this.actualConfidenceLevel+"";
		int zeroes = 0;
		if(strConfidence.length()!=5){
			zeroes = 5 - strConfidence.length();
		for(int i = 0; i < zeroes; i++){
			strConfidence = strConfidence + "0";
			
		}
		}
		
		
		return "IF " + this.antecedent.toStringWithoutSupport() + " THEN "
				+ this.consequent.toStringWithoutSupport() + " ("
				+ strConfidence + ")";
	}

	


}
