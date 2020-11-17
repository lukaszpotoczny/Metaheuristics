package MetaLab;

import java.util.ArrayList;
import java.util.Random;


public class TournamentSelection extends Selection{

	private int n;
	
	public TournamentSelection(int n) {
		this.n = n;
	}
	
	@Override
	public Individual execute(Population p) {
		int pop_size = p.getIndividuals().length;
		
		if(this.n > pop_size) this.n = pop_size;
		
		Random rand = new Random();
		
		ArrayList<Integer> ids = new ArrayList<Integer>(this.n);
		

		int randId;
		while(ids.size()!=this.n) {
			randId = rand.nextInt(pop_size);
			if(!ids.contains(randId)){
				ids.add(randId);
			}
		}
		
		int minId = ids.get(0);
		Individual ind = p.getIndividuals()[minId];
		double minDist = ind.getRouteDistance();
		
		int id;
		double dist;
		for(int i=0; i<ids.size(); i++) {
			id = ids.get(i);
			ind = p.getIndividuals()[id];
			dist = ind.getRouteDistance();
			
			if(dist<minDist) {
				minDist = dist;
				minId = id;
			}
		}

		return Helper.copy(p.getIndividuals()[minId]);
	}
	
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
}
