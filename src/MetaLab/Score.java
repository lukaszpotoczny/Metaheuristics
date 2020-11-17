package MetaLab;

public class Score {

	private double best;
	private double current;
	private double average;
	private double worst;
	
	public Score(double b, double c) {
		this.best = b;
		this.current = c;
	}
	
	public Score(double b, double a, double w) {
		this.best = b;
		this.average = a;
		this.worst = w;
	}
	
	public Score(double b, double c, double a, double w) {
		this.best = b;
		this.current = c;
		this.average = a;
		this.worst = w;
	}

	public double getBest() {
		return best;
	}

	public void setBest(double best) {
		this.best = best;
	}
	
	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getWorst() {
		return worst;
	}

	public void setWorst(double worst) {
		this.worst = worst;
	}
	
	
}
