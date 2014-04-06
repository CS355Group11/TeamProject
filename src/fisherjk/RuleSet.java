package fisherjk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*Class to store the complete set of rules after running the APrioriAlgorithm*/
public class RuleSet {

	private ArrayList<Rule> ruleSet;//instance variable to access the ruleSet
	private String timestamp;

	
	/*Constructors*/
	public RuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	public RuleSet(){
		this.ruleSet = new ArrayList<Rule>();
		this.timestamp = "";
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
