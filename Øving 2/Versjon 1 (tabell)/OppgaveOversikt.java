/**
 * OppgaveOversikt.java
 *
 * Øving 2, vår 2014
 */

class OppgaveOversikt {
	private Student[] studenter = new Student[5];   
	private int antStud = 0;
	
	public boolean regNyStudent(String navn) {
		for (int i = 0; i < antStud; i++) {
			if (studenter[i].getNavn().equals(navn)) {
				return false;
			}
		}
		if (antStud == studenter.length) {
			utvidTabell();
		}
		studenter[antStud] = new Student(navn);
		antStud++;
		return true;
	}
	
	private void utvidTabell() {
		Student[] nyTab = new Student[studenter.length + 5];
		for (int i = 0; i < antStud; i++) {
			nyTab[i] = studenter[i];
		}
		studenter = nyTab;
	}
	
	public int finnAntStud() {
		return antStud;
	}
	
	public int finnAntOppgaver(String navn) {
		for (int i = 0; i < antStud; i++) {
			if (studenter[i].getNavn().equals(navn)) {
				return studenter[i].getAntOppg();
			}
		}
		return -1;
	}
	
	public boolean okAntOppg(String navn, int okning) {
		for (int i = 0; i < antStud; i++) {
			if (studenter[i].getNavn().equals(navn)) {
				studenter[i].setAntOppg(okning);
				return true;
			}
		}
		return false;
	}
	
	public String[] finnAlleNavn() { // Navnene til alle studentene
		String[] alleStudentNavn = new String[antStud];
		for (int i = 0; i < antStud; i++) {
			alleStudentNavn[i] = studenter[i].getNavn();
		}
		return alleStudentNavn;
	}
	
	public String toString() {
		String utskrift = "";
		for (int i = 0; i < antStud; i++) {
			utskrift += studenter[i].getNavn() + ", antall oppgaver løst: " + studenter[i].getAntOppg() + "\n";
		}
		return utskrift;
	}
}