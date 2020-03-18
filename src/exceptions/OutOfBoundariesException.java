package exceptions;
public class OutOfBoundariesException extends Exception {
	public OutOfBoundariesException(){
		super("Given Point is out of the Set");
	}
}
