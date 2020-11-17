package MetaLab;

import java.util.Random;

public class MutationSwap extends Mutation{

	double pM;
	
	MutationSwap(double pM){
		this.pM = pM;
	}
	
	@Override
	public void mutate(Individual ind) {
		
		Random randIndex = new Random();
		Random rand = new Random();
		int size = ind.getCities().length;
		
//		for(int i=1; i<size; i++) {
//			
//			if(rand.nextDouble() < pM) {
//				int id2 = randIndex.nextInt(size - 1) + 1;
//				
//				City c = ind.getCities()[i];
//				ind.getCities()[i] =  ind.getCities()[id2];
//				ind.getCities()[id2] = c;
//			}
//		}
		
		
		int id1 = randIndex.nextInt(size - 1) + 1;
		int id2 = randIndex.nextInt(size - 1) + 1;

		City c = ind.getCities()[id1];
		ind.getCities()[id1] =  ind.getCities()[id2];
		ind.getCities()[id2] = c;
		

	}
}
