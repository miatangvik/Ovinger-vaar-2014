/**
 * Klient.java
 *
 * Øving 3, vår 2014
 */

import static javax.swing.JOptionPane.*;
class Klient {
	public static void main(String[] args) {
		final String[] ALTERNATIVER = {"Registrer rom", "Reserver rom", "Skriv ut informasjon", "Finn rom", "Avslutt"};
		
		Konferansesenter konferansesenter = new Konferansesenter();
		
		int valg = showOptionDialog(null, "Hva vil du gjøre?", "Konferansesenter", DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
		
		do {
			switch (valg) {
			case 0: // Registrer rom
				String romnr = showInputDialog("Hvilket romnummer skal rommet ha?");
				String storrelse = showInputDialog("Hvor mange personer er det plass til i rommet?");
				if (konferansesenter.registrerRom(Integer.parseInt(romnr), Integer.parseInt(storrelse))) {
					showMessageDialog(null, "Rom registrert.");
				} else {
					showMessageDialog(null, "Romnummeret finnes fra før.");
				}
				valg = showOptionDialog(null, "Hva vil du gjøre?", "Konferansesenter", DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
				break;
			case 1: // Reserver rom
				String tidspunktFra = showInputDialog("Fra når skal rommet reserveres? Oppgi sammenhengende i formatet ååååmmddttmm.");
				String tidspunktTil = showInputDialog("Når skal reservasjonen gjelde til? Oppgi sammenhengende i formatet ååååmmddttmm.");
				String antPersoner = showInputDialog("Hvor mange personer skal det være plass til i rommet?");
				String kundeNavn = showInputDialog("Hva er kundens navn?");
				String kundeTlf = showInputDialog("Hva er kundens telefonnummer?");
				Tidspunkt fra = new Tidspunkt(Long.parseLong(tidspunktFra));
				Tidspunkt til = new Tidspunkt(Long.parseLong(tidspunktTil));
				Kunde k = new Kunde(kundeNavn, kundeTlf);
				if (konferansesenter.reserverRom(fra, til, Integer.parseInt(antPersoner), k)) {
					showMessageDialog(null, "Reservasjon ok.");
				} else {
					showMessageDialog(null, "Reservasjon ikke ok.");
				}
				valg = showOptionDialog(null, "Hva vil du gjøre?", "Konferansesenter", DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
				break;
			case 2: // Skriver ut all registrert informasjon
				String utskrift = "Antall rom registrert: " + konferansesenter.finnAntRom() + "\n";
				//konferansesenter.finnRomGittIndeks(showInputDialog("Skriv indeks:")); HVORDAN BRUKE DENNE HER??
				utskrift += konferansesenter.toString();
				showMessageDialog(null, utskrift);
				valg = showOptionDialog(null, "Hva vil du gjøre?", "Konferansesenter", DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
				break;
			case 3: // Finner et rom gitt romnummer og skriver ut informasjonen om rommet
				String romnrInfo = showInputDialog("Hvilket romnummer vil du ha informasjon om?");
				Rom aktueltRom = konferansesenter.finnRomGittRomnr(Integer.parseInt(romnrInfo));
				String utskrift2 = aktueltRom.toString();
				showMessageDialog(null, utskrift2);
				valg = showOptionDialog(null, "Hva vil du gjøre?", "Konferansesenter", DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
				break;
			default:
				break;
			}
		} while (valg != 4);
	}
}