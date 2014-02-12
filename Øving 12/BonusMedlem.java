/**
 * BonusMedlem.java
 *
 * Øving 12, vår 2014
 */

class BonusMedlem {
	private final int medlNr;
	private final Personalia pers;
	private final Dato innmeldtDato;
	private int poeng = 0;
	
	public BonusMedlem(int medlNr, Personalia pers, Dato innmeldtDato) {
		this.medlNr = medlNr;
		this.pers = pers;
		this.innmeldtDato = innmeldtDato;
	}
	
	public BonusMedlem(int medlNr, Personalia pers, Dato innmeldtDato, int poeng) {
		this.medlNr = medlNr;
		this.pers = pers;
		this.innmeldtDato = innmeldtDato;
		this.poeng = poeng;
	}
	
	public int getMedlnr() {
		return medlNr;
	}
	
	public Personalia getPersonalia() {
		return pers;
	}
	
	public Dato getInnmeldt() {
		return innmeldtDato;
	}
	
	public int getPoeng() {
		return poeng;
	}
	
	/**
	 * finnKvalPoeng() returnerer antall poeng som kan kvalifisere til oppgradering av medlemskapet til sølv eller gull.
	 * Dersom innmeldingsdatoen ligger mindre enn 365 dager bak i tid i forhold til datoen som sendes inn som argument,
	 * returneres antall poeng. Hvis det er mer enn ett år siden kunden meldte seg inn, returneres 0 poeng.
	 */
	
	public int finnKvalPoeng(Dato idag) {
		return (innmeldtDato.dagerForskjell(idag) > 365) ? 0 : poeng;
	}
	
	/**
	 * okPassord() tar et passord som argument, og returnerer true dersom det er ok.
	 */
	
	public boolean okPassord(String passord) {
		return pers.okPassord(passord);
	}
	
	/**
	 * registrerPoeng() tar antall poeng som argument og registrerer disse i henhold til reglene foran.
	 */
	
	public void registrerPoeng(int poeng) {
		this.poeng += poeng;
	}
}

class BasicMedlem extends BonusMedlem {
	public BasicMedlem(int medlNr, Personalia pers, Dato innmeldtDato) {
		super(medlNr, pers, innmeldtDato);
	}
}

class SoelvMedlem extends BonusMedlem {
	public SoelvMedlem(int medlNr, Personalia pers, Dato innmeldtDato, int poeng) {
		super(medlNr, pers, innmeldtDato, poeng);
	}
	
	public void registrerPoeng(int poeng) {
		poeng *= 1.2;
		super.registrerPoeng((int) poeng);
	}
}

class GullMedlem extends BonusMedlem {
	public GullMedlem(int medlNr, Personalia pers, Dato innmeldtDato, int poeng) {
		super(medlNr, pers, innmeldtDato, poeng);
	}
	
	public void registrerPoeng(int poeng) {
		poeng *= 1.5;
		super.registrerPoeng((int) poeng);
	}
}