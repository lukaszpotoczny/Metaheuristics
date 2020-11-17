package MetaLab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

	private int iterations;
	private Individual[] neighbours;
	private double bestScore;
	private double temperature;
	
	private Instance inst;
	private Mutation mutation;
	
	private ArrayList<Score> scores;
	
	public SimulatedAnnealing(int iter, int n_size, double temp, Instance inst, Mutation m) {
		this.iterations = iter;
		this.neighbours = new Individual[n_size];
		this.temperature = temp;
		this.inst = inst;
		this.mutation = m;
		this.bestScore = Integer.MAX_VALUE;
		scores = new ArrayList<Score>(iter);
	}
	
	public double execute() {
		
		Individual current = new Individual(inst.getCities(), inst.getCapacity());
		Individual candidate;
		initiate(current);
		current.setRouteDistance(current.calculateRouteDistance(inst.getDistanceMatrix()));
		bestScore = current.getRouteDistance();
		scores.add(new Score(bestScore, bestScore));
		int a=0;
		for(int i=1; i<=iterations; i++) {
		//	System.out.println("\nIteration " + i);
			createNeighbours(current);
			candidate = getBestNeighbour();
		//	System.out.println("candidate: " + candidate.getRouteDistance() + ", current: " + current.getRouteDistance());
			if(candidate.getRouteDistance() < current.getRouteDistance()) {
				current = Helper.copy(candidate);
				
			}
			else if(chooseWorst(current.getRouteDistance(), candidate.getRouteDistance())) {
				current = Helper.copy(candidate);
			}
			
			if(current.getRouteDistance() < bestScore) bestScore = current.getRouteDistance();
			
			temperature = temperature * 0.99985;
			
			//System.out.println("best: " + bestScore + ", current: " + current.getRouteDistance());
			scores.add(new Score(bestScore, current.getRouteDistance()));

			
		}

		System.out.println(bestScore);
		//System.out.println("TEMPERATURE: " + temperature);
		Helper.logSAExcel(scores, "SAresults", inst.getName());
		
		return bestScore;
		
	}
	
	private boolean chooseWorst(double better, double worst) {
		Random rand = new Random();
		double swap = Math.exp((better - worst) / temperature);		

		return (rand.nextDouble() < swap);
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
	
	private Individual getBestNeighbour() {
		double newBest = Integer.MAX_VALUE;
		Individual best = neighbours[0];
		for(int j=0; j<neighbours.length; j++) {
			double distance;
			distance = (neighbours[j].calculateRouteDistance(inst.getDistanceMatrix()));
			neighbours[j].setRouteDistance(distance);
			if(distance < newBest) {
				newBest = distance;
				best = neighbours[j];
			}
		}
		return best;
	}
	
	
	
	
}
