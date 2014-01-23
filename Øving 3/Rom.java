/**
 * RomTest.java
 *
 * Øving 3, vår 2014
 */

import static javax.swing.JOptionPane.*;
import java.util.ArrayList;
class Rom {
	private ArrayList<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();
	private int romnr;
	private int storrelse;
	
	public Rom(int romnr, int storrelse) {
		this.romnr = romnr;
		this.storrelse = storrelse;
	}
	
	public int getRomnr() {
		return romnr;
	}
	
	public int getStorrelse() {
		return storrelse;
	}
	
	public boolean reserverRom(Reservasjon r) {
		for (int i = 0; i < reservasjoner.size(); i++) {
			if (reservasjoner.get(i).overlapp(r.getFraTid(), r.getTilTid())) {
				return false;
			}
		}
		reservasjoner.add(r);
		return true;
	}
	
	public String reservasjonsUtskrift() {
		String utskrift = "";
		for (int i = 0; i < reservasjoner.size(); i++) {
			utskrift += reservasjoner.get(i).toString() + "\n";
		}
		return utskrift;
	}
	
	public String toString() {
		return "Rom nummer " + romnr + " har plass til " + storrelse + " personer og har følgende reservasjoner registrert:\n" + reservasjonsUtskrift();
	}

	public static void main(String[] args) {
		System.out.println("Totalt antall tester: 2");
		Rom rom = new Rom(12345, 12);
		if (rom.reserverRom(new Reservasjon(new Tidspunkt(201401161000L), new Tidspunkt(201401161200L), new Kunde("Mia", "40637430")))) {
			System.out.println("Test 1 vellykket");
		}
		if (!(rom.reserverRom(new Reservasjon(new Tidspunkt(201401161000L), new Tidspunkt(201401161500L), new Kunde("John Inge", "40555430"))))) {
			System.out.println("Test 2 vellykket");
		}
	}
}