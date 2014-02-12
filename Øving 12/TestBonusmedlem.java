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

class TestBonusmedlem {
	public static void main(String[] args) throws Exception {
		Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
		Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
		Dato testdato = new Dato("10022008");
		System.out.println("Totalt antall tester: 8");
		
		BasicMedlem b1 = new BasicMedlem(100, ole, new Dato("15022006"));
		b1.registrerPoeng(30000);
		if (b1.finnKvalPoeng(testdato) == 0 && b1.getPoeng() == 30000) {
			System.out.println("Test 1 ok");
		}
		b1.registrerPoeng(15000);
		if (b1.finnKvalPoeng(testdato) == 0 && b1.getPoeng() == 45000) {
			System.out.println("Test 2 ok");
		}
		
		BasicMedlem b2 = new BasicMedlem(110, tove, new Dato("05032007"));
		b2.registrerPoeng(30000);
		if (b2.finnKvalPoeng(testdato) == 30000 && b2.getPoeng() == 30000) {
			System.out.println("Test 3 ok");
		}
		
		SoelvMedlem b3 = new SoelvMedlem(b2.getMedlnr(), b2.getPersonalia(), b2.getInnmeldt(), b2.getPoeng());
		b3.registrerPoeng(50000);
		if (b3.finnKvalPoeng(testdato) == 90000 && b3.getPoeng() == 90000) {
			System.out.println("Test 4 ok");
		}
		
		GullMedlem b4 = new GullMedlem(b3.getMedlnr(), b3.getPersonalia(), b3.getInnmeldt(), b3.getPoeng());
		b4.registrerPoeng(30000);
		if (b4.finnKvalPoeng(testdato) == 135000 && b4.getPoeng() == 135000) {
			System.out.println("Test 5 ok");
		}
				
		testdato = new Dato("10122008");
		if (b4.finnKvalPoeng(testdato) == 0 && b4.getPoeng() == 135000) {
			System.out.println("Test 6 ok");
		}
		
		if (!ole.okPassord("OOO")) {
			System.out.println("Test 7 ok");
		}
		if (tove.okPassord("tove")) {
			System.out.println("Test 8 ok");
		}
	}
}