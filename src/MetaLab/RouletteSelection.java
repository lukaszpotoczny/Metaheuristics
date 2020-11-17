package MetaLab;

import java.util.Random;

public class RouletteSelection extends Selection {

	@Override
	public Individual execute(Population p) {
		double total = 0;
		int size = p.getIndividuals().length;
		double[] start = new double[size];

        for(int i=0; i<size; i++) {
        	total += Math.pow((10000 / p.getIndividuals()[i].getRouteDistance()), 10);
        	start[i] = total;
        }

        int rand = new Random().nextInt((int)total);

        for(int i=0; i<size; i++) {
        	if (start[i] >= rand) return Helper.copy(p.getIndividuals()[i]);
        }

        return null;
	}
}
