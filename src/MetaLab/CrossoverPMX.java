package MetaLab;

import java.util.Random;

public class CrossoverPMX extends Crossover{

	@Override
	public Individual[] cross(Individual parent1, Individual parent2) {
		
		Random r = new Random();
		
		int size = parent1.getCities().length;
		int id1 = r.nextInt(size-1) + 1;
		int id2 = r.nextInt(size);

		while(id1 >= id2) {
			id1 = r.nextInt(size);
			id2 = r.nextInt(size);
		}

		City[][] result = new City[2][size];
		result[0][0] = parent1.getCities()[0];
		result[1][0] = parent2.getCities()[0];
		int range = id2 - id1 + 1;

		City[][] swap = new City[2][range];
		int count=0;
		for(int i=id1; i<=id2; i++) {
			swap[0][count] = parent1.getCities()[i];
			swap[1][count] = parent2.getCities()[i];
			result[0][i] = parent1.getCities()[i];
			result[1][i] = parent2.getCities()[i];
			count++;
		}
		boolean cross = false;
		for(int i=1; i<size; i++) {
			if(result[0][i] == null) {
				cross=false;
				for(int j=0; j<count; j++) {
					if(parent2.getCities()[i].getId() == swap[0][j].getId()) {
						City city = swap[1][j];
						
						for(int a=0; a<count; a++) {
							if(city.getId() == swap[0][a].getId()) {
								city = swap[1][a];
								a=-1;
							}
						}
						result[0][i] = city;
						cross = true;
					}
					
				}
				if(!cross) result[0][i] = parent2.getCities()[i];
			}
			
		}
		
		for(int i=1; i<size; i++) {
			if(result[1][i] == null) {
				cross=false;
				for(int j=0; j<count; j++) {
					if(parent1.getCities()[i].getId() == swap[1][j].getId()) {
						City city = swap[0][j];
						for(int a=0; a<count; a++) {
							if(city.getId() == swap[1][a].getId()) {
								city = swap[0][a];
								a=-1;
							}
						}
						result[1][i] = city;
						cross = true;
					}
				}
				if(!cross) result[1][i] = parent1.getCities()[i];
			}
			
		}

		Individual[] inds = new Individual[2];
		inds[0] = new Individual(result[0], parent1.getCapacity());
		inds[1] = new Individual(result[1], parent2.getCapacity());
	
		return inds;
	}
	
}
