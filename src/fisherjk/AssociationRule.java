package fisherjk;

public class AssociationRule {

	private ItemSet x;
	private ItemSet y;
	private double minSupportLevel;//must have 4 digits of precision
	private double minConfidenceLevel;//must have 4 digits of precision
	
	
	public AssociationRule(ItemSet x, ItemSet y,double minSupportLevel, double minConfidenceLevel) {
		this.x = x;
		this.y = y;
		this.minSupportLevel = minSupportLevel;
		this.minConfidenceLevel = minConfidenceLevel;
	}
	
	public AssociationRule(){
		this.x = new ItemSet();
		this.y = new ItemSet();
		this.minSupportLevel = 0;
		this.minConfidenceLevel = 0;
	}


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

	@Override
	public String toString() {
		return "IF " +  this.x + " THEN " + this.y + " (" + minConfidenceLevel + ") Support:" + minSupportLevel;
	}
	
	
	
	
	
}
