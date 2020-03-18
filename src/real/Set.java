package real;
import exceptions.DimException;

public class Set {
	private Function equality;
	private Function inequality;

	public Set(Function eq, Function ineq) throws DimException{
		if(eq.inputDim() != ineq.inputDim()) throw new DimException();
		equality = eq;
		inequality = ineq;
	}

	public boolean hasElement(Vec a) throws DimException{
		return (equality(a) || inequality(a));
	}

	public int inputDim(){
		return equality.inputDim();
	}

	private boolean equality(Vec a) throws DimException{
		return equality.act(a).isZero();
	}

	private boolean inequality(Vec a) throws DimException{
		return inequality.act(a).isNegative();
	}
}
