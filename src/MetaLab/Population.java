package MetaLab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Population {

	private int size;
	private Instance inst;
	private Individual[] individuals;

	public Population(int s, Instance i) {
		this.size = s;
		this.inst = i;
		this.individuals = createInd();
	}
	
	private Individual[] createInd() {
		Individual[] individuals = new Individual[this.size];
		City[] cities = new City[this.inst.getCities().length];
		
		for(int i=0; i<this.size; i++) {
			cities = new City[this.inst.getCities().length];
			for(int j=0; j<this.inst.getCities().length; j++) {
				cities[j] = new City(this.inst.getCities()[j].getId(), this.inst.getCities()[j].getX(), this.inst.getCities()[j].getY(), this.inst.getCities()[j].getDemand());
			}
			individuals[i] = (new Individual(cities, this.inst.getCapacity()));
		}
		
		return individuals;
	}
	
	public void addIndividual(Individual ind, int index) {
		if(index<size) this.individuals[index] = (Helper.copy(ind));
	}
	
//	public double calculateBestDistance() {
//		double score = individuals[0].calculateRouteDistance(inst.getDistanceMatrix());
//		double dist;
//		for(int i=1; i<individuals.length; i++) {
//			dist = individuals[i].calculateRouteDistance(inst.getDistanceMatrix());
//			if(dist<score) {
//				score=dist;
//			}
//		}
//		
//		return score;
//	}
//	
//	public double calculateAverageDistance() {
//		double score=0;
//		for(int i=0; i<this.individuals.length; i++) {
//			score+=this.individuals[i].calculateRouteDistance(this.inst.getDistanceMatrix());
//		}
//		return new BigDecimal(score/this.size).setScale(2, RoundingMode.HALF_UP).doubleValue();
//	}
//	
//	public double calculateWorstDistance() {
//		double score = this.individuals[0].calculateRouteDistance(this.inst.getDistanceMatrix());
//		double dist;
//		for(int i=1; i<this.individuals.length; i++) {
//			dist = this.individuals[i].calculateRouteDistance(this.inst.getDistanceMatrix());
//			if(dist>score) {
//				score=dist;
//			}
//		}
//		
//		return score;
//	}
	


	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Instance getInst() {
		return inst;
	}

	public void setInst(Instance inst) {
		this.inst = inst;
	}

	public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}
	
	
}
