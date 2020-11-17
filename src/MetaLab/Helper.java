package MetaLab;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Helper {

	public static Individual copy(Individual ind) {
		Individual copyInd;
		
		City[] copyCity = new City[ind.getCities().length];
		
		City c;
		for(int i=0; i<ind.getCities().length; i++) {
			c = ind.getCities()[i];
			if(c != null)copyCity[i] = new City(c.getId(), c.getX(), c.getY(), c.getDemand());
		}
		
		copyInd = new Individual(copyCity, ind.getCapacity());
		copyInd.setRouteDistance(ind.getRouteDistance());
		
		return copyInd;
	}
	
	public static void logExcel(ArrayList<Score> scores, String name, boolean append, String file, int rP, int lG, double pX, double pM, int n) {
		try {
			File fout = new File("C:/Users/Student242447/Desktop/" + name + ".xls");
			FileOutputStream fos = new FileOutputStream(fout, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("\t\t\t\tNazwa pliku" + "\t" + "Rozmiar populacji" + "\t" + "Liczba generacji" + "\t" + "Prawd. krzyøowania" + "\t" + "Prawd. mutacji" + "\t" + "Rozmiar turnieju");
			bw.newLine();
			bw.write("\t\t\t\t" + file + "\t" + rP + "\t" + lG + "\t" + pX + "\t" + pM + "\t" + n);
			bw.newLine();
			bw.newLine();
			bw.write("Nr generacji\tNajlepszy\tåredni\tNajgorszy");
			bw.newLine();
			for (int i = 0; i < scores.size(); i++) {
				bw.write("" + (i+1));
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getBest() + "\";\".\")");
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getAverage() + "\";\".\")");
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getWorst() + "\";\".\")");
				bw.write("\t");
				bw.newLine();
			}
			bw.newLine();
			bw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logExcel(Population pop, String name, Instance inst) {
		try {
			File fout = new File("C:/Users/Student242447/Desktop/" + name + ".xls");
			FileOutputStream fos = new FileOutputStream(fout, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("Nazwa pliku" + "\t" + "Rozmiar populacji" + "\t" + "Liczba generacji" + "\t" + "Prawd. krzyøowania" + "\t" + "Prawd. mutacji" + "\t" + "Rozmiar turnieju");
			bw.newLine();
			//bw.write(file + "\t" + rP + "\t" + lG + "\t" + pX + "\t" + pM + "\t" + n);
			bw.newLine();
			bw.newLine();
			bw.write("Route Distance");
			bw.newLine();
			double best = Integer.MAX_VALUE;
			double score;
			for (int i = 0; i < pop.getSize(); i++) {
				bw.write("" + (i+1));
				bw.write("\t");
				score = pop.getIndividuals()[i].calculateRouteDistance(inst.getDistanceMatrix());
				bw.write("=WARTOå∆.LICZBOWA(\""+ score + "\";\".\")");
				if(best > score) best = score;
				bw.write("\t");
				bw.newLine();
			}
			bw.newLine();
			bw.close();
			System.out.println(best);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void logExcel(ArrayList<Score> scores, String name, String file) {
		try {
			File fout = new File("C:/Users/Student242447/Desktop/" + name + ".xls");
			FileOutputStream fos = new FileOutputStream(fout, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("\t\t\t\tNazwa pliku" + "\t" + "Rozmiar populacji" + "\t" + "Liczba generacji" + "\t" + "Prawd. krzyøowania" + "\t" + "Prawd. mutacji" + "\t" + "Rozmiar turnieju");
			bw.newLine();
			bw.write("\t\t\t\t" + file );
			bw.newLine();
			bw.newLine();
			bw.write("Nr generacji\tNajlepszy\tObecny\tåredni\tNajgorszy");
			bw.newLine();
			for (int i = 0; i < scores.size(); i++) {
				bw.write("" + (i+1));
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getBest() + "\";\".\")");
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getCurrent() + "\";\".\")");
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getAverage() + "\";\".\")");
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getWorst() + "\";\".\")");
				bw.write("\t");
				bw.newLine();
			}
			bw.newLine();
			bw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logSAExcel(ArrayList<Score> scores, String name, String file) {
		try {
			File fout = new File("C:/Users/Student242447/Desktop/" + name + ".xls");
			FileOutputStream fos = new FileOutputStream(fout, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("\t\t\t\tNazwa pliku" + "\t" + "Rozmiar populacji" + "\t" + "Liczba generacji" + "\t" + "Prawd. krzyøowania" + "\t" + "Prawd. mutacji" + "\t" + "Rozmiar turnieju");
			bw.newLine();
			bw.write("\t\t\t\t" + file );
			bw.newLine();
			bw.newLine();
			bw.write("Nr generacji\tNajlepszy\tObecny");
			bw.newLine();
			for (int i = 0; i < scores.size(); i++) {
				bw.write("" + (i+1));
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getBest() + "\";\".\")");
				bw.write("\t");
				bw.write("=WARTOå∆.LICZBOWA(\""+ scores.get(i).getCurrent() + "\";\".\")");
				bw.newLine();
			}
			bw.newLine();
			bw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
