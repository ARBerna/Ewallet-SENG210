package fileFix;

public class Wage {
	public String source;
	public double amount;
	public String Month;
	
	//should add contructor(s)
	//constructor
	//Blake fixed to make public to call from mainframe
	
	public Wage(String tempSource, double tempAmount, String tempMonth) {
		this.source = tempSource;
		this.amount = tempAmount;
		this.Month  = tempMonth;
		
	}
}