/**
 * Tribune.java
 *
 * Vår 2014, øving 14
 */

class Tribune {
	private final String tribunenavn;
	private final int kapasitet;
	private final int pris;
	
	public Tribune(String tribunenavn, int kapasitet, int pris) {
		this.tribunenavn = tribunenavn;
		this.kapasitet = kapasitet;
		this.pris = pris;
	}
	
	public String getTribunenavn() {
		return tribunenavn;
	}
	
	public int getKapasitet() {
		return kapasitet;
	}
	
	public int getPris() {
		return pris;
	}
	
	public int finnAntallSolgteBilletter() {
		return 0;
	}
	
	public int finnInntekt() {
		return pris * finnAntallSolgteBilletter();
	}
	
	public Billett[] kjopBilletter(int antBilletter) {
		return null;
	}
	
	public Billett[] kjopBilletter(String[] billetteiere) {
		return null;
	}
	
	public String toString() {
		return "Tribune: " + tribunenavn + " Kapasitet: " + kapasitet + " Pris: " + pris;
	}
}

/**
 * Ståtribune
 */

class Staa extends Tribune {
	private int antSolgteBilletter;
		
	public Staa(String tribunenavn, int kapasitet, int pris) {
		super(tribunenavn, kapasitet, pris);
	}
	
	public int finnAntallSolgteBilletter() {
		return antSolgteBilletter;
	}
	
	public Billett[] kjopBilletter(int antBilletter) {
		Billett[] billetter = new StaaplassBillett[antBilletter];
		if (antSolgteBilletter >= super.getKapasitet() || antBilletter > super.getKapasitet()) {
			return null;
		}
		for (int i = 0; i < billetter.length; i++) {
			billetter[i] = new StaaplassBillett(super.getTribunenavn(), super.getPris());
		}
		antSolgteBilletter += antBilletter;
		return billetter;
	}
	
	public Billett[] kjopBilletter(String[] billetteiere) {
		Billett[] billetter = new StaaplassBillett[billetteiere.length];
		if (antSolgteBilletter >= super.getKapasitet() || billetteiere.length > super.getKapasitet()) {
			return null;
		}
		for (int i = 0; i < billetter.length; i++) {
			billetter[i] = new StaaplassBillett(super.getTribunenavn(), super.getPris());
		}
		antSolgteBilletter += billetteiere.length;
		return billetter;
	}
	
	public String toString() {
		String res = super.toString();
		return res += " Antall solgte billetter: " + finnAntallSolgteBilletter() + " Inntekt: " + super.finnInntekt();
	}
}

/**
 * Sittetribune
 */

class Sitte extends Tribune {
	private int[] antOpptatt; // tabellstørrelse: antall rader
	
	public Sitte(String tribunenavn, int kapasitet, int pris, int antRader) {
		super(tribunenavn, kapasitet, pris);
		antOpptatt = new int[antRader];
	}
	
	public int finnAntallSolgteBilletter() {
		int antSolgteBilletter = 0;
		for (int i = 0; i < antOpptatt.length; i++) {
			antSolgteBilletter += antOpptatt[i];
		}
		return antSolgteBilletter;
	}
	
	public Billett[] kjopBilletter(int antBilletter) {
		Billett[] billetter = new SitteplassBillett[antBilletter];
		if (antBilletter > (super.getKapasitet() / antOpptatt.length)) {
			return null;
		}
		for (int i = 0; i < billetter.length; i++) {
			billetter[i] = new SitteplassBillett(super.getTribunenavn(), super.getPris(), forsteLedigeRad(), forsteLedigePlass());
		}
		return billetter;
	}
	
	public Billett[] kjopBilletter(String[] billetteiere) {
		Billett[] billetter = new SitteplassBillett[billetteiere.length];
		if (finnAntallSolgteBilletter() >= super.getKapasitet() || billetteiere.length > super.getKapasitet()) {
			return null;
		}
		for (int i = 0; i < billetter.length; i++) {
			billetter[i] = new SitteplassBillett(super.getTribunenavn(), super.getPris(), forsteLedigeRad(), forsteLedigePlass());
		}
		return billetter;
	}
	
	private int forsteLedigeRad() {
		for (int i = 0; i < antOpptatt.length; i++) {
			if (!(antOpptatt[i] >= (super.getKapasitet() / antOpptatt.length))) {
				return i + 1;
			}
		}
		return 0;
	}
	
	private int forsteLedigePlass() {
		for (int i = 0; i < antOpptatt.length; i++) {
			if (!(antOpptatt[i] >= (super.getKapasitet() / antOpptatt.length))) {
				antOpptatt[i]++;
				return antOpptatt[i];
			}
		}
		return 0;
	}
	
	public String toString() {
		String res = super.toString();
		return res += " Antall rader: " + antOpptatt.length + " Antall solgte billetter: " + finnAntallSolgteBilletter() + " Inntekt: " + super.finnInntekt();
	}
}

/**
 * VIP-tribune
 */

class Vip extends Tribune {
	private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad
	
	public Vip(String tribunenavn, int kapasitet, int pris, int antRader) {
		super(tribunenavn, kapasitet, pris);
		tilskuer = new String[antRader][super.getKapasitet() / antRader];
	}
	
	public int finnAntallSolgteBilletter() {
		int antSolgteBilletter = 0;
		for (int i = 0; i < tilskuer.length; i++) {
			for (int j = 0; j < tilskuer[0].length; j++) {
				if (tilskuer[i][j] != null) {
					antSolgteBilletter++;
				}
			}
		}
		return antSolgteBilletter;
	}
	
	public Billett[] kjopBilletter(int antBilletter) {
		return null;
	}
	
	public Billett[] kjopBilletter(String[] billetteiere) {
		Billett[] billetter = new SitteplassBillett[billetteiere.length];
		if (finnAntallSolgteBilletter() >= super.getKapasitet() || billetteiere.length > (super.getKapasitet() / tilskuer.length)) {
			return null;
		}
		for (int i = 0; i < tilskuer.length; i++) {
			billetter[i] = new SitteplassBillett(super.getTribunenavn(), super.getPris(), forsteLedigeRad(), forsteLedigePlass(billetteiere));
			/*for (int j = 0; j < tilskuer[0].length; j++) {
				tilskuer[i][j] = billetteiere[i];
			}*/
		}
		return billetter;
	}
	
	private int forsteLedigeRad() {
		for (int i = 0; i < tilskuer.length; i++) {
			for (int j = 0; j < tilskuer[0].length; j++) {
				if (tilskuer[i][j] == null) {
					return j + 1;
				}
			}
		}
		return 0;
	}
	
	private int forsteLedigePlass(String[] billetteiere) {
		for (int i = 0; i < tilskuer.length; i++) {
			for (int j = 0; j < tilskuer[0].length; i++) {
				if (tilskuer[forsteLedigeRad() - 1][j] == null) {
					tilskuer[forsteLedigeRad() - 1][j] = billetteiere[i];
					return i + 1;
				}
			}
		}
		return 0;
	}
	
	public String toString() {
		String res = super.toString();
		return res += " Antall rader: " + tilskuer.length + " Antall solgte billetter: " + finnAntallSolgteBilletter() + " Inntekt: " + super.finnInntekt();
	}
}