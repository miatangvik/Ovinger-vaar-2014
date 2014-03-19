/**
 * Dyrehage.java
 *
 * Øving 18, vår 2014
 */

import java.util.ArrayList;
import javax.swing.JOptionPane.*;

class Dyrehage {
	private ArrayList<Art> arter = new ArrayList<Art>();
	
	public boolean registrerNyArt(Art art) {
		for (int i = 0; i < arter.size(); i++) {
			if (arter.get(i).getNorskNavn().equals(art.getNorskNavn())) {
				return false;
			} else if (arter.get(i).getLatNavn().equals(art.getLatNavn())) {
				return false;
			}
		}
		arter.add(art);
		return true;
	}
	
	public Art finnArt(String norskNavn) {
		for (int i = 0; i < arter.size(); i++) {
			if (arter.get(i).getNorskNavn().equals(norskNavn)) {
				return arter.get(i);
			}
		}
		return null;
	}
	
	public Art finnArt(int indeks) {
		if (indeks <= arter.size()) {
			return arter.get(indeks);
		}
		return null;
	}
	
	private int finnArtIndeks(String norskNavn) {
		for (int i = 0; i < arter.size(); i++) {
			if (arter.get(i).getNorskNavn().equals(norskNavn)) {
				return i;
			}
		}
		return -1;
	}
	
	public int finnAntArter() {
		return arter.size();
	}
	
	public boolean registrerNyttIndivid(String norskNavn, Individ individ) {
		if (finnArt(norskNavn) == null) {
			return false;
		}
		if ((arter.get(finnArtIndeks(norskNavn)) instanceof DyrSomIndivider)) {
			DyrSomIndivider dyr = (DyrSomIndivider) arter.get(finnArtIndeks(norskNavn));
			dyr.registrerIndivid(individ);
		}
		return true;
	}
	
	public Individ finnIndivid(String norskNavn, String individnavn) {
		for (int i = 0; i < arter.size(); i++) {
			if (arter.get(i).getNorskNavn().equals(norskNavn)) {
				DyrSomIndivider dyr = (DyrSomIndivider) arter.get(finnArtIndeks(norskNavn));
				return dyr.finnIndivid(individnavn);
			}
		}
		return null;
	}
	
	public String finnFDatoIndivid(String norskNavn, String individnavn) {
		for (int i = 0; i < arter.size(); i++) {
			if (arter.get(i).getNorskNavn().equals(norskNavn)) {
				DyrSomIndivider dyr = (DyrSomIndivider) arter.get(finnArtIndeks(norskNavn));
				return dyr.finnFDatoIndivid(individnavn);
			}
		}
		return null;
	}
}

class DyrehageTest {
	public static void main(String[] args) {
		Dyrehage dyrehage = new Dyrehage();
		DyrSomIndivider love = new DyrSomIndivider("Løve", "Leo", "Felidae", "2", true);
		System.out.println("Totalt antall tester: 4");
		dyrehage.registrerNyArt(love);
		if (!(dyrehage.registrerNyArt(new DyrSomIndivider("Løvinne", "Leo", "Felidae", "2", true)))) {
			System.out.println("Arten finnes fra før - Test 1 bestått");
		}
		System.out.println(dyrehage.finnArt(love.getNorskNavn()) + " - Test 2 bestått");
		System.out.println((dyrehage.finnArt(0)) + " - Test 3 bestått");
		if (dyrehage.finnAntArter() == 1) {
			System.out.println(dyrehage.finnAntArter() + " - Test 4 bestått");
		}
	}
}