package MetaLab;

import java.util.ArrayList;

public class Greedy {

	public void execute(Individual ind, City startCity, double[][]distMatrix) {
		ArrayList<City> newCities = new ArrayList<City>(ind.getCities().length);
		
		newCities.add(ind.getCities()[0]);
		newCities.add(startCity);
		City currentCity = startCity;
		City nextCity = null;
		double minDistance = Integer.MAX_VALUE;
		int itemsLeft = ind.getCapacity() - startCity.getDemand();
		int citiesVisited = 2;
		
		while(citiesVisited < ind.getCities().length) {		
			for(City c : ind.getCities()) {
				if(!newCities.contains(c)) {
					if(itemsLeft>c.getDemand()) {
						if(distMatrix[currentCity.getId()-1][c.getId()-1]<minDistance) {
							minDistance = distMatrix[currentCity.getId()-1][c.getId()-1];
							nextCity = c;
						}
					}
				}
			}
			
			if(nextCity!=null) {
				itemsLeft -= nextCity.getDemand();
				newCities.add(nextCity);
				currentCity = nextCity;
				minDistance = Integer.MAX_VALUE;			
				citiesVisited++;
				nextCity=null;
			}
			else {
				itemsLeft = ind.getCapacity();
				currentCity = ind.getCities()[0];
				minDistance = Integer.MAX_VALUE;
			}
			
		}
		ind.setCities(newCities);
	}
}
