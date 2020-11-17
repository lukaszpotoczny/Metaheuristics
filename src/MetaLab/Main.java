package MetaLab;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
	
	static String fileName = "n45k6";
	static int popSize = 400;
	static int gen = 25000;
	static double pX = 0.55;
	static double pM = 0.91;
	static int tour = 6;
	
	
	static int iterations = 100000;
	static int neighbours = 100;
	static int tabu = 490;
	
	static double temperature = 100000000;

	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Instance inst = new Instance(fileName);
		//inst.displayMatrix();
	
		Selection s = new TournamentSelection(popSize * tour / 100);
		Selection rou = new RouletteSelection();
		
		Crossover ox = new CrossoverOX();
		Crossover pmx = new CrossoverPMX();
		
		Mutation swap = new MutationSwap(0.01);
		Mutation inverse = new MutationInverse(pM);
		
		double total = 0;
		
		
		
		EvolutionaryAlgorithm ea1 = new EvolutionaryAlgorithm(popSize, gen, pX, pM, inst, s, ox, inverse);
		TabuSearch ts = new TabuSearch(iterations, neighbours, tabu, inst, inverse);
		SimulatedAnnealing sa = new SimulatedAnnealing(iterations, neighbours, temperature, inst, inverse);
		
		long a = System.nanoTime();
		
		for(int i=0; i<20; i++) {
			//total += ts.execute();
			EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(popSize, gen, pX, pM, inst, s, ox, inverse);
			total += ea.execute();
		}
		System.out.println("Average: " + total/20);
		
		System.out.println(System.nanoTime() - a);
		

//		runGreedyAlg(inst);
//		runRandAlg(inst);
	
		System.out.println("DONE");		
	}
	
	
	public static void runRandAlg(Instance inst) {
		RandomAlgorithm randAlg = new RandomAlgorithm();
		Population pop = new Population(popSize * gen, inst);
		
		for(int i=0; i<pop.getSize(); i++) {
			randAlg.execute(pop.getIndividuals()[i]);
		}
		Helper.logExcel(pop, "RandomAlgResult", inst);
	}
	
	public static void runGreedyAlg(Instance inst) throws FileNotFoundException {
		Individual ind = new Individual(inst.getCities(), inst.getCapacity());
		Greedy greedy = new Greedy();
		
		for(int a=1; a<inst.getDimension(); a++) {
			inst = new Instance(fileName);
			ind = new Individual(inst.getCities(), inst.getCapacity());
			greedy.execute(ind, ind.getCities()[a], inst.getDistanceMatrix());
			System.out.println(ind.calculateRouteDistance(inst.getDistanceMatrix()));
		}
		
	}
	

}
