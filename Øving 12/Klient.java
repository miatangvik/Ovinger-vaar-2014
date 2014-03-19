/**
 * Klient.java
 *
 * Øving 12, vår 2014
 *
 * Lager en enkel testklient der spesielt metoden sjekkMedlemmer() blir prøvd ut.
 */

import java.text.ParseException;

class Klient {
	public static void main(String[] args) throws ParseException {
		Medlemsarkiv medlemsarkiv = new Medlemsarkiv();
		
		medlemsarkiv.nyMedlem(new Personalia("Mia", "Tangvik", "mktangvi@student.hist.no", "hemmelig"), new Dato("13022014"));
		medlemsarkiv.nyMedlem(new Personalia("David", "Harestad", "davidhar@student.hist.no", "hemmelig"), new Dato("13022014"));
		medlemsarkiv.nyMedlem(new Personalia("John Inge", "Sivertsvik", "johnis@student.hist.no", "hemmelig"), new Dato("13022014"));
		medlemsarkiv.nyMedlem(new Personalia("Julian Silden", "Langlo", "juliansl@student.hist.no", "hemmelig"), new Dato("13022014"));
		medlemsarkiv.nyMedlem(new Personalia("Magnus Dyrhaug", "Grande", "magnusdg@student.hist.no", "hemmelig"), new Dato("13022014"));
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Medlem " + (i + 1) + " hører til klassen " + medlemsarkiv.finnKlasseGittIndeks(i) + " og har medlemsnummer " + medlemsarkiv.finnMedlnrGittIndeks(i));
		}
		
		int medlNr = medlemsarkiv.finnMedlnrGittIndeks(0);
		medlemsarkiv.registrerPoeng(medlNr, 30000);
		medlNr = medlemsarkiv.finnMedlnrGittIndeks(1);
		medlemsarkiv.registrerPoeng(medlNr, 5000);
		medlNr = medlemsarkiv.finnMedlnrGittIndeks(2);
		medlemsarkiv.registrerPoeng(medlNr, 80000);
		medlNr = medlemsarkiv.finnMedlnrGittIndeks(3);
		medlemsarkiv.registrerPoeng(medlNr, 25000);
		medlNr = medlemsarkiv.finnMedlnrGittIndeks(4);
		medlemsarkiv.registrerPoeng(medlNr, 75000);
		
		if (medlemsarkiv.sjekkMedlemmer()) {
			System.out.println("\nOppgradering ok\n");
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Medlem " + (i + 1) + " hører nå til klassen " + medlemsarkiv.finnKlasseGittIndeks(i) + " og har " + medlemsarkiv.finnPoeng(medlemsarkiv.finnMedlnrGittIndeks(i), "hemmelig") + " poeng");
		}
	}
}