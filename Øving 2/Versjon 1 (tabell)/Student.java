/**
 * Student.java
 *
 * Øving 2, vår 2014
 */

class Student {
	private final String navn;
	private int antOppg;
	
	public Student(String navn) {
		this.navn = navn;
	}
	
	public String getNavn() {
		return navn;
	}
	
	public int getAntOppg() {
		return antOppg;
	}
	
	public void setAntOppg(int antOppg) {
		if (antOppg < 0) {
			throw new IllegalArgumentException("Antall oppgaver kan ikke være negativt.");
		}
		this.antOppg += antOppg;
	}
	
	public String toString() {
		return "Navn: " + navn + ", antall oppgaver godkjent: " + antOppg;
	}
}