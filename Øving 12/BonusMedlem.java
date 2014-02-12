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
	
	public int finnKvalPoeng(Dato idag) {
		return (innmeldtDato.dagerForskjell(idag) > 365) ? 0 : poeng;
	}
	
	public boolean okPassord(String passord) {
		return pers.okPassord(passord);
	}
	
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