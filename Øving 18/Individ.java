/**
 * Individ.java
 *
 * Øving 18, vår 2014
 */

class Individ {
	private final String individnavn;
	private final String fodselsdato;
	
	public Individ(String individnavn, String fodselsdato) {
		this.individnavn = individnavn;
		this.fodselsdato = fodselsdato;
	}
	
	public String getIndividnavn() {
		return individnavn;
	}
	
	public String getFodselsdato() {
		return fodselsdato;
	}
}