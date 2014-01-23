/**
 * OppgaveOversikt.java
 *
 * Øving 2, vår 2014
 * Klassen er akkurat den samme, bortsett fra at ArrayList brukes for objektet studenter.
 */

import java.util.ArrayList;

class OppgaveOversikt {
	private ArrayList<Student> studenter = new ArrayList<Student>();   
	
	public boolean regNyStudent(String navn) {
		for (int i = 0; i < studenter.size(); i++) {
			if (studenter.get(i).getNavn().equals(navn)) {
				return false;
			}
		}
		studenter.add(new Student(navn));
		return true;
	}
	
	public int finnAntStud() {
		return studenter.size();
	}
	
	public int finnAntOppgaver(String navn) {
		for (int i = 0; i < studenter.size(); i++) {
			if (studenter.get(i).getNavn().equals(navn)) {
				return studenter.get(i).getAntOppg();
			}
		}
		return -1;
	}
	
	public boolean okAntOppg(String navn, int okning) {
		for (int i = 0; i < studenter.size(); i++) {
			if (studenter.get(i).getNavn().equals(navn)) {
				studenter.get(i).setAntOppg(okning);
				return true;
			}
		}
		return false;
	}
	
	public String[] finnAlleNavn() { // Navnene til alle studentene
		String[] alleStudentNavn = new String[studenter.size()];
		for (int i = 0; i < studenter.size(); i++) {
			alleStudentNavn[i] = studenter.get(i).getNavn();
		}
		return alleStudentNavn;
	}
	
	public String toString() {
		String utskrift = "";
		for (int i = 0; i < studenter.size(); i++) {
			utskrift += studenter.get(i).getNavn() + ", antall oppgaver løst: " + studenter.get(i).getAntOppg() + "\n";
		}
		return utskrift;
	}
}