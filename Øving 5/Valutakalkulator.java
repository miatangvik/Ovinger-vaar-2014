/**
 * Valutakalkulator.java 
 *
 * Øving 5, vår 2014
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane.*;

class Vindu extends JFrame {
	private JTextField belopFelt = new JTextField();
	private JButton knapp1 = new JButton("Til SEK (fra NOK)");
	private JButton knapp2 = new JButton("Til NOK (fra SEK)");
	private JLabel tekst = new JLabel("Resultatet kommer her");
	
	public Vindu() {
		setTitle("Valutakalkulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(belopFelt, BorderLayout.NORTH);
		add(knapp1, BorderLayout.WEST);
		add(knapp2, BorderLayout.EAST);
		add(tekst, BorderLayout.SOUTH);
		pack();
		Knappelytter lytteren = new Knappelytter();
		knapp1.addActionListener(lytteren);
		knapp2.addActionListener(lytteren);
	}
	
	private class Knappelytter implements ActionListener {
		public void actionPerformed(ActionEvent hendelse) {
			String utskrift = tekst.getText();
			double belop = 0.0;
			JButton knappen = new JButton();
			try {
				belop = Double.parseDouble(belopFelt.getText());
				knappen = (JButton) hendelse.getSource();
			} catch (NumberFormatException e) {
				utskrift = "Kan ikke omforme " + belopFelt.getText() + " til et gyldig tall.";
			}
			if (knappen == knapp1) {
				utskrift = "Etter omregning blir beløpet: " + (new DecimalFormat("##.##").format(belop * 100 / 95.41));
			} else if (knappen == knapp2) {
				utskrift = "Etter omregning blir beløpet: " +  (new DecimalFormat("##.##").format(belop * 95.41 / 100));
			}
			tekst.setText(utskrift);
		}
	}
}

class Valutakalkulator {
	public static void main(String[] args) {
		Vindu etVindu = new Vindu();
		etVindu.setVisible(true);
	}
}