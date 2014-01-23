/**
 * RegistrerLantaker.java
 *
 * Øving 4, vår 2014
 */

import java.sql.*;
import static javax.swing.JOptionPane.*;
class RegistrerLantaker {
	public static void main(String[] args) throws Exception {
		String databasedriver = "org.apache.derby.jdbc.ClientDriver"; // Kopler til tjener, ligger i derbyclient.jar
		Class.forName(databasedriver); // Laster inn driverklassen
		
		String databasenavn = "jdbc:derby://localhost:1527/oving_db;user=db;password=db;create=true";
		Connection forbindelse = DriverManager.getConnection(databasenavn);
		Statement setning = forbindelse.createStatement();
		
		String navn = showInputDialog("Navn:");
                String ISBN = showInputDialog("ISBN:");
                int eksemplarNummer = (Integer.parseInt(showInputDialog("Eksemplarnummer:")));
                
                if (setning.executeUpdate("update eksemplar set laant_av = '" + navn + "' where isbn = '" + ISBN + "' and eks_nr = " + eksemplarNummer + " and laant_av is null") != 0) {
                    showMessageDialog(null, "Utlån registrert.");
                } else {
                    showMessageDialog(null, "Lånt ut fra før.");
                }
                setning.close();
                forbindelse.close();
	}
}