/**
 * Restaurant.java
 *
 * Øving 1, vår 2014
 */

// I stedet for å bruke java.util.* lenger ned, kunne jeg heller skrevet dette øverst:

// import java.util.GregorianCalendar;
// import static java.util.Calendar.*;

class Restaurant {
	private String restaurantNavn;
	private final int etableringsAr;
	private Bord bord;
	
	public Restaurant(String restaurantNavn, int etableringsAr, int antallBord) {
		this.restaurantNavn = restaurantNavn;
		this.etableringsAr = etableringsAr;
		bord = new Bord(antallBord);
	}
	
	public String getRestaurantNavn() {
		return restaurantNavn;
	}
	
	public void setNavn(String nyttRestaurantNavn) {
		restaurantNavn = nyttRestaurantNavn;
	}
	
	public int getEtableringsAr() {
		return etableringsAr;
	}
	
	public String[] getNavnTabell() {
		return bord.getNavn();
	}
	
	public int finnRestaurantAlder() {
		java.util.GregorianCalendar dagensDato = new java.util.GregorianCalendar();
		return dagensDato.get(java.util.Calendar.YEAR) - etableringsAr;
	}
	
	public int antallLedigeBord() {
		return bord.finnAntallLedigeBord();
	}
	
	public int antallOpptatteBord() {
		return bord.finnAntallOpptatteBord();
	}
	
	public boolean reservereBord(String navn, int hvorMange) {
		return bord.reserverBord(navn, hvorMange);
	}
	
	public int[] personBord(String navn) {
		return bord.finnBordGittNavn(navn);
	}
	
	public boolean ryddet(int[] bordRyddet) {
		return bord.frigi(bordRyddet);
	}
}