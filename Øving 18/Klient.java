/**
 * Klient.java
 *
 * Øving 18, vår 2014
 */

import javax.swing.JOptionPane.*;

class Klient {
	public static void main(String[] args) {
		Dyrehage dyrehage = new Dyrehage();
		boolean registreringOk = dyrehage.registrerNyArt(new DyrSomIndivider("Løve", "Leo", "Felidae", "2-3", true));
		if (registreringOk) {
			System.out.println("Registrering ok");
		}
		DyrSomIndivider finnLove = (DyrSomIndivider) dyrehage.finnArt("Løve");
		System.out.println("Arten på første plass i ArrayListen har registrert følgende informasjon: " + dyrehage.finnArt(0));
		System.out.println("Antall arter registrert er " + dyrehage.finnAntArter());
		dyrehage.registrerNyttIndivid("Løve", new Individ("Simba", "14. mars 2014"));
		dyrehage.finnIndivid("Løve", "Simba");
		System.out.println("Individets fødselsdato er " + dyrehage.finnFDatoIndivid("Løve", "Simba"));
	}
}