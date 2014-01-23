/**
 * Konferansesenter.java
 *
 * Øving 3, vår 2014
 */

import java.util.ArrayList;
class Konferansesenter {
	private ArrayList<Rom> rom = new ArrayList<Rom>();
	
	public boolean reserverRom(Tidspunkt fra, Tidspunkt til, int antPersoner, Kunde k) {
		for (int i = 0; i < rom.size(); i++) {
			if (rom.get(i).getStorrelse() >= antPersoner) {
				if (rom.get(i).reserverRom(new Reservasjon(fra, til, k))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean registrerRom(int romnr, int storrelse) {
		for (int i = 0; i < rom.size(); i++) {
			if (rom.get(i).getRomnr() == romnr) {
				return false; // Returnerer false hvis romnummeret eksisterer fra før
			}
		}
		rom.add(new Rom(romnr, storrelse));
		return true;
	}
	
	public int finnAntRom() {
		return rom.size();
	}
	
	public boolean finnRomGittIndeks(int indeks) {
		if (indeks > rom.size() - 1) {
			return false;
		}
		return true;
	}
	
	public Rom finnRomGittRomnr(int romnr) {
		for (int i = 0; i < rom.size(); i++) {
			if (rom.get(i).getRomnr() == romnr) {
				return rom.get(i);
			}
		}
		return null; // Returnerer null hvis romnummeret ikke eksisterer
	}
	
	public String toString() {
		String utskrift = "";
		for (int i = 0; i < rom.size(); i++) {
			utskrift += rom.get(i).toString() + "\n";
		}
		return utskrift;
	}
}
