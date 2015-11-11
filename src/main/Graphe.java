package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Graphe {
	
	private HashMap<Integer, String> sommets;
	private int nombreDeSommets, nombreDArretes;
	private int[] arretes;
	
	public Graphe(int[] arretes, int nombreDeSommets, int nombreDArretes){
		this.arretes = arretes;
		this.nombreDeSommets = nombreDeSommets;
		this.nombreDArretes = nombreDArretes;
		
		sommets = new HashMap<>(nombreDeSommets);
		for (int i = 1; i <= nombreDeSommets; i++) {
			sommets.put(i, "no-col");
		}
	}
	
	public void genererDicmac(String fichier) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter cible = new PrintWriter(fichier+".dicmac", "UTF-8");

		cible.println("p cnf " + (3*nombreDeSommets) + " " + (nombreDeSommets+3*nombreDArretes));
		
		for (int i = 1; i <= 3*nombreDeSommets; i+=3) {
			cible.println(i + " " + (i+1) + " " + (i+2) +" 0");
		}
		
		int premier, second;
		for (int i = 0; i < 2*nombreDArretes; i+=2) {
			premier = arretes[i]; second = arretes[i+1];
			cible.println("-" + (premier*3) 	+ " -" + (second*3) 	+" 0");
			cible.println("-" + (premier*3-1) 	+ " -" + (second*3-1)	+" 0");
			cible.println("-" + (premier*3-2) 	+ " -" + (second*3-2) 	+" 0");
		}
		cible.close();
	}
	
	public void genererResult(String fichier) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("minisat "+fichier+".dicmac "+fichier+".result");
	}
	
	public void estTroisCol(String fichier) throws IOException{
		this.genererDicmac(fichier);
		this.genererResult(fichier);
		if(this.estSAT(fichier)){
			System.out.println("SAT");
			this.colorierGraphe(fichier);
		}
		else{
			System.out.println("UNSAT");
		}
	}
	
	private boolean estSAT(String fichier) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fichier+".result"));
		boolean estSAT = br.readLine().equals("SAT");
		br.close();
		return estSAT;
	}
	
	private void colorierGraphe(String fichier) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fichier+".result"));
		br.readLine();//Skip first line
		String[] line = br.readLine().split(" ");
		
		for (int i = 0; i < line.length-1; i+=3) {
			if ( ! line[i].contains("-"))
				sommets.put((i+3)/3,"rouge");
			else if ( ! line[i+1].contains("-"))
				sommets.put((i+3)/3,"vert");
			else
				sommets.put((i+3)/3,"bleu");
		}
		System.out.println(this.sommets.toString());
		br.close();
	}

	public HashMap<Integer, String> getSommets() {
		return sommets;
	}

	public void setSommets(HashMap<Integer, String> sommets) {
		this.sommets = sommets;
	}

	public int getNombreDeSommets() {
		return nombreDeSommets;
	}

	public void setNombreDeSommets(int nombreDeSommets) {
		this.nombreDeSommets = nombreDeSommets;
	}

	public int getNombreDArretes() {
		return nombreDArretes;
	}

	public void setNombreDArretes(int nombreDArretes) {
		this.nombreDArretes = nombreDArretes;
	}

	public int[] getArretes() {
		return arretes;
	}

	public void setArretes(int[] arretes) {
		this.arretes = arretes;
	}
}
