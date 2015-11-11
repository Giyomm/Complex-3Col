package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		int[] a1 = {1,2,2,3,3,4,1,3,2,4}; 
		int[] a2 = {1,2,2,3,3,4,4,5,5,1,1,6,2,7,3,8,4,9,5,10,6,8,7,9,8,10,9,6,10,7};
		int[] a3 = {1,2,2,3,3,4,4,1,2,4,1,3};
		
		Graphe g1 = new Graphe(a1, 4, 5);
		Graphe g2 = new Graphe(a2, 10, 15);
		Graphe g3 = new Graphe(a3, 4, 6);
		
		try {
			if (g1.estTroisCol("g1"))
				System.out.println("Le graphe g1 est 3-coloriables : " + g1.getSommets().toString());
			else
				System.out.println("Le graphe g1 n'est pas 3-coloriables.");

			if (g2.estTroisCol("g2"))
				System.out.println("Le graphe g2 est 3-coloriables : " + g2.getSommets().toString());
			else
				System.out.println("Le graphe g2 n'est pas 3-coloriables.");

			if (g3.estTroisCol("g3"))
				System.out.println("Le graphe g3 est 3-coloriables : " + g3.getSommets().toString());
			else
				System.out.println("Le graphe g3 n'est pas 3-coloriables.");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
