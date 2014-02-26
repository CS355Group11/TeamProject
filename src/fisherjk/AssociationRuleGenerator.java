package fisherjk;

public class AssociationRuleGenerator {

	
	private ItemSet x;
	private ItemSet y;
	private double minSupportLevel;//must have 4 digits of precision
	private double minConfidenceLevel;//must have 4 digits of precision
	
	
	public AssociationRuleGenerator(ItemSet x, ItemSet y,
			double minSupportLevel, double minConfidenceLevel) {
		this.x = x;
		this.y = y;
		this.minSupportLevel = minSupportLevel;
		this.minConfidenceLevel = minConfidenceLevel;
	}
	
	
	/*Part 1: First generate possible association rules from final frequent item set collection – all permutations
	 * 
	 */
	
	
	/*Part 2: Calculating Confidence
	 * 
	 * Confidence(X => Y) = P(Y|X) = ( Support_count(X U Y) / Support_count (X) )
	 * Support_count(X U Y) = number of transactions containing all elements in X and Y
	 * Support_count(X) = number of transactions containing all elements in X
	 */
	
	/*More to discuss

	
	
	
	


	public ItemSet getX() {
		return x;
	}


	public void setX(ItemSet x) {
		this.x = x;
	}


	public ItemSet getY() {
		return y;
	}


	public void setY(ItemSet y) {
		this.y = y;
	}


	public double getMinSupportLevel() {
		return minSupportLevel;
	}


	public void setMinSupportLevel(double minSupportLevel) {
		this.minSupportLevel = minSupportLevel;
	}


	public double getMinConfidenceLevel() {
		return minConfidenceLevel;
	}


	public void setMinConfidenceLevel(double minConfidenceLevel) {
		this.minConfidenceLevel = minConfidenceLevel;
	}
	
	
	
	
	
	
}
