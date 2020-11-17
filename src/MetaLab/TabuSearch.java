package MetaLab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class TabuSearch {

	private int iterations;
	private Individual[] neighbours;
	private Individual[] tabu;
	private double bestScore;
	
	private Instance inst;
	private Mutation mutation;
	
	private ArrayList<Score> scores;
	
	public TabuSearch(int iter, int n_size, int tabu_size, Instance inst, Mutation m) {
		this.iterations = iter;
		this.neighbours = new Individual[n_size];
		this.tabu = new Individual[tabu_size];
		this.inst = inst;
		this.mutation = m;
		this.bestScore = Integer.MAX_VALUE;
		scores = new ArrayList<Score>(iter);
	}
	
	public double execute() {
		
		Individual current = new Individual(inst.getCities(), inst.getCapacity());
		initiate(current);
		current.setRouteDistance(current.calculateRouteDistance(inst.getDistanceMatrix()));
		bestScore = current.getRouteDistance();
		scores.add(new Score(bestScore, bestScore, bestScore, bestScore));
		int tabuIndex = 0;
		
		
		for(int i=1; i<=iterations; i++) {
		//	System.out.println("\nIteration " + i);
			createNeighbours(current);
			rateNeighbours();
			current = selectBest();
			tabu[tabuIndex % tabu.length] = Helper.copy(current);
			tabuIndex++;
			
		}

		System.out.println(bestScore);
		Helper.logExcel(scores, "TSresults", inst.getName());
		
		return bestScore;
		
		
	}
	
	private Individual selectBest() {
		boolean found = false;
		while(!found) {
			double best = neighbours[0].getRouteDistance();
			int bestIndex = 0;
			for(int i=0; i<neighbours.length; i++) {
				if(neighbours[i].getRouteDistance() < best) {
					best = neighbours[i].getRouteDistance();
					bestIndex = i;
				}
			}
			if(neighbours[bestIndex].getRouteDistance() < bestScore) {
				bestScore = neighbours[bestIndex].getRouteDistance();
				return neighbours[bestIndex];
			}
			else if(!isInTabu(neighbours[bestIndex])) return neighbours[bestIndex];
			else neighbours[bestIndex].setRouteDistance(Integer.MAX_VALUE);	
		}

		return null;
	}
	
	private boolean isInTabu(Individual ind) {
		for(int i=0; i<tabu.length; i++) {
			if(tabu[i] == null) return false;
			else {
				int j=0;
				while(tabu[i].getCities()[j].getId() == ind.getCities()[j].getId() && j<ind.getCities().length) {
					j++;
					if(j == ind.getCities().length)	return true;
				}
			}

		}
		return false;
	}
	
	private void createNeighbours(Individual ind) {
		for(int i=0; i<neighbours.length; i++) {
			Individual newInd = Helper.copy(ind);
			mutation.mutate(newInd);
			neighbours[i] = newInd;
		}
	}
	
	
	private void initiate(Individual ind) {
		RandomAlgorithm randAlg = new RandomAlgorithm();
		randAlg.execute(ind);
		
		//Greedy greedy = new Greedy();
		//greedy.execute(ind, ind.getCities()[1], inst.getDistanceMatrix());
	}
	
	private void rateNeighbours() {
		double newBest = Integer.MAX_VALUE;
		double newWorst = 0, total = 0;
		for(int j=0; j<neighbours.length; j++) {
			double distance;
			distance = (neighbours[j].calculateRouteDistance(inst.getDistanceMatrix()));
			neighbours[j].setRouteDistance(distance);
			if(distance < newBest) newBest = distance;
			else if(distance > newWorst) newWorst = distance;
			total += distance;
		}
		double average = new BigDecimal(total/neighbours.length).setScale(2, RoundingMode.HALF_UP).doubleValue();
		
		scores.add(new Score(bestScore, newBest, average, newWorst));
		
		//System.out.println("best: " + newBest + " average: " + average + " worst: " + newWorst);

	}
	
	
	
	
	
}
