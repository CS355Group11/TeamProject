package fisherjk;

import java.util.ArrayList;

public class RuleSet {

	private ArrayList<Rule> ruleSet;

	//List of Rules
	public RuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	public RuleSet(){
		this.ruleSet = new ArrayList<Rule>();
	}

	public ArrayList<Rule> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}

	@Override
	public String toString() {
		String strRuleSets = "";
		for(int i = 0; i < this.ruleSet.size(); i++){
			strRuleSets = strRuleSets + this.ruleSet.get(i).toString() + "\n"; 
		}
		
		
		
		return strRuleSets;
	}
	
	

	
}
