package MetaLab;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class EvolutionaryAlgorithm {

	private int popSize;
	private int gen;
	private double pX;
	private double pM;
	private double bestScore = Integer.MAX_VALUE;
	
	private Instance inst;
	private Selection selection;
	private Crossover crossover;
	private Mutation mutation;
	
	private ArrayList<Score> scores;
	
	
	public EvolutionaryAlgorithm(int pS, int g, double pX, double pM, Instance i, Selection s, Crossover c, Mutation m) {
		this.popSize = pS;
		this.gen = g;
		this.pX = pX;
		this.pM = pM;
		this.inst = i;
		this.selection = s;
		this.crossover = c;
		this.mutation = m;
		scores = new ArrayList<Score>(gen);
	}

	public double execute() throws FileNotFoundException {

		Population pop = initiate();
		
		rate(pop);
	//	System.out.println("\ngen" + 0);
	//	System.out.println("best: " + scores.get(0).getBest() + " average: " + scores.get(0).getAverage() + " worst: " + scores.get(0).getWorst());

		
		int popCount = pop.getSize();
		
		Population newPop=null;

		for(int i=1; i<gen; i++) {
		//	System.out.println("\ngen" + i);
			
			if(i>1) {
				pop = newPop;			
			}
			popCount = 0;
			newPop = new Population(this.popSize, this.inst);
			
			while(popCount < pop.getSize()) {
				
				Individual parent1 = select(pop);
				Individual parent2 = select(pop);
				
				Individual[] children = cross(parent1,parent2);

				mutate(children[0]);
				mutate(children[1]);
				
				newPop.addIndividual(children[0], popCount);
				popCount++;
				newPop.addIndividual(children[1], popCount);
				popCount++;
			}

			rate(newPop);			
		}
		
		
		//Helper.logExcel(scores, "EAresults", true, inst.getName(), popSize, gen, pX, pM, 20);
		
		System.out.println(bestScore);
		return bestScore;
		
	}
	
	private Population initiate() {
		RandomAlgorithm randAlg = new RandomAlgorithm();
		Population pop = new Population(this.popSize, this.inst);
		
		for(int i=0; i<pop.getSize(); i++) {
			randAlg.execute(pop.getIndividuals()[i]);
		}
		
		return pop;
	}
	

	private void rate(Population p) {
		double newBest = Integer.MAX_VALUE;
		double newWorst = 0, total = 0;
		for(int j=0; j<popSize; j++) {
			double distance;
			distance = (p.getIndividuals()[j].calculateRouteDistance(inst.getDistanceMatrix()));
			p.getIndividuals()[j].setRouteDistance(distance);
			if(distance < newBest) newBest = distance;
			else if(distance > newWorst) newWorst = distance;
			total += distance;
		}
		double average = new BigDecimal(total/popSize).setScale(2, RoundingMode.HALF_UP).doubleValue();
		
		scores.add(new Score(newBest, average, newWorst));
		
		if(newBest < bestScore) bestScore = newBest;

		//System.out.println("best: " + newBest + " average: " + average + " worst: " + newWorst);

	}
	
	private Individual select(Population p) {
		return selection.execute(p);
	}

	private Individual[] cross(Individual r1, Individual r2) {
		if(Math.random()<pX) {
			return crossover.cross(r1, r2);
		}
		else {
			Individual[] inds = new Individual[] {r1, r2};
			return inds;
		}
	}
	
	private void mutate(Individual ind) {
//		if(Math.random()<pM) {
//			mutation.mutate(ind);
//		}
		mutation.mutate(ind);

	}

	public int getPopSize() {
		return popSize;
	}

	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}

	public int getGen() {
		return gen;
	}

	public void setGen(int gen) {
		this.gen = gen;
	}

	public double getpX() {
		return pX;
	}

	public void setpX(double pX) {
		this.pX = pX;
	}

	public double getpM() {
		return pM;
	}

	public void setpM(double pM) {
		this.pM = pM;
	}

	public Instance getInst() {
		return inst;
	}

	public void setInst(Instance inst) {
		this.inst = inst;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public Crossover getCrossover() {
		return crossover;
	}

	public void setCrossover(Crossover crossover) {
		this.crossover = crossover;
	}

	public ArrayList<Score> getScores() {
		return scores;
	}

	public void setScores(ArrayList<Score> scores) {
		this.scores = scores;
	}
		
	
}
