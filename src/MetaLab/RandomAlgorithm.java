package MetaLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomAlgorithm {

	public void execute(Individual ind) {
		
		Random rand = new Random();
		
		for (int i = 1; i < ind.getCities().length; i++) {
			int randomIndexToSwap = rand.nextInt(ind.getCities().length - 1) + 1;
			City temp = ind.getCities()[randomIndexToSwap];
			ind.getCities()[randomIndexToSwap] = ind.getCities()[i];
			ind.getCities()[i] = temp;
		}
		
	}
}
