package fileFix;

public class CurrencyConversion {
	private double usDollarToUsDollar = 1; //EdgeCase?
	private double usDollarToJpYen = 145.0;
	private double usDollarToEuro = 0.86;
	private double usDollarToBritishPound = 0.79;
	private double usDollarToCadDollar = 1.36;
	
	public double convertResult(double amount, String userChoice) {
		
	switch (userChoice) {
	
		case "USDOLLAR TO JPYEN" :
			return amount * usDollarToJpYen;
		case "YPYEN TO USDOLLAR" :
			return amount / usDollarToJpYen;
			
		case "USDOLLAR TO EURO" :
			return amount * usDollarToEuro;
		case "EURO TO USDOLLAR" :
			return amount / usDollarToEuro;
			
		case "USDOLLAR TO POUND" :
			return amount * usDollarToBritishPound;
		case "POUND TO USDOLLAR" :
			return amount / usDollarToBritishPound;
			
		case "USDOLLAR TO CADOLLAR" :
			return amount * usDollarToCadDollar;
		case "CADOLLAR TO USDOLLAR" :
			return amount / usDollarToCadDollar;
			
		default:
			return 0;
	}
}

}
