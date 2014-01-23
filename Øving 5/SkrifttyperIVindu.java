/**
 * SkrifttyperIVindu.java
 *
 * Øving 5, oppgave 1, vår 2014
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Vindu extends JFrame {
	private JButton knapp1 = new JButton("SansSerif");
	private JButton knapp2 = new JButton("Serif");
	private JButton knapp3 = new JButton("Monospaced");
	private JButton knapp4 = new JButton("Verdana"); // Dialog sto i oppgaven, men maskinen min har ikke fonten
	private JLabel tekst = new JLabel("Denne teksten får lik skrifttype som knappen du trykker på");
	
	public Vindu() {
		setTitle("Skrifttypeskift ved knappetrykk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		add(knapp1);
		add(knapp2);
		add(knapp3);
		add(knapp4);
		add(tekst);
		pack();
		Knappelytter lytteren = new Knappelytter();
		knapp1.addActionListener(lytteren);
		knapp2.addActionListener(lytteren);
		knapp3.addActionListener(lytteren);
		knapp4.addActionListener(lytteren);
	}
	
	private class Knappelytter implements ActionListener {
		public void actionPerformed(ActionEvent hendelse) {
			JButton knappen = (JButton) hendelse.getSource();
			if (knappen == knapp1) {
				tekst.setFont(new Font("SansSerif", 0, 15));
			} else if (knappen == knapp2) {
				tekst.setFont(new Font("Serif", 0, 15));
			} else if (knappen == knapp3) {
				tekst.setFont(new Font("Monospaced", 0, 15));
			} else {
				tekst.setFont(new Font("Verdana", 0, 15));
			}
		}
	}
}

class SkrifttyperIVindu {
	public static void main(String[] args) {
		Vindu etVindu = new Vindu();
		etVindu.setVisible(true);
		etVindu.setSize(550, 100);
	}
}