package MetaLab;

import java.util.ArrayList;
import java.util.Random;

public class CrossoverOX extends Crossover{

	@Override
	public Individual[] cross(Individual parent1, Individual parent2) {
		
		Random r = new Random();
		
		int size = parent1.getCities().length;
		int id1 = r.nextInt(size-1) + 1;
		int id2 = r.nextInt(size-1) + 1;

		while(id1 >= id2) {
			id1 = r.nextInt(size-1) + 1;
			id2 = r.nextInt(size-1) + 1;
		}
	
		City[][] result = new City[2][size];
		result[0][0] = parent1.getCities()[0];
		result[1][0] = parent2.getCities()[0];
		ArrayList<Integer> cityIndex = new ArrayList<Integer>(size);
		
		for(int i=id1; i<=id2; i++) {
			result[0][i] = parent1.getCities()[i];
			cityIndex.add(parent1.getCities()[i].getId());
		}
		
		for(int i=1; i<size; i++) {
			if(result[0][i] == null) {
				for(int j=1; j<size; j++) {
					if(!cityIndex.contains(parent2.getCities()[j].getId())) {
						result[0][i] = parent2.getCities()[j];
						cityIndex.add(parent2.getCities()[j].getId());
						j=size;
					}
						
				}
			}
		}
		cityIndex.clear();
		
		for(int i=id1; i<=id2; i++) {
			result[1][i] = parent2.getCities()[i];
			cityIndex.add(parent2.getCities()[i].getId());
		}
		
		for(int i=1; i<size; i++) {
			if(result[1][i] == null) {
				for(int j=1; j<size; j++) {
					if(!cityIndex.contains(parent1.getCities()[j].getId())) {
						result[1][i] = parent1.getCities()[j];
						cityIndex.add(parent1.getCities()[j].getId());
						j=size;
					}
						
				}
			}
		}
		Individual[] inds = new Individual[2];
		inds[0] = new Individual(result[0], parent1.getCapacity());
		inds[1] = new Individual(result[1], parent2.getCapacity());

		return inds;
	}
}
