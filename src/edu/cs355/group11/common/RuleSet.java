package edu.cs355.group11.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.cs355.group11.service.Rule;

/*Class to store the complete set of rules after running the APrioriAlgorithm*/
public class RuleSet implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Rule> ruleSet;//instance variable to access the ruleSet
	private String timestamp;
	private String defTimeStamp;

	
	/*Constructors*/
	public RuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	public RuleSet(){
		this.ruleSet = new ArrayList<Rule>();
		this.timestamp = "";
	}

	public RuleSet(RuleSet aRuleSet) {
		// TODO Auto-generated constructor stub
		setRuleSet(aRuleSet.getRuleSet());
		setDefTimeStamp(aRuleSet.getDefTimeStamp());
	}

	/*Respective Getters and Setters*/
	public ArrayList<Rule> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	

	public String getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		this.timestamp = strDate;
	}
	

	public String getDefTimeStamp() {
		return defTimeStamp;
	}

	public void setDefTimeStamp(String defTimeStamp) {
		this.defTimeStamp = defTimeStamp;
	}

	/*Override the toString to carefuly print each ruleSets content*/
	@Override
	public String toString() {
		String strRuleSets = "";
		strRuleSets = "Rule Set Generated on: " + this.timestamp + "\n"; 
		for(int i = 0; i < this.ruleSet.size(); i++){
			strRuleSets = strRuleSets + this.ruleSet.get(i).toString() + "\n"; 
		}
		return strRuleSets;
	}
		
}
