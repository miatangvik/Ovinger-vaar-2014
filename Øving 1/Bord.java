/**
 * Bord.java
 *
 * Øving 1, vår 2014
 */

class Bord {
	private String[] navn;
	
	public Bord(int antallBord) {
		navn = new String[antallBord];
	}
	
	public String[] getNavn() {
		return navn;
	}
	
	public int finnAntallLedigeBord() {
		int antallLedigeBord = 0;
		for (int i = 0; i < navn.length; i++) {
			if (navn[i] == null) {
				antallLedigeBord++;
			}
		}
		return antallLedigeBord;
	}
	
	public int finnAntallOpptatteBord() {
		return navn.length - finnAntallLedigeBord();
	}
	
	public int[] finnBordGittNavn(String gittNavn) {
		int[] personBord = new int[navn.length];
		for (int i = 0; i < navn.length; i++) {
			if (gittNavn.equals(navn[i])) {
				personBord[i] = 1; // Setter et ettall på indeksene som tilsvarer reserverte bord
			}
		}
		return personBord;
	}
	
	public boolean frigi(int[] bordRyddet) {
		for (int i = 0; i < bordRyddet.length; i++) {
			int bordSomErRyddet = bordRyddet[i] - 1;
			if (navn[bordSomErRyddet] == null) {
				return false;
			} else {
				navn[bordSomErRyddet] = null;
			}
		}
		return true;
	}
	
	public boolean reserverBord(String gittNavn, int hvorMange) {
		if (finnAntallLedigeBord() == 0) {
			return false;
		} else if (finnAntallOpptatteBord() < navn.length) {
			int antallReserveringerOk = 0;
			for (int i = 0; i < navn.length; i++) {
				if (navn[i] == null && antallReserveringerOk < hvorMange) {
					navn[i] = gittNavn;
					antallReserveringerOk++;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}