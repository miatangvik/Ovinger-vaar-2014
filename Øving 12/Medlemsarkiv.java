/**
 * Medlemsarkiv.java
 *
 * Øving 12, vår 2014
 */

import java.util.Random;

class Medlemsarkiv {
	private ArrayList<BonusMedlem> medlemmer = new ArrayList<BonusMedlem>();
	
	/**
	 * finnPoeng() skal ta medlemsnummer og passord som argument og returnere antall poeng denne kunden har spart opp.
	 * Returner en negativ verdi hvis medlem med dette nr ikke fins, eller passord er ugyldig.
	 */
	
	public int finnPoeng(int medlNr, String passord) {
		if (!sjekkMedlem(medlNr)) {
			return -1;
		}
		for (int i = 0; i < medlemmer.size(); i++) {
			if (!(medlemmer.get(i).getPersonalia().okPassord(passord))) {
				return -1;
			}
		}
		int indeks = finnMedlemsIndeks(medlNr);
		return medlemmer.get(indeks).getPoeng();
		/*for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i).getMedlnr().equals(medlNr)) {
				return medlemmer.get(i).getPoeng();
			}
		}
		return -1;*/
	}
	
	public boolean registrerPoeng(int medlNr, int poeng) {
		if (!sjekkMedlem(medlNr)) {
			return false;
		}
		int indeks = finnMedlemsIndeks(medlNr);
		medlemmer.get(indeks).registrerPoeng(poeng);
		return true;
	}
	
	public int nyMedlem(Personalia pers, Dato innmeldt) {
		Personalia person = new Personalia("", "", "", ""); // String fornavn, String etternavn, String ePostadr, String passord
		BasicMedlem medlem = new BasicMedlem(finnLedigNr(), person, new Dato("08041994"));
		medlemmer.add(medlem);
	}
	
	private int finnLedigNr() {
		
	}
	
	private boolean sjekkMedlem(int medlNr) {
		for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i).getMedlnr().equals(medlNr)) {
				return true;
			}
		}
		return false;
	}
	
	private int finnMedlemsIndeks(int medlNr) {
		for (int i = 0; i < medlemmer.size(); i++) {
			if (medlemmer.get(i).getMedlnr().equals(medlNr)) {
				return i;
			}
		}
		return -1;
	}
}