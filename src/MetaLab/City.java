package MetaLab;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class City {
	private int id;
	private double x;
	private double y;
	private int demand;

	public City(int i, double x, double y, int d) {
		this.id = i;
		this.x = x;
		this.y = y;
		this.demand = d;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public int getDemand() {
		return demand;
	}


	public void setDemand(int demand) {
		this.demand = demand;
	}
	
	public double calculateDistance(City c) {
		double dist=0.0;
				
		dist = Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
			
		return new BigDecimal(dist).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
}
