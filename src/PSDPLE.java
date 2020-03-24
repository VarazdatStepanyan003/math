import exceptions.DimException;
import exceptions.OutOfBoundariesException;
import real.Function;
import real.Set;
import real.Vec;

public class PSDPLE {

	public static void main(String[] args) throws DimException, OutOfBoundariesException{
		Function eq = new Function() {
			@Override
			public int outputDim(){
				return 1;
			}

			@Override
			public int inputDim(){
				return 2;
			}

			@Override
			public Vec act(Vec a) throws DimException{
				if(a.getDim() != inputDim()) throw new DimException();
				double[] c = {Math.sqrt(a.norm()) - 1};
				return new Vec(c);
			}
		};
		Function ineq = new Function() {
			@Override
			public int outputDim(){
				return 1;
			}

			@Override
			public int inputDim(){
				return 2;
			}

			@Override
			public Vec act(Vec a) throws DimException{
				if(a.getDim() != inputDim()) throw new DimException();
				double[] c = {a.norm() - 1};
				return new Vec(c);
			}
		};
		Set s = new Set(eq, ineq);
		Function b = new Function() {
			@Override
			public int outputDim(){
				return 1;
			}

			@Override
			public int inputDim(){
				return 2;
			}

			@Override
			public Vec act(Vec a) throws DimException{
				if(a.getDim() != inputDim()) throw new DimException();
				double[] c = {a.norm()};
				return new Vec(c);
			}
		};
		PSDPLE problem = new PSDPLE(s, b);

		double[] a = {0, 0};
		System.out.println(problem.result(new Vec(a), 100));
	}

	private Set s;
	private Function border;

	public PSDPLE(Set s, Function border){
		if(border.inputDim() == s.inputDim()) this.s = s;
		this.border = border;
	}

	public Vec result(Vec a, int n) throws OutOfBoundariesException, DimException{
		if(!s.hasElement(a)) throw new OutOfBoundariesException();
		Vec res = new Vec(border.outputDim());
		int nn = n;
		while(nn > 0){
			res = res.add(border.act(randomChain(a)));
			nn--;
		}
		return res.dot(1.0 / n);
	}

	private Vec randomChain(Vec a) throws DimException{
		Vec c = a;
		Vec b = a.add(new Vec(a.getDim()));
		do{
			c = b;
			b = c.add(randomVec(a.getDim()));
		} while(s.hasElement(b));
		return hit(c, b);
	}

	private Vec randomVec(int dim){
		double a[] = new double[dim];
		for(int i = 0;i < dim;i++){
			a[i] = Math.random() * 0.1 - 0.05;
		}
		return new Vec(a);
	}

	private Vec hit(Vec a, Vec b) throws DimException{
		Vec c = b.add(a.dot(-1));
		c = c.dot(1 / (c.norm() * 20));
		Vec d = a.add(new Vec(a.getDim()));
		do{
			d = d.add(c);
		} while(s.hasElement(d));
		return d;
	}
}