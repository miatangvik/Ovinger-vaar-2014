/**
 * Klient.java
 *
 * Øving 1, vår 2014
 */

import static javax.swing.JOptionPane.*;
class Klient {
	public static void main(String[] args) {
		String restaurantNavn = showInputDialog("Restaurantnavn:");
		String etableringsAr = showInputDialog("Etableringsår:");
		int etableringsArLest = Integer.parseInt(etableringsAr);
		String antallBord = showInputDialog("Antall bord i restauranten:");
		int antallBordLest = Integer.parseInt(antallBord);
		
		Restaurant restaurant = new Restaurant(restaurantNavn, etableringsArLest, antallBordLest); // Oppretter et Restaurant-objekt
		
		String[] ALTERNATIVER = {"Reserver bord", "Finn reserverte bord", "Frigi bord", "Finn alder", "Nytt restaurantnavn", "Avslutt"};
		final int RESERVER_BORD = 0;
		final int FINN_BORDNR = 1;
		final int FRIGI_BORD = 2;
		final int FINN_ALDER = 3;
		final int NYTT_NAVN = 4;
		final int AVSLUTT = 5;
		int valg = showOptionDialog(null, "Hva vil du gjøre?", restaurant.getRestaurantNavn(), DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
		
		do {
			switch (valg) {
			case RESERVER_BORD: // Reservere et antall bord på et bestemt navn
				String navn = showInputDialog("Hva er navnet på den som reserverer?");
				String hvorMange = showInputDialog("Hvor mange bord vil han/hun reservere?");
				if (restaurant.reservereBord(navn, Integer.parseInt(hvorMange))) {
					String tabellUtskrift = "";
					for (int i = 0; i < restaurant.getNavnTabell().length; i++) {
						if (navn.equals(restaurant.getNavnTabell()[i])) {
							tabellUtskrift += (i + 1) + " ";
						}
					}
					showMessageDialog(null, "Reservasjon OK, bord nr. " + tabellUtskrift + "har blitt reservert");
				} else {
					showMessageDialog(null, "Reservasjon ikke OK");
				}
				valg = showOptionDialog(null, "Hva vil du gjøre?", restaurant.getRestaurantNavn(), DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
				break;
            case FINN_BORDNR: // Finne alle bordene som er reservert på et bestemt navn
				String bordGittNavn = showInputDialog("Hvem vil du sjekke hvilke bord har reservert? Skriv et navn:");
				int[] reserverteBord = restaurant.personBord(bordGittNavn);
				String reserverteBordUtskrift = "";
            	for (int i = 0; i < reserverteBord.length; i++) {
            		if (reserverteBord[i] != 0) {
            			reserverteBordUtskrift += (i + 1) + " ";
            		}
            	}
            	showMessageDialog(null, bordGittNavn + " har reservert bord nummer " + reserverteBordUtskrift);
            	valg = showOptionDialog(null, "Hva vil du gjøre?", restaurant.getRestaurantNavn(), DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
            	break;
            case FRIGI_BORD: // Frigi en rekke bord, gitt bordnummer
            	int bordRyddet = 0;
            	String antallBordRyddet = showInputDialog("Hvor mange bord har blitt ryddet?");
            	int[] hvilkeBordRyddetLest = new int[Integer.parseInt(antallBordRyddet)];
            	String hvilkeBordRyddet = showInputDialog("Hvilke bordnummer har blitt ryddet? (Skriv et tall)");
            	hvilkeBordRyddetLest[0] = Integer.parseInt(hvilkeBordRyddet);
            	bordRyddet++;
            	for (int i = 0; i < restaurant.getNavnTabell().length; i++) {
            		if (bordRyddet <= hvilkeBordRyddetLest.length) {
            			hvilkeBordRyddet = showInputDialog("Hvilke bordnummer har blitt ryddet? (Skriv et tall)");
            			hvilkeBordRyddetLest[i] = Integer.parseInt(hvilkeBordRyddet);
            			bordRyddet++;
            		}
            	}
            	if (restaurant.ryddet(hvilkeBordRyddetLest)) {
            		showMessageDialog(null, "Bordene er ryddet og klar for ny reservasjon");
            	} else {
            		showMessageDialog(null, "Bordet har ikke vært brukt");
            	}
            	valg = showOptionDialog(null, "Hva vil du gjøre?", restaurant.getRestaurantNavn(), DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
            	break;
            case FINN_ALDER: // Finne hvor gammel restauranten er
            	showMessageDialog(null, "Restauranten er " + restaurant.finnRestaurantAlder() + " år gammel");
            	valg = showOptionDialog(null, "Hva vil du gjøre?", restaurant.getRestaurantNavn(), DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
            	break;
            case NYTT_NAVN: // Gi restauranten et nytt navn
            	restaurant.setNavn(showInputDialog("Hva er restaurantens nye navn?"));
            	valg = showOptionDialog(null, "Hva vil du gjøre?", restaurant.getRestaurantNavn(), DEFAULT_OPTION, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
            	break;
            default:
            	break;
			}
		} while (valg != AVSLUTT);
	}
}