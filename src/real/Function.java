package real;
import exceptions.DimException;

public interface Function {
	int outputDim();
	int inputDim();
	Vec act(Vec a) throws DimException;
}