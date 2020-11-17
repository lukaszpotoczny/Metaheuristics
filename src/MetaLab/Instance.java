package MetaLab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instance {

	private String name;
	private String type;
	private String comment;
	private int dimension;
	private String edgeWeightType;
	private int capacity;
	private City[] cities;
	private double[][] distanceMatrix;
	int storageIndex;
	
	public Instance(String fileName) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(fileName + ".txt")); 
		
		this.name = in.nextLine().split(" ")[2];
		this.comment = in.nextLine().split(" ")[2];
		this.type = in.nextLine().split(" ")[2];
		this.dimension = Integer.parseInt(in.nextLine().split(" ")[2]);
		this.edgeWeightType = in.nextLine().split(" ")[2];
		this.capacity =  Integer.parseInt(in.nextLine().split(" ")[2]);
		this.cities = new City[dimension];
		
		if(in.nextLine().contains("NODE_COORD_SECTION")) {
			for(int i=0; i<dimension; i++) {
				String[] cityInfo = in.nextLine().split(" ");							
				cities[i] = new City(Integer.parseInt(cityInfo[1]), Double.parseDouble(cityInfo[2]), Double.parseDouble(cityInfo[3]), 0);
			}			
		}
		
		if(in.nextLine().contains("DEMAND_SECTION")) {
			for(int i=0; i<dimension; i++) {
				String[] cityInfo = in.nextLine().split(" ");							
				cities[i].setDemand(Integer.parseInt(cityInfo[1]));
			}			
		}
		

		if(in.nextLine().contains("DEPOT_SECTION")) {						
			this.storageIndex =  Integer.parseInt(in.nextLine().split(" ")[1]);			
		}
		
		this.distanceMatrix = generateDistanceMatrix();
	}
	
	
	private double[][] generateDistanceMatrix(){
		double[][] distance = new double[dimension][dimension];
		
		for(int i=0; i<dimension; i++) {
			for(int j=i; j<dimension; j++) {
				double dist = cities[i].calculateDistance(cities[j]);
				distance[i][j] = dist;
				distance[j][i] = dist;
			}
		}
		return distance;
	}
	
	public void displayMatrix() {
		for(int i=0; i<dimension; i++) {
			for(int j=0; j<dimension; j++) {
				System.out.print(distanceMatrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public String getEdgeWeightType() {
		return edgeWeightType;
	}

	public void setEdgeWeightType(String edgeWeightType) {
		this.edgeWeightType = edgeWeightType;
	}


	public City[] getCities() {
		return cities;
	}


	public void setCities(City[] cities) {
		this.cities = cities;
	}


	public double[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public void setDistanceMatrix(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}
	
	
	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getStorageIndex() {
		return storageIndex;
	}


	public void setStorageIndex(int storageIndex) {
		this.storageIndex = storageIndex;
	}
	
}
