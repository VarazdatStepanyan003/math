package exceptions;
public class DimException extends Exception {
	public DimException(){
		super("Dimensions don't meet the requirement");
	}
}