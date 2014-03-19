/**
 * Testprogram.java
 *
 * Øving 14, vår 2014
 */

import java.util.*;
import java.io.*;

class Testprogram {
	public static void main(String[] args) {
		Staa staatribune1 = new Staa("Ståtribune 1", 30, 100); // String tribunenavn, int kapasitet, int pris
		Staa staatribune2 = new Staa("Ståtribune 2", 20, 200);
		Sitte sittetribune = new Sitte("Sittetribune", 500, 300, 25); // String tribunenavn, int kapasitet, int pris, int antRader
		Vip viptribune = new Vip("VIP-tribune", 20, 500, 2); // String tribunenavn, int kapasitet, int pris, int antRader
		Tribune[] tribuner = {staatribune1, staatribune2, sittetribune, viptribune};
		
		for (int i = 0; i < (tribuner.length - 1); i++) {
			Billett[] billetter = tribuner[i].kjopBilletter(20);
			for (int j = 0; j < billetter.length; j++) {
				System.out.println(billetter[j]);
			}
		}
		
		String[] billetteiere = {"Mia", "David", "Julian", "John Inge", "Magnus"};
		Billett[] billetter = tribuner[3].kjopBilletter(billetteiere);
		
		for (int i = 0; i < billetter.length; i++) {
			System.out.println(billetter[i]);
		}
		
		for (int i = 0; i < tribuner.length; i++) {
			System.out.println(tribuner[i]);
		}
		
		//Arrays.sort(tribuner, Collections.reverseOrder());
		
		String filnavn = "Tribuner.txt";
		
		try {
			FileWriter skriveforbindelseTilFil = new FileWriter(filnavn, true);
			PrintWriter skriver = new PrintWriter(new BufferedWriter(skriveforbindelseTilFil));
			
			for (int i = 0; i < tribuner.length; i++) {
				skriver.println(tribuner[i]);
			}
			skriver.close();
			
			FileReader leseforbindelseTilFil = new FileReader(filnavn);
			BufferedReader leser = new BufferedReader(leseforbindelseTilFil);
			Scanner scan = new Scanner(leser);
			
			String lestFraFil = "";
			while(scan.hasNext()) {
				lestFraFil += scan.nextLine() + "\n";
			}
			System.out.println(lestFraFil);
			scan.close();
			leser.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Fil ikke funnet: " + filnavn);
		} catch (IOException e) {
			System.out.println("IO-feil ved åpning/lukking av fil: " + filnavn);
		}
	}
}