package fisherjk;

import java.io.Serializable;

public class Vendor implements Serializable {

	private static final long serialVersionUID = 1L;

private String vendor_name;

public Vendor(String vendor_name){
	this.vendor_name = vendor_name;
}

public Vendor(){
	this.vendor_name = "";
}

public String getVendor_name() {
	return vendor_name;
}

public void setVendor_name(String vendor_name) {
	this.vendor_name = vendor_name;
}


	
}
