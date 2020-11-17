package MetaLab;

import java.util.Random;

public class MutationInverse extends Mutation{

	double pM;
	
	MutationInverse(double pM){
		this.pM = pM;
	}
	
	@Override
	public void mutate(Individual ind) {
		Random rand = new Random();
		if(rand.nextDouble() < pM) {
			Random r = new Random();
			
			int size = ind.getCities().length;
			int id1 = r.nextInt(size - 1) + 1;
			int id2 = r.nextInt(size - 1) + 1;

			while(id1 >= id2) {
				id1 = r.nextInt(size - 1) + 1;
				id2 = r.nextInt(size - 1) + 1;
			}
		
			int mid = id1 + ((id2 + 1) - id1) / 2;
			int end = id2;
			
			for(int i = id1; i<mid; i++) {
				City temp = ind.getCities()[i];
				ind.getCities()[i] = ind.getCities()[end];
				ind.getCities()[end] = temp;
				end--;
			}
			
			
		}

	}
}
