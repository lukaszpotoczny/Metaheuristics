package MetaLab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Individual {

	private City[] cities;
	private int capacity;
	private double routeDistance;
	

	public Individual(City[] c, int cap) {
		this.cities = c;
		this.capacity = cap;
	}
	
	public City[] getCities() {
		return cities;
	}

	public void setCities(City[] cities) {
		this.cities = cities;
	}
	
	public void setCities(ArrayList<City> cities) {
		for(int i=0; i<cities.size(); i++)
			this.cities[i] = cities.get(i);
	}
		
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public double calculateRouteDistance(double[][] distanceMatrix) {
		double dist = 0.0;
		int itemsLeft = this.capacity;
		
		for(int i=0; i<(cities.length-1); i++) {
			if( cities[i+1] == null) System.out.println(i+1);
			if(itemsLeft >= cities[i+1].getDemand()) {
				dist += distanceMatrix[cities[i].getId()-1][cities[i+1].getId()-1];
				itemsLeft -= cities[i+1].getDemand();
			}
			else {
				dist += distanceMatrix[cities[i].getId()-1][0];
				itemsLeft = this.capacity;
				dist += distanceMatrix[0][cities[i+1].getId()-1];
				itemsLeft -= cities[i+1].getDemand();
			}
		}
		
		dist += distanceMatrix[cities[0].getId()-1][cities[(cities.length-1)].getId()-1];

		return new BigDecimal(dist).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public void displayRoute() {
		
//		int routeNum = 1;
//		int itemsLeft = this.capacity;
//		System.out.print("\nRoute #" + routeNum + ": ");
//		for(int i=1; i<cities.length; i++) {
//			if(itemsLeft < cities[i].getDemand()) {
//				routeNum++;
//				System.out.print("\nRoute #" + routeNum + ": ");
//				itemsLeft = this.capacity;
//				i--;
//			}
//			else {
//				itemsLeft -= cities[i].getDemand();
//				System.out.print(cities[i].getId() + " ");
//			}
//			
//		}
//	
		//System.out.println("\n");
		
		for(int i=0; i<this.cities.length; i++) System.out.print(this.cities[i].getId() + " ");
		
		System.out.println();
	}

	public double getRouteDistance() {
		return routeDistance;
	}

	public void setRouteDistance(double routeDistance) {
		this.routeDistance = routeDistance;
	}
	
	
}
