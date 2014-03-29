package fisherjk;

import java.util.ArrayList;

/*Class to store the complete set of rules after running the APrioriAlgorithm*/
public class RuleSet {

	private ArrayList<Rule> ruleSet;//instance variable to access the ruleSet
	private String datetime;

	
	/*Constructors*/
	public RuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	public RuleSet(){
		this.ruleSet = new ArrayList<Rule>();
	}

	/*Respective Getters and Setters*/
	public ArrayList<Rule> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	/*Override the toString to carefuly print each ruleSets content*/
	@Override
	public String toString() {
		String strRuleSets = "";
		for(int i = 0; i < this.ruleSet.size(); i++){
			strRuleSets = strRuleSets + this.ruleSet.get(i).toString() + "\n"; 
		}
		return strRuleSets;
	}
		
}
