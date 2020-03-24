package real;
import exceptions.DimException;

public class Vec {
	private double[] v;

	public Vec(int dim){
		v = new double[dim];
	}

	public Vec(double[] v){
		this.v = v;
	}

	public int getDim(){
		return v.length;
	}

	public Vec add(Vec a){
		double[] c = new double[getDim()];
		for(int i = 0;i < getDim();i++){
			c[i] = a.v[i] + v[i];
		}
		return new Vec(c);
	}

	public Vec dot(double a){
		double[] c = new double[getDim()];
		for(int i = 0;i < v.length;i++){
			c[i] = a * v[i];
		}
		return new Vec(c);
	}

	public double dot(Vec n) throws DimException{
		if(getDim() != n.getDim()) throw new DimException();
		double res = 0;
		for(int i = 0;i < getDim();i++){
			res += v[i] * n.v[i];
		}
		return res;
	}

	public double norm(){
		double res = 0;
		for(double a: v){
			res += a * a;
		}
		return Math.sqrt(res);
	}

	public boolean isZero(){
		return (norm() < 0.02);
	}

	public boolean isPositive(){
		for(double a: v){
			if(a <= 0) return false;
		}
		return true;
	}

	public boolean isNegative(){
		for(double a: v){
			if(a >= 0) return false;
		}
		return true;
	}

	public double[] getV(){
		return v;
	}

	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(double vv: v){
			s.append(vv);
			if(vv != v[v.length - 1]) s.append(", ");
		}
		return s.toString();
	}
}