import static javax.swing.JOptionPane.*;

/**
 * GodkjenningBGS.java
 *
 * Øving 2, vår 2014
 * Program som kan brukes til å prøve ut metodene laget i øvingen.
 */

class GodkjenningBGS {
	public final String NY_STUDENT = "Ny student";
	public final String AVSLUTT = "Avslutt";
	private String[] muligeValg = {NY_STUDENT, AVSLUTT}; // Første gang, ingen studenter registrert
	
	private OppgaveOversikt oversikt;
	public GodkjenningBGS(OppgaveOversikt oversikt) {
		this.oversikt = oversikt;
  	}
	
	/**
	 * Metoden leser inn valget som en streng, og returnerer den.
	 * Valget skal være argument til metoden utforValgtOppgave().
	 * Hvis programmet skal avsluttes, returneres null.
	 */
	
	public String lesValg() {
		int antStud = oversikt.finnAntStud();
		String valg = (String) showInputDialog(null, "Velg fra listen, " + antStud + " studenter:",  "Godkjente oppgaver", DEFAULT_OPTION, null, muligeValg, muligeValg[0]);
		if (AVSLUTT.equals(valg)) {
			valg = null;
		}
		return valg;
	}
	
	/**
	 * Metode som sørger for at ønsket valg blir utført.
	 */
	
	public void utforValgtOppgave(String valg) {
		if (valg != null && !valg.equals(AVSLUTT)) {
			if (valg.equals(NY_STUDENT)) {
				registrerNyStudent();
			} else {
				registrerOppgaver(valg); // Valg er navnet til studenten
			}
		}
	}
	
	/**
	 * Metoden registrere ny student.
	 * Hvis student med dette navnet allerede eksisterer, skjer ingen registrering.
	 * Resultatet av operasjonen skrives ut til brukeren.
	 */
	
	private void registrerNyStudent() {
		String navnNyStud = null;
		do {
			navnNyStud = showInputDialog("Oppgi navn:");
		} while (navnNyStud == null);
		
		navnNyStud = navnNyStud.trim();
		if (oversikt.regNyStudent(navnNyStud)) {
			showMessageDialog(null, navnNyStud + " er registrert.");
			String[] alleNavn = oversikt.finnAlleNavn();
			String[] nyMuligeValg = new String[alleNavn.length + 2];
			for (int i = 0; i < alleNavn.length; i++) {
				nyMuligeValg[i] = alleNavn[i];
			}
			nyMuligeValg[alleNavn.length] = NY_STUDENT;
			nyMuligeValg[alleNavn.length + 1] = AVSLUTT;
			muligeValg = nyMuligeValg;
		} else  {
			showMessageDialog(null, navnNyStud + " er allerede registrert.");
		}
	}
	
	/**
	 * Metoden registrerer oppgaver for en navngitt student.
	 * Brukerinput kontrolleres ved at det må kunne tolkes som et tall.
	 * Registreringsmetoden (i klassen Student) kan kaste unntaksobjekt IllegalArgumentException.
	 * Dette fanges også opp. I begge tilfeller må brukeren gjenta inntasting inntil ok data.
	 * Endelig skrives det ut en melding om antall oppgaver studenten nå har registrert.
	 */
    
	private void registrerOppgaver(String studNavn) {
		String melding = "Oppgi antall nye oppgaver som skal godkjennes for " + studNavn +": ";
		int antOppgOkning = 0;
		boolean registrert = false;
		do { // Gjentar inntil registrering aksepteres av objektet oversikt
			try {
				antOppgOkning = lesHeltall(melding);
				oversikt.okAntOppg(studNavn, antOppgOkning); // Kan ikke returnere false, pga navn alltid gyldig
				registrert = true; // Kommer hit bare dersom exception ikke blir kastet
			} catch (IllegalArgumentException e) {  // Kommer hit hvis studenter får negativt antall oppgaver
				melding = "Du skrev " + antOppgOkning + ". \nIkke godkjent økning for " + studNavn + ". Prøv igjen: ";
			}
		} while (!registrert);

		melding = "Totalt antall oppgaver registrert på " + studNavn + " er " + oversikt.finnAntOppgaver(studNavn) + ".";
		showMessageDialog(null, melding);
    }
	
	/* Hjelpemetode som går i løkke inntil brukeren skriver et heltall. */
	
	private int lesHeltall(String melding) {
		int tall = 0;
		boolean ok = false;
		do {  // gjentar inntil brukerinput kan tolkes som tall
			String tallLest = showInputDialog(melding);
			try {
				tall = Integer.parseInt(tallLest);
				ok = true;
			} catch (Exception e) {
				showMessageDialog(null, "Kan ikke tolke det du skrev som tall. Prøv igjen.");
			}
		} while (!ok);
			return tall;
	}
}

/**
 * Hovedprogrammet. Går i løkke og lar brukeren gjøre valg.
 */

class Klient {
	public static void main(String[] args) {
		OppgaveOversikt oversikt = new OppgaveOversikt();
		GodkjenningBGS bgs = new GodkjenningBGS(oversikt);
		
		String valg = bgs.lesValg();
		while (valg != null) {
			bgs.utforValgtOppgave(valg);
			valg = bgs.lesValg();
		}
	
		/* Prøver toString() */
		System.out.println("\nHer kommer informasjon om alle studentene: ");
		System.out.println(oversikt);
	}
}