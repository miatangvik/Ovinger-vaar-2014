/**
 * ValutaListe.java
 *
 * Øving 6, vår 2014
 */

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;

class Vindu extends JFrame {
	private JLabel tekst = new JLabel("Velg fravaluta til venstre på listen og tilvaluta til høyre på listen:");
	private DefaultListModel listeinnhold = new DefaultListModel();
	private JList valutaliste1 = new JList(listeinnhold);
	private JList valutaliste2 = new JList(listeinnhold);
	private ArrayList<Valuta> valuta = new ArrayList<Valuta>();
	
	public Vindu() {
		setTitle("Valutakalkulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ListePanel sentrum = new ListePanel();
		add(sentrum, BorderLayout.CENTER);
		
		add(tekst, BorderLayout.NORTH);
		pack();
	}
	
	private class ListePanel extends JPanel {
		public ListePanel() {
			setLayout(new GridLayout(1, 2, 5, 0));
			listeinnhold.addElement("Ny valuta");
			
			valuta.add(new Valuta("Euro", 8.10, 1));
			valuta.add(new Valuta("US Dollar", 6.23, 1));
			valuta.add(new Valuta("Britiske pund", 12.27, 1));
			valuta.add(new Valuta("Svenske kroner", 88.96, 100));
			valuta.add(new Valuta("Danske kroner", 108.75, 100));
			valuta.add(new Valuta("Yen", 5.14, 100));
			valuta.add(new Valuta("Islandske kroner", 9.16, 100));
			valuta.add(new Valuta("Norske kroner", 100, 100));
			
			for (int i = 0; i < valuta.size(); i++) {
				listeinnhold.addElement(valuta.get(i).getValutaNavn());
			}
			
			valutaliste1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			valutaliste2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane rullefeltMedListe1 = new JScrollPane(valutaliste1);
			JScrollPane rullefeltMedListe2 = new JScrollPane(valutaliste2);
			add(rullefeltMedListe1);
			add(rullefeltMedListe2);
			valutaliste1.addListSelectionListener(new ListeboksLytter());
			valutaliste2.addListSelectionListener(new ListeboksLytter());
		}
	}
	
	private class ListeboksLytter implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent hendelse) {
			int valg1 = valutaliste1.getSelectedIndex();
			int valg2 = valutaliste2.getSelectedIndex();
			double nyKursLest = 0.0;
			int nyEnhetLest = 0;
			if (valg1 == 0 || valg2 == 0) {
				String nyValuta = showInputDialog("Valutaens navn:");
				String nyKurs = showInputDialog("Valutaens kurs (bytt ut komma med punktum):");
				String nyEnhet = showInputDialog("Valutaens enhet (i forhold til NOK):");
				nyKursLest = Double.parseDouble(nyKurs);
				nyEnhetLest = Integer.parseInt(nyEnhet);
				if (nyValuta != null && nyKursLest != 0 && nyEnhetLest != 0) {
					listeinnhold.addElement(nyValuta);
					valuta.add(new Valuta(nyValuta, nyKursLest, nyEnhetLest));
					valutaliste1.clearSelection();
					valutaliste2.clearSelection();
				}
			} else if (valg1 > 0 && valg2 > 0) {
				String belop = showInputDialog("Oppgi beløp (bytt ut komma med punktum):");
				double belopLest = Double.parseDouble(belop);
				double belopINok = belopLest * valuta.get(valg1 - 1).getValutaKurs() / valuta.get(valg1 - 1).getValutaEnhet();
				double svar = belopINok * valuta.get(valg2 - 1).getValutaEnhet() / valuta.get(valg2 - 1).getValutaKurs();
				showMessageDialog(null, "Det nye beløpet er " + (new DecimalFormat("##.##").format(svar)) + " " + valuta.get(valg2 - 1).getValutaNavn());
				valutaliste1.clearSelection();
				valutaliste2.clearSelection();
			}
		}
	}
}

class Valuta {
	private String valutaNavn;
	private double valutaKurs;
	private int valutaEnhet;
	
	public Valuta(String valutaNavn, double valutaKurs, int valutaEnhet) {
		this.valutaNavn = valutaNavn;
		this.valutaKurs = valutaKurs;
		this.valutaEnhet = valutaEnhet;
	}
	
	public String getValutaNavn() {
		return valutaNavn;
	}
	
	public double getValutaKurs() {
		return valutaKurs;
	}
	
	public int getValutaEnhet() {
		return valutaEnhet;
	}
}

class ValutaListe {
	public static void main(String[] args) {
		Vindu etVindu = new Vindu();
		etVindu.setVisible(true);
		etVindu.setSize(425, 300);
	}
}