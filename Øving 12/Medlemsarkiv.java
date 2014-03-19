/**
 * Medlemsarkiv.java
 *
 * Øving 12, vår 2014
 */

import java.util.ArrayList;
import java.util.Random;

class Medlemsarkiv {
	private ArrayList<BonusMedlem> medlemmer = new ArrayList<BonusMedlem>();
	
	public int finnPoeng(int medlNr, String passord) {
		if (!sjekkMedlemsEksistens(medlNr)) {
			return -1;
		}
		for (int i = 0; i < medlemmer.size(); i++) {
			if (!(medlemmer.get(i).getPersonalia().okPassord(passord))) {
				return -1;
			}
		}
		int indeks = finnMedlemsIndeks(medlNr);
		return medlemmer.get(indeks).getPoeng();
	}
	
	public boolean registrerPoeng(int medlNr, int poeng) {
		if (!sjekkMedlemsEksistens(medlNr)) {
			return false;
		}
		int indeks = finnMedlemsIndeks(medlNr);
		medlemmer.get(indeks).registrerPoeng(poeng);
		return true;
	}
	
	public int nyMedlem(Personalia pers, Dato innmeldt) {
		BasicMedlem medlem = new BasicMedlem(finnLedigNr(), pers, innmeldt);
		medlemmer.add(medlem);
		return medlem.getMedlnr();
	}
	
	public boolean sjekkMedlemmer() {
		for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i) instanceof BasicMedlem && medlemmer.get(i).getPoeng() >= 25000 && medlemmer.get(i).getPoeng() < 75000) {
				int medlNr = medlemmer.get(i).getMedlnr();
				Personalia pers = medlemmer.get(i).getPersonalia();
				Dato innmeldtDato = medlemmer.get(i).getInnmeldt();
				int poeng = medlemmer.get(i).getPoeng();
				SoelvMedlem soelvMedlem = new SoelvMedlem(medlNr, pers, innmeldtDato, poeng);
				medlemmer.set(i, soelvMedlem);
			} else if (medlemmer.get(i) instanceof BasicMedlem && medlemmer.get(i).getPoeng() >= 75000) {
				int medlNr = medlemmer.get(i).getMedlnr();
				Personalia pers = medlemmer.get(i).getPersonalia();
				Dato innmeldtDato = medlemmer.get(i).getInnmeldt();
				int poeng = medlemmer.get(i).getPoeng();
				GullMedlem gullMedlem = new GullMedlem(medlNr, pers, innmeldtDato, poeng);
				medlemmer.set(i, gullMedlem);
			} else if (medlemmer.get(i) instanceof SoelvMedlem && medlemmer.get(i).getPoeng() >= 75000) {
				int medlNr = medlemmer.get(i).getMedlnr();
				Personalia pers = medlemmer.get(i).getPersonalia();
				Dato innmeldtDato = medlemmer.get(i).getInnmeldt();
				int poeng = medlemmer.get(i).getPoeng();
				GullMedlem gullMedlem = new GullMedlem(medlNr, pers, innmeldtDato, poeng);
				medlemmer.set(i, gullMedlem);
			}
		}
		return true;
	}
	
	public int finnMedlnrGittIndeks(int indeks) {
		return medlemmer.get(indeks).getMedlnr();
	}
	
	public String finnKlasseGittIndeks(int indeks) {
		return medlemmer.get(indeks).getClass().getName();
	}
	
	private int finnLedigNr() {
		Random randomTall = new Random();
		int medlNrTest = randomTall.nextInt(1000);
		for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i).getMedlnr() == medlNrTest) {
				medlNrTest = randomTall.nextInt(1000);
			}
		}
		return medlNrTest;
	}
	
	private boolean sjekkMedlemsEksistens(int medlNr) {
		for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i).getMedlnr() == medlNr) {
				return true;
			}
		}
		return false;
	}
	
	private int finnMedlemsIndeks(int medlNr) {
		for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i).getMedlnr() == medlNr) {
				return i;
			}
		}
		return -1;
	}
}